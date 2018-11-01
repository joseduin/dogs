package joseduin.dogbreeds.presenter;

import android.content.Context;
import android.util.Log;
import android.widget.ImageView;

import com.google.gson.Gson;

import joseduin.dogbreeds.R;
import joseduin.dogbreeds.model.Dog;
import joseduin.dogbreeds.presenter.interfaces.IDogImg;
import joseduin.dogbreeds.restApi.EndPointsApi;
import joseduin.dogbreeds.restApi.adapter.RestApiAdapter;
import joseduin.dogbreeds.restApi.model.DogImgResponse;
import joseduin.dogbreeds.util.Message;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Presentador encargado de buscar la imagen random por raza
 */
public class DogImgPresenter implements IDogImgPresenter {
    private IDogImg iDogImg;
    private Dog breed;
    private Context context;
    private int position;
    private ImageView slideImageView;

    public DogImgPresenter(IDogImg iDogImg, Context context, Dog dog, int position, ImageView slideImageView) {
        this.iDogImg = iDogImg;
        this.context = context;
        this.position = position;
        this.slideImageView = slideImageView;
        this.breed = dog;

        getDogImg();
    }

    /**
     * Realizamos un request a la API para buscar
     * la imagen random por raza y luego la mostramos en pantalla
     */
    @Override
    public void getDogImg() {
        RestApiAdapter restApiAdapter = new RestApiAdapter();
        Gson gson = restApiAdapter.breedsImg();
        EndPointsApi endPointsApi = restApiAdapter.setApiConnection(gson);

        Call<DogImgResponse> call = endPointsApi.getRandomImgByBreed( breed.getBreedAndSubBreed(position, "/") );
        call.enqueue(new Callback<DogImgResponse>() {
            @Override
            public void onResponse(Call<DogImgResponse> call, Response<DogImgResponse> response) {
                if (response.code() == 200) {
                    String img = response.body().getDogImg();
                    if(breed.getSubBreed() == null) {
                        breed.setImage(img);
                    }
                    else {
                        breed.setImageBySubBreed(position, img);
                    }
                    iDogImg.displayImg(breed, position, slideImageView);
                }
            }

            @Override
            public void onFailure(Call<DogImgResponse> call, Throwable t) {
                Message.messageLong(context, context.getResources().getString(R.string.request_error));
            }
        });
    }
}
