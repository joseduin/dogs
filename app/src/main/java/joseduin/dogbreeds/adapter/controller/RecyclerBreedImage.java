package joseduin.dogbreeds.adapter.controller;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import joseduin.dogbreeds.ImagePreviewActivity;
import joseduin.dogbreeds.R;
import joseduin.dogbreeds.model.DogImg;

/**
 * Adaptador de las imagenes por raza
 */
public class RecyclerBreedImage extends RecyclerView.Adapter<RecyclerBreedImage.BreedsViewHolder> {
    private Context context;
    private DogImg dog;
    private CallbackInterface mCallback;

    public interface CallbackInterface {
        /**
         * Detonador cuando la vista este lista para visualizar
         * todas las imagenes
         */
        void onLoadImages();
    }

    public RecyclerBreedImage(Context context, DogImg dog) {
        this.context = context;
        this.dog = dog;
        mCallback = (CallbackInterface) context;
    }

    @NonNull
    @Override
    public BreedsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.card_breed_image, parent, false);
        BreedsViewHolder pvh = new BreedsViewHolder(v);
        return pvh;
    }

    /**
     * Inicializamos la vista y el comportamiento de sus elementos
     */
    @Override
    public void onBindViewHolder(@NonNull BreedsViewHolder holder, final int position) {
        final String image = dog.getImages().get(position);

        Picasso.get().load(image).into(holder.dogImg, new Callback() {
            @Override
            public void onSuccess() {
                loadLastImg(position);
            }

            @Override
            public void onError(Exception e) {
                loadLastImg(position);
            }
        });
        holder.container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, ImagePreviewActivity.class);
                i.putExtra("IMAGE", image);
                context.startActivity(i);
            }
        });
    }

    /**
     * Al momento de que sea cargada la ultima imagen
     * se llama al detonador para que avise a la vista
     */
    private void loadLastImg(int position) {
        if((dog.getImages().size() - 1) == position) {
            if(mCallback != null) {
                mCallback.onLoadImages();
            }
        }
    }

    /**
     * Asignamos la cantidad de imagenes que tendra la vista
     */
    @Override
    public int getItemCount() {
        return dog.getImages().size();
    }

    /**
     * Enlazar los elementos de la vista con el controlador
     */
    public static class BreedsViewHolder extends RecyclerView.ViewHolder {
        private ImageView dogImg;
        private View container;

        BreedsViewHolder(View itemView) {
            super(itemView);
            container = itemView;
            dogImg = itemView.findViewById(R.id.dogImg);
        }
    }
}
