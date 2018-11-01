package joseduin.dogbreeds;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import java.util.ArrayList;

import joseduin.dogbreeds.adapter.controller.RecyclerBreeds;
import joseduin.dogbreeds.model.Dog;
import joseduin.dogbreeds.presenter.DogsPresenter;
import joseduin.dogbreeds.presenter.IDogsPresenter;
import joseduin.dogbreeds.presenter.interfaces.IDogs;

public class DogsActivity extends AppCompatActivity implements IDogs, SearchView.OnQueryTextListener {
    private RecyclerView recyclerBreed;
    private RecyclerBreeds adapter;
    private SearchView searchBreed;

    private IDogsPresenter iDogsPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dogs);

        linkView();
        iDogsPresenter = new DogsPresenter(this, DogsActivity.this);
    }

    /**
     * Enlazar los elementos de la vista con el controlador
     */
    private void linkView() {
        recyclerBreed = findViewById(R.id.recyclerBreed);
        searchBreed = findViewById(R.id.searchBreed);
        searchBreed.setOnQueryTextListener(this);
    }

    /**
     * Configuramos el Layout que tendra el RecyclerView
     */
    @Override
    public void generateLayout() {
        LinearLayoutManager llm = new LinearLayoutManager(DogsActivity.this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);

        StaggeredGridLayoutManager sglm =
                new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);

        recyclerBreed.setLayoutManager(sglm);
    }

    /**
     * Instanciamos el adaptador
     * @param list: lista de razas de perros
     * @return RecyclerBreeds: Adaptador
     */
    @Override
    public RecyclerBreeds initAdapter(ArrayList<Dog> list) {
        adapter = new RecyclerBreeds(DogsActivity.this, list);
        return adapter;
    }

    /**
     * Asignamos el adaptador al RecyclerView
     * @param adapter: Adaptador
     */
    @Override
    public void setAdapter(RecyclerBreeds adapter) {
        recyclerBreed.setAdapter(adapter);
    }

    /**
     * Detonador cuando se da click al searchBar
     * @param query: Texto a buscar
     */
    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    /**
     * Detonador cuando se esta escribiendo en el searchBar
     * @param newText: Texto a buscar
     */
    @Override
    public boolean onQueryTextChange(String newText) {
        if(adapter != null)
            adapter.filter(newText);
        return true;
    }
}
