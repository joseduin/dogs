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
import java.util.Map;
import java.util.Set;

import joseduin.dogbreeds.model.Dog;
import joseduin.dogbreeds.restApi.JsonKey;
import joseduin.dogbreeds.restApi.model.DogResponse;

/**
 * Deserializador de la lista de razas de perros
 */
public class DogDeserialize implements JsonDeserializer<DogResponse> {

    @Override
    public DogResponse deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        Gson gson = new Gson();
        DogResponse response = gson.fromJson(json.toString(), DogResponse.class);

        JsonObject data = JsonKey.responseWithOutPrefix(json);
        response.setDogs( deserialize(data) );
        return response;
    }

    private ArrayList<Dog> deserialize(JsonObject obj) {
        ArrayList<Dog> dogs = new ArrayList<>();

        String status = obj.get(JsonKey.STATUS).getAsString();
        if(status.equals(JsonKey.SUCCESS)) {
            JsonObject breeds = obj.getAsJsonObject(JsonKey.MESSAGE);
            dogs = deserializeBreeds(breeds);
        }
        return dogs;
    }

    private ArrayList<Dog> deserializeBreeds(JsonObject jsonBreeds) {
        ArrayList<Dog> dogs = new ArrayList<>();

        Set<Map.Entry<String, JsonElement>> breedsSet = jsonBreeds.entrySet();
        for(Map.Entry<String, JsonElement> mapBreed : breedsSet) {
            String breed = mapBreed.getKey();

            JsonArray breedsArray = jsonBreeds.get(breed).getAsJsonArray();
            if(breedsArray.size() == 0) {
                dogs.add(new Dog(breed, null));
            }
            else {
                ArrayList<Dog> subBreedArr = new ArrayList<>();
                for(int i = 0; i < breedsArray.size(); i++) {
                    String subBreed = breedsArray.get(i).getAsString();
                    subBreedArr.add(new Dog(subBreed, null));
                }
                dogs.add(new Dog(breed, subBreedArr));
            }
        }
        return dogs;
    }

}
