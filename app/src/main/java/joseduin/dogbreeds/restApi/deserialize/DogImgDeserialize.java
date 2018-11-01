package joseduin.dogbreeds.restApi.deserialize;

import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

import joseduin.dogbreeds.restApi.JsonKey;
import joseduin.dogbreeds.restApi.model.DogImgResponse;

/**
 * Deserializador de las imagenes random por raza
 */
public class DogImgDeserialize implements JsonDeserializer<DogImgResponse> {

    @Override
    public DogImgResponse deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        Gson gson = new Gson();
        DogImgResponse response = gson.fromJson(json.toString(), DogImgResponse.class);

        JsonObject data = JsonKey.responseWithOutPrefix(json);
        response.setDogImg( deserialize(data) );
        return response;
    }

    private String deserialize(JsonObject obj) {
        String image = null;
        String status = obj.get(JsonKey.STATUS).getAsString();
        if(status.equals(JsonKey.SUCCESS)) {
            image = obj.get(JsonKey.MESSAGE).getAsString();
        }
        return image;
    }

}
