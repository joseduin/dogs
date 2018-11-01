package joseduin.dogbreeds.restApi.deserialize;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.util.ArrayList;

import joseduin.dogbreeds.model.DogImg;
import joseduin.dogbreeds.restApi.JsonKey;
import joseduin.dogbreeds.restApi.model.BreedImgResponse;

/**
 * Deserializador de las imagenes por razas
 */
public class BreedsImgDeserialize implements JsonDeserializer<BreedImgResponse> {

    @Override
    public BreedImgResponse deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        Gson gson = new Gson();
        BreedImgResponse response = gson.fromJson(json.toString(), BreedImgResponse.class);

        JsonObject data = JsonKey.responseWithOutPrefix(json);
        response.setDogImg( deserialize(data) );
        return response;
    }

    private DogImg deserialize(JsonObject obj) {
        DogImg dogImage = null;
        String status = obj.get(JsonKey.STATUS).getAsString();
        if(status.equals(JsonKey.SUCCESS)) {
            JsonArray images = obj.get(JsonKey.MESSAGE).getAsJsonArray();
            ArrayList<String> imagesArr = new ArrayList<>();
            for (int i = 0; i < images.size(); i++) {
                String image = images.get(i).getAsString();
                imagesArr.add(image);
            }
            dogImage = new DogImg(imagesArr);
        }
        return dogImage;
    }

}
