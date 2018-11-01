package joseduin.dogbreeds.presenter.interfaces;

import joseduin.dogbreeds.adapter.controller.RecyclerBreedImage;
import joseduin.dogbreeds.model.DogImg;

/**
 * Interfaz del listado de imagenes por razas - controlador
 */
public interface IBreedListImg {

    public void generateLayout();

    public RecyclerBreedImage initAdapter(DogImg dogImg);

    public void setAdapter(RecyclerBreedImage adapter);

}
