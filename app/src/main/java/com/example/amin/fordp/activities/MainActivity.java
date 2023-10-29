package com.example.amin.fordp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.amin.fordp.R;
import com.example.amin.fordp.adapter.ElementAdapter;
import com.example.amin.fordp.models.ElementoRealm;

import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.Case;

public class MainActivity extends AppCompatActivity implements ElementAdapter.OnItemClickListener {

    private Realm realm;
    private RealmResults<ElementoRealm> elementResults;
    private ElementAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }

        realm = Realm.getDefaultInstance();

        elementResults = realm.where(ElementoRealm.class).findAll();

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));

        adapter = new ElementAdapter(elementResults);

        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(this);

        SearchView searchView = findViewById(R.id.SearchView);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextChange(String newText) {
                newText = newText.toLowerCase();

                RealmResults<ElementoRealm> filteredResults = elementResults.where()
                        .contains("title", newText, Case.INSENSITIVE)
                        .findAll();

                adapter.updateData(filteredResults);

                return true;
            }

            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }
        });
    }

    @Override
    public void onItemClick(@NonNull ElementoRealm element) {
        Intent intent = new Intent(this, DescriptionActivity.class);
        intent.putExtra("title", element.getTitle());
        intent.putExtra("imageResource", element.getImageResource());
        intent.putExtra("description", element.getDescription());
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.personajes) {
            return true;
        } else if (id == R.id.objetos) {
            return true;
        } else if (id == R.id.lugares) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        realm.close();
    }
}