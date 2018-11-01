package joseduin.dogbreeds.presenter;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;

import java.util.ArrayList;

import joseduin.dogbreeds.R;
import joseduin.dogbreeds.model.Dog;
import joseduin.dogbreeds.presenter.interfaces.IDogs;
import joseduin.dogbreeds.restApi.EndPointsApi;
import joseduin.dogbreeds.restApi.adapter.RestApiAdapter;
import joseduin.dogbreeds.restApi.model.DogResponse;
import joseduin.dogbreeds.util.Message;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Presentador encargado de buscar las razas de perros en la API
 */
public class DogsPresenter implements IDogsPresenter {
    private IDogs iDogs;
    private Context context;
    private ArrayList<Dog> dogs;

    public DogsPresenter(IDogs iDogs, Context context) {
        this.iDogs = iDogs;
        this.context = context;

        getDogs();
    }

    /**
     * Realizamos un request a la API para buscar
     * el listado de razas de perros
     */
    @Override
    public void getDogs() {
        RestApiAdapter restApiAdapter = new RestApiAdapter();
        Gson gson = restApiAdapter.dogs();
        EndPointsApi endPointsApi = restApiAdapter.setApiConnection(gson);

        Call<DogResponse> call = endPointsApi.getAllBreeds();
        Log.d("RESPONSE", call.request().url().toString() + " ");
        call.enqueue(new Callback<DogResponse>() {
            @Override
            public void onResponse(Call<DogResponse> call, Response<DogResponse> response) {
                if (response.code() == 200) {
                    dogs = response.body().getDogs();
                    displayDogs();
                }
            }

            @Override
            public void onFailure(Call<DogResponse> call, Throwable t) {
                Message.messageLong(context, context.getResources().getString(R.string.request_error));
                Log.d("ERROR", t.toString());
            }
        });
    }

    /**
     * Mostramos en pantalla la data encontrada
     */
    @Override
    public void displayDogs() {
        this.iDogs.setAdapter(this.iDogs.initAdapter(dogs));
        this.iDogs.generateLayout();
    }
}
