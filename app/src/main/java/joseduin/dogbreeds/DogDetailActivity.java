package joseduin.dogbreeds;

import android.support.design.widget.CollapsingToolbarLayout;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.squareup.picasso.Picasso;

import joseduin.dogbreeds.adapter.controller.RecyclerBreedImage;
import joseduin.dogbreeds.model.Dog;
import joseduin.dogbreeds.model.DogImg;
import joseduin.dogbreeds.presenter.BreedListImgPresenter;
import joseduin.dogbreeds.presenter.IBreedListImgPresenter;
import joseduin.dogbreeds.presenter.interfaces.IBreedListImg;
import joseduin.dogbreeds.util.AppCompactActivityBase;

public class DogDetailActivity extends AppCompactActivityBase
        implements IBreedListImg, RecyclerBreedImage.CallbackInterface {
    private Dog dog;
    private int positionBreed = 0;
    private RecyclerBreedImage adapter;
    private IBreedListImgPresenter iBreedListImgPresenter;
    private int numberOfColumns = 3;

    private Toolbar toolbar;
    private CollapsingToolbarLayout collapsingToolbar;
    private ImageView dogPreview;
    private RecyclerView recyclerImages;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dog_detail);

        getDog();
        linkView();
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        collapsingToolbar.setTitle(dog.getBreedAndSubBreed(positionBreed));

        iBreedListImgPresenter = new BreedListImgPresenter(this, DogDetailActivity.this, dog, positionBreed);
    }

    /**
    * Enlazar los elementos de la vista con el controlador
    */
    private void linkView() {
        toolbar = findViewById(R.id.toolbar);
        collapsingToolbar = findViewById(R.id.collapsingToolbar);
        dogPreview = findViewById(R.id.dogPreview);
        recyclerImages = findViewById(R.id.recyclerImages);
        progressBar = findViewById(R.id.progressBar);

        Picasso.get().load(dog.getImage(positionBreed)).into(dogPreview);
    }

    /**
     * Obtenemos los valores 'DOG' y 'POSITION' pasados mediante el
     * activity al ser creados
     */
    public void getDog() {
        Bundle extras = getIntent().getExtras();
        if(extras != null) {
            dog = (Dog) extras.getSerializable("DOG");
            positionBreed = extras.getInt("POSITION");
        }
    }

    /**
     * Configuramos el Layout que tendra el RecyclerView
     */
    @Override
    public void generateLayout() {
        StaggeredGridLayoutManager sglm =
                new StaggeredGridLayoutManager(numberOfColumns, StaggeredGridLayoutManager.VERTICAL);
        recyclerImages.setLayoutManager(sglm);
    }

    /**
     * Instanciamos el adaptador
     * @param dogImg: Imagenes de las razas
     * @return RecyclerBreedImage: Adaptador
     */
    @Override
    public RecyclerBreedImage initAdapter(DogImg dogImg) {
        adapter = new RecyclerBreedImage(DogDetailActivity.this, dogImg);
        return adapter;
    }

    /**
     * Asignamos el adaptador al RecyclerView
     * @param adapter: Adaptador
     */
    @Override
    public void setAdapter(RecyclerBreedImage adapter) {
        recyclerImages.setAdapter(adapter);
    }

    /**
     * Al momento de que las imagenes han sido cargadas en la vista
     * se desaparece el ProgressBar
     */
    @Override
    public void onLoadImages() {
        progressBar.setVisibility(View.GONE);
    }
}
