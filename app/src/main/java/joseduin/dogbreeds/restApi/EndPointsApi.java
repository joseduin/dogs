package joseduin.dogbreeds.restApi;

import joseduin.dogbreeds.restApi.model.BreedImgResponse;
import joseduin.dogbreeds.restApi.model.DogImgResponse;
import joseduin.dogbreeds.restApi.model.DogResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Endpoints
 */
public interface EndPointsApi {

    @GET(ConstantsRestApi.BREEDS_URL)
    Call<DogResponse> getAllBreeds();

    @GET(ConstantsRestApi.BREED_URL)
    Call<BreedImgResponse> getByBreedImgs(@Path(value = "breed_name", encoded = true) String breed_name);

    @GET(ConstantsRestApi.RANDOM_URL)
    Call<DogImgResponse> getRandomImgByBreed(@Path(value = "breed_name", encoded = true) String breed_name);
}
