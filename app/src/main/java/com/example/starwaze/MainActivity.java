package com.example.starwaze;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private MyAdapter myAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private List<Data> data = Collections.emptyList();;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        List<Data> data = fill_with_data();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Toolbar toolbar = findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        // in content do not change the layout size
        // of the RecyclerView
        recyclerView.setHasFixedSize(true);
        // use a linear layout manager
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        // define an adapter
        myAdapter = new MyAdapter(data, getApplication());
        recyclerView.setAdapter(myAdapter);

        layoutManager = new LinearLayoutManager(this);
    }

    private List<Data> fill_with_data() {
        this.data = new ArrayList<>();
        data.add(new Data("Understand our solar system","How many planets are there on our solar system ? What type of planet are each of them? All the answers are here!", R.drawable.solar_system));
        data.add(new Data("Life of a star", "If you always wanted to be a star, understand what it takes to be one first", R.drawable.star));
        data.add(new Data("What's an eclipse ?", "Do not ever look at an eclipse with bear eyes...here's why", R.drawable.eclipse));
        data.add(new Data("Nasa next project","Be the next nerd", R.drawable.astronaut));
        data.add(new Data("The mystery of black holes", "What's in the other side of the tunnel ?", R.drawable.black_hole));
        data.add(new Data("How does a spaceship work ?", "Just in case you're called for a mission", R.drawable.space_shuttle));

        return data;
    }
        /*FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                       .setAction("Action", null).show();
            }
        });*/
    /*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }*/
}
