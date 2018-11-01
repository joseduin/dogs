package joseduin.dogbreeds.restApi;

/**
 * Routas de la API a consumir
 */
public class ConstantsRestApi {
    public static final String API = "https://dog.ceo/api/";

    private static final String BREEDS = "breeds/list/all";
    public static final String BREEDS_URL = API + BREEDS;

    private static final String BREED = "breed/{breed_name}/images";
    public static final String BREED_URL = API + BREED;

    private static final String RANDOM = "breed/{breed_name}/images/random";
    public static final String RANDOM_URL = API + RANDOM;

}
