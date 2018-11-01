package joseduin.dogbreeds.adapter.controller;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import joseduin.dogbreeds.R;
import joseduin.dogbreeds.adapter.res.SliderAdapter;
import joseduin.dogbreeds.model.Dog;
import joseduin.dogbreeds.util.StringUtil;

/**
 * Adaptador de las razas de perros
 */
public class RecyclerBreeds extends RecyclerView.Adapter<RecyclerBreeds.BreedsViewHolder> {

    private Context context;
    private ArrayList<Dog> dogs;
    private ArrayList<Dog> dogsFilter;
    private SliderAdapter sliderAdapter;

    public RecyclerBreeds(Context context, ArrayList<Dog> dogs) {
        this.context = context;
        this.dogs = dogs;
        dogsFilter = dogs;
    }

    @NonNull
    @Override
    public BreedsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.card_breeds, parent, false);
        BreedsViewHolder pvh = new BreedsViewHolder(v);
        return pvh;
    }

    /**
     * Inicializamos la vista y el comportamiento de sus elementos
     */
    @Override
    public void onBindViewHolder(@NonNull BreedsViewHolder holder, int position) {
        Dog dog = dogs.get(position);

        sliderAdapter = new SliderAdapter(context, dog, position);
        holder.imgSlider.setAdapter(sliderAdapter);
        addIndicators(dog, holder);
    }

    /**
     * Asigna icono de paginacion
     * @param dog: Raza
     * @param holder: elementos de la vista
     */
    private void addIndicators(Dog dog, BreedsViewHolder holder) {
        holder.dogImg.setVisibility(View.GONE);

        if(dog.getSubBreed() == null) return;
        if(dog.getSubBreed().size() == 1) return;

        holder.dogImg.setVisibility(View.VISIBLE);
    }

    /**
     * Asignamos la cantidad de imagenes que tendra la vista
     */
    @Override
    public int getItemCount() {
        return dogs.size();
    }

    /**
     * Filtramos la busqueda de las razas
     * @param charText: texto a buscar
     */
    public void filter(String charText) {
        dogs = new ArrayList<>();
        if (charText.isEmpty()) {
            dogs.addAll( dogsFilter );
        } else {
            for (Dog dog : dogsFilter) {
                if(dog.getSubBreed() == null) {
                    filterDogs(charText, dog, 0);
                }
                else {
                    for(int i = 0; i < dog.getSubBreed().size(); i++) {
                        if(filterDogs(charText, dog, i)) {
                            break;
                        }
                    }
                }

            }
        }
        notifyDataSetChanged();
    }

    /**
     * Validamos que el texto a buscar coincida con alguna de las razas y subrazas
     * @param charText: texto a buscar
     * @param dog: raza evaluada
     * @param position: posicion de la subraza
     */
    private boolean filterDogs(String charText, Dog dog, int position) {
        boolean canInsert = StringUtil.contains(dog.getBreedAndSubBreed(position), charText);
        if(canInsert) {
            dogs.add( dog );
        }
        return canInsert;
    }

    /**
     * Enlazar los elementos de la vista con el controlador
     */
    public static class BreedsViewHolder extends RecyclerView.ViewHolder {
        private ViewPager imgSlider;
        private ImageView dogImg;

        BreedsViewHolder(View itemView) {
            super(itemView);
            imgSlider = itemView.findViewById(R.id.slideBreed);
            dogImg = itemView.findViewById(R.id.dogImg);
        }
    }
}
