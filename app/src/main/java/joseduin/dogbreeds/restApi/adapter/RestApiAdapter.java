package joseduin.dogbreeds.restApi.adapter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import joseduin.dogbreeds.restApi.ConstantsRestApi;
import joseduin.dogbreeds.restApi.EndPointsApi;
import joseduin.dogbreeds.restApi.deserialize.BreedsImgDeserialize;
import joseduin.dogbreeds.restApi.deserialize.DogDeserialize;
import joseduin.dogbreeds.restApi.deserialize.DogImgDeserialize;
import joseduin.dogbreeds.restApi.model.BreedImgResponse;
import joseduin.dogbreeds.restApi.model.DogImgResponse;
import joseduin.dogbreeds.restApi.model.DogResponse;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Vinculamos las respuestas contra los deserializadores
 */
public class RestApiAdapter {

    public EndPointsApi setApiConnection(Gson gson) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ConstantsRestApi.API)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        return retrofit.create(EndPointsApi.class);
    }

    public Gson dogs(){
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(DogResponse.class, new DogDeserialize());
        return gsonBuilder.create();
    }

    public Gson breedsImg(){
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(DogImgResponse.class, new DogImgDeserialize());
        return gsonBuilder.create();
    }

    public Gson breedsListImg(){
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(BreedImgResponse.class, new BreedsImgDeserialize());
        return gsonBuilder.create();
    }

}
