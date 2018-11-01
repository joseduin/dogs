package joseduin.dogbreeds.presenter.interfaces;

import android.widget.ImageView;

import joseduin.dogbreeds.model.Dog;

/**
 * Interfaz de la imagen random por raza - controlador
 */
public interface IDogImg {

    public void displayImg(Dog dog, int position, ImageView slideImageView);

}
