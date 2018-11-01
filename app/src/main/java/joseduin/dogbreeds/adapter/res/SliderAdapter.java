package joseduin.dogbreeds.adapter.res;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import joseduin.dogbreeds.DogDetailActivity;
import joseduin.dogbreeds.R;
import joseduin.dogbreeds.model.Dog;
import joseduin.dogbreeds.presenter.DogImgPresenter;
import joseduin.dogbreeds.presenter.IDogImgPresenter;
import joseduin.dogbreeds.presenter.interfaces.IDogImg;

/**
 * Adaptador de las imagenes tipo Slider
 */
public class SliderAdapter extends PagerAdapter implements IDogImg {
    private Context context;
    private LayoutInflater layoutInflater;
    private Dog dog;
    private int position;
    private IDogImgPresenter iDogImgPresenter;

    private ImageView slideImageView;
    private TextView dogBreed;

    public SliderAdapter(Context context, Dog dog, int position) {
        this.context = context;
        this.dog = dog;
        this.position = position;
    }

    /**
     * Asignamos la cantidad de imagenes que tendra el PagerView
     */
    @Override
    public int getCount() {
        return dog.getSubBreed() == null ? 1 : dog.getSubBreed().size();
    }

    /**
     * Instanciamos la vista
     */
    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    /**
     * Inicializamos la vista y el comportamiento de sus elementos
     */
    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.slide_layout, container, false);
        linkView(view);

        dogBreed.setText(dog.getBreedAndSubBreed(position));
        iDogImgPresenter = new DogImgPresenter(this, context, dog, position, slideImageView);
        final int positionImgSlider = position;
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, DogDetailActivity.class);
                i.putExtra("DOG", dog);
                i.putExtra("POSITION", positionImgSlider);
                context.startActivity(i);
            }
        });

        container.addView(view);
        return view;
    }

    /**
     * Enlazar los elementos de la vista con el controlador
     */
    private void linkView(View view) {
        slideImageView = view.findViewById(R.id.dogImg);
        dogBreed = view.findViewById(R.id.dogBreed);
    }

    /**
     * Removemos las imagenes que quedan atras para ir liberando memoria
     */
    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((RelativeLayout) object);
    }

    /**
     * Denotador cuando encontremos la imagen referencial a la raza
     * @param dog: raza
     * @param position: posicion de la subraza
     * @param slideImageView: ImagenView a imprimir la imagen
     */
    @Override
    public void displayImg(Dog dog, int position, ImageView slideImageView) {
        this.dog = dog;

        displayImg(position, slideImageView);
    }

    /**
     * Mostramos la imagen correspondiente en la vida
     * @param position: posicion de la subraza
     * @param slideImageView: ImagenView a imprimir la imagen
     */
    private void displayImg(int position, ImageView slideImageView) {
        Picasso.get().load(dog.getImage(position)).into(slideImageView);
    }

}
