package joseduin.dogbreeds.restApi.model;

import joseduin.dogbreeds.model.DogImg;

/**
 * Respuesta del servidor para la lista de imagenes por raza
 */
public class BreedImgResponse {
    private DogImg dogImg;

    public void setDogImg(DogImg dogImg) {
        this.dogImg = dogImg;
    }

    public DogImg getDogImg() {
        return dogImg;
    }
}
