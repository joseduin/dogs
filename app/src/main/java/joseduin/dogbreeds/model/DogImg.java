package joseduin.dogbreeds.model;

import java.util.ArrayList;

/**
 * Editad que asignamos las imagenes de las razas
 */
public class DogImg {
    private ArrayList<String> images;

    public DogImg(ArrayList<String> images) {
        this.images = images;
    }

    public ArrayList<String> getImages() {
        return images;
    }

    public void setImages(ArrayList<String> images) {
        this.images = images;
    }
}
