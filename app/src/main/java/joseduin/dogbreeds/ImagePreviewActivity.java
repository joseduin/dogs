package joseduin.dogbreeds;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import joseduin.dogbreeds.util.AppCompactActivityBase;

public class ImagePreviewActivity extends AppCompactActivityBase {
    private String image;

    private Toolbar toolbar;
    private ImageView imagePreview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_preview);

        getImage();
        linkView();

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    /**
     * Obtenemos el valor 'IMAGE' pasados mediante el
     * activity al ser creados
     */
    public void getImage() {
        Bundle extras = getIntent().getExtras();
        if(extras != null) {
            image = extras.getString("IMAGE");
        }
    }

    /**
     * Enlazar los elementos de la vista con el controlador
     */
    private void linkView() {
        toolbar = findViewById(R.id.toolbar);
        imagePreview = findViewById(R.id.imagePreview);

        Picasso.get().load(image).into(imagePreview);
    }

}
