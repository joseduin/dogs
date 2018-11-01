package joseduin.dogbreeds.model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Entidad que asignamos los datos de la raza y subrazas
 */
@SuppressWarnings("serial")
public class Dog implements Serializable {
    private String breed;
    private ArrayList<Dog> subBreed;
    private String image = null;

    public Dog(String breed, ArrayList<Dog> subBreed) {
        this.breed = breed;
        this.subBreed = subBreed;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public ArrayList<Dog> getSubBreed() {
        return subBreed;
    }

    public void setSubBreed(ArrayList<Dog> subBreed) {
        this.subBreed = subBreed;
    }

    public String getImage() {
        return image;
    }

    public String getImage(int position) {
        if(subBreed != null) {
            return subBreed.get(position).getImage();
        }
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setImageBySubBreed(int index, String image) {
        if(subBreed != null) {
            subBreed.get(index).setImage(image);
        }
    }

    public String getBreedAndSubBreed(int position) {
        return breedAndSubBreed(position, " - ");
    }

    public String getBreedAndSubBreed(int position, String split) {
        return breedAndSubBreed(position, split);
    }

    private String breedAndSubBreed(int position, String split) {
        String name = this.breed;
        if(subBreed != null) {
            name = String.format("%s%s%s", name, split, subBreed.get(position).getBreed());
        }
        return name;
    }
}
