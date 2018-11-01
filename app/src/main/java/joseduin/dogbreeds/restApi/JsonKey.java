package joseduin.dogbreeds.restApi;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

/**
 * Key de los JSON que son utilizados en la app
 */
public class JsonKey {
    public static final String DATA = "data";
    public static final String MESSAGE = "message";

    public static final String STATUS = "status";
    public static final String SUCCESS = "success";

    /**
     * Convertimos el JSON en otro con mejor manejo de la data
     * @example
     *      {
     *          "status":"success"
     *      }
     *
     *      convert to:
     *      "data": {
     *          "status":"success"
     *      }
     * @param json: JSON a transformar
     * @return JSON mejorado
     */
    public static final JsonObject responseWithOutPrefix(JsonElement json) {
        JsonObject obj = new JsonObject();
        obj.add(JsonKey.DATA, json);
        return obj.getAsJsonObject().getAsJsonObject(JsonKey.DATA);
    }
}
