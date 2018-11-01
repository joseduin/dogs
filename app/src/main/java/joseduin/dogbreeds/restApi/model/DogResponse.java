package joseduin.dogbreeds.restApi.model;

import java.util.ArrayList;

import joseduin.dogbreeds.model.Dog;

/**
 * Respuesta del servidor para la lista de razas
 */
public class DogResponse {
    private ArrayList<Dog> dogs = new ArrayList<>();

    public ArrayList<Dog> getDogs() {
        return dogs;
    }

    public void setDogs(ArrayList<Dog> dogs) {
        this.dogs = dogs;
    }
}
