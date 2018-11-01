package joseduin.dogbreeds.presenter;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;

import joseduin.dogbreeds.R;
import joseduin.dogbreeds.model.Dog;
import joseduin.dogbreeds.model.DogImg;
import joseduin.dogbreeds.presenter.interfaces.IBreedListImg;
import joseduin.dogbreeds.restApi.EndPointsApi;
import joseduin.dogbreeds.restApi.adapter.RestApiAdapter;
import joseduin.dogbreeds.restApi.model.BreedImgResponse;
import joseduin.dogbreeds.util.Message;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Presentador encargado de buscar las imagenes por raza
 */
public class BreedListImgPresenter implements IBreedListImgPresenter {
    private IBreedListImg iBreedListImg;
    private Context context;
    private Dog dog;
    private int positionBreed;
    private DogImg dogImg;

    public BreedListImgPresenter(IBreedListImg iBreedListImg, Context context, Dog dog, int positionBreed) {
        this.iBreedListImg = iBreedListImg;
        this.context = context;
        this.dog = dog;
        this.positionBreed = positionBreed;

        getImgs();
    }

    /**
     * Realizamos un request a la API para buscar
     * el listado de imagenes por raza
     */
    @Override
    public void getImgs() {
        RestApiAdapter restApiAdapter = new RestApiAdapter();
        Gson gson = restApiAdapter.breedsListImg();
        EndPointsApi endPointsApi = restApiAdapter.setApiConnection(gson);

        Call<BreedImgResponse> call = endPointsApi.getByBreedImgs( dog.getBreedAndSubBreed(positionBreed, "/") );
        Log.d("RESPONSE", call.request().url().toString() + " ");
        call.enqueue(new Callback<BreedImgResponse>() {
            @Override
            public void onResponse(Call<BreedImgResponse> call, Response<BreedImgResponse> response) {
                if (response.code() == 200) {
                    dogImg = response.body().getDogImg();
                    displayImgs();
                }
            }

            @Override
            public void onFailure(Call<BreedImgResponse> call, Throwable t) {
                Message.messageLong(context, context.getResources().getString(R.string.request_error));
            }
        });
    }

    /**
     * Mostramos en pantalla la data encontrada
     */
    @Override
    public void displayImgs() {
        this.iBreedListImg.setAdapter(this.iBreedListImg.initAdapter(dogImg));
        this.iBreedListImg.generateLayout();
    }
}
