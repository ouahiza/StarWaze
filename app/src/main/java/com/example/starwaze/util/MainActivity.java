package com.example.starwaze.util;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.starwaze.R;
import com.example.starwaze.adapters.ArticleAdapter;
import com.example.starwaze.modeles.Article;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity implements ArticleAdapter.OnArticleListener {
    private static final String TAG = "MainActivity";
    private RecyclerView recyclerView;
    private ArticleAdapter articleAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private List<Article> articles = Collections.emptyList();
    private Button buttonApod;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        List<Article> articles = fill_with_article();
        super.onCreate(savedInstanceState);
        //Toolbar toolbar = findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_main);

        getSupportActionBar().hide();

        buttonApod = (Button) findViewById(R.id.buttonApod);
        buttonApod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openApodActivity();
            }
        });

        recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        // in content do not change the layout size
        // of the RecyclerView
        recyclerView.setHasFixedSize(true);
        // use a linear layout manager
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        // define an adapter
        articleAdapter = new ArticleAdapter(articles, getApplication(), this);
        recyclerView.setAdapter(articleAdapter);

        layoutManager = new LinearLayoutManager(this);
    }

    private void openApodActivity() {
        Intent intent = new Intent(this, ApodActivity.class);
        startActivity(intent);
    }

    private List<Article> fill_with_article() {
        this.articles = new ArrayList<>();
        articles.add(new Article("Understand our solar system", "How many planets are there on our solar system ? What type of planet are each of them? All the answers are here!", R.drawable.solar_system));
        articles.add(new Article("Life of a star", "If you always wanted to be a star, understand what it takes to be one first", R.drawable.star));
        articles.add(new Article("What's an eclipse ?", "Do not ever look at an eclipse with bear eyes...here's why", R.drawable.eclipse));
        articles.add(new Article("Nasa next project", "Be the next nerd", R.drawable.astronaut));
        articles.add(new Article("The mystery of black holes", "What's in the other side of the tunnel ?", R.drawable.black_hole));
        articles.add(new Article("How does a spaceship work ?", "Just in case you're called for a mission", R.drawable.space_shuttle));
        articles.add(new Article("Understand our solar system", "How many planets are there on our solar system ? What type of planet are each of them? All the answers are here!", R.drawable.solar_system));
        articles.add(new Article("Life of a star", "If you always wanted to be a star, understand what it takes to be one first", R.drawable.star));
        articles.add(new Article("What's an eclipse ?", "Do not ever look at an eclipse with bear eyes...here's why", R.drawable.eclipse));
        articles.add(new Article("Nasa next project", "Be the next nerd", R.drawable.astronaut));
        articles.add(new Article("The mystery of black holes", "What's in the other side of the tunnel ?", R.drawable.black_hole));
        articles.add(new Article("How does a spaceship work ?", "Just in case you're called for a mission", R.drawable.space_shuttle));
        articles.add(new Article("Understand our solar system", "How many planets are there on our solar system ? What type of planet are each of them? All the answers are here!", R.drawable.solar_system));
        articles.add(new Article("Life of a star", "If you always wanted to be a star, understand what it takes to be one first", R.drawable.star));
        articles.add(new Article("What's an eclipse ?", "Do not ever look at an eclipse with bear eyes...here's why", R.drawable.eclipse));
        articles.add(new Article("Nasa next project", "Be the next nerd", R.drawable.astronaut));
        articles.add(new Article("The mystery of black holes", "What's in the other side of the tunnel ?", R.drawable.black_hole));
        articles.add(new Article("How does a spaceship work ?", "Just in case you're called for a mission", R.drawable.space_shuttle));

        return articles;
    }

    @Override
    public void onArticleClick(int position) {
        Log.d(TAG, "onArticleClick: opening");

        Intent intent = new Intent(this, ArticleActivity.class);
        intent.putExtra("selected_article", articles.get(position));
        startActivity(intent);

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
