package joseduin.dogbreeds.presenter.interfaces;

import java.util.ArrayList;

import joseduin.dogbreeds.adapter.controller.RecyclerBreeds;
import joseduin.dogbreeds.model.Dog;

/**
 * Interfaz del listado de razas - controlador
 */
public interface IDogs {

    public void generateLayout();

    public RecyclerBreeds initAdapter(ArrayList<Dog> list);

    public void setAdapter(RecyclerBreeds adapter);

}
