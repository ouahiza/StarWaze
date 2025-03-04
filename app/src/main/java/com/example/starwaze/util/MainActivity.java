package com.example.starwaze.util;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.starwaze.R;
import com.example.starwaze.adapters.ArticleAdapter;
import com.example.starwaze.modeles.Article;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;
import com.muddzdev.styleabletoastlibrary.StyleableToast;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity implements ArticleAdapter.OnArticleListener, NavigationView.OnNavigationItemSelectedListener {
    private static final String TAG = "MainActivity";
    private RecyclerView recyclerView;
    private ArticleAdapter articleAdapter;
    private RecyclerView.LayoutManager layoutManager;
    SharedPreferences sharedPreferences;
    static List<Article> favArticles = Collections.emptyList();
    private List<Article> articles = Collections.emptyList();
    private DrawerLayout drawer;
    private FloatingActionButton favArticlesBtn;
    private boolean displayingFavs;
    Context context;
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_main);

        List<Article> articles = fill_with_article();
        favArticles = new ArrayList<Article>();
        getSupportActionBar().hide();
        context = this;
        favArticlesBtn = findViewById(R.id.fab);
        sharedPreferences = getSharedPreferences("userPrefs", Context.MODE_PRIVATE);
        try {
            favArticles = (ArrayList<Article>) ObjectSerializer.deserialize(sharedPreferences.getString("userPrefs", ObjectSerializer.serialize(new ArrayList<Article>())));
        } catch (IOException e) {
            e.printStackTrace();
        }

        drawer = findViewById(R.id.drawer_layout_main_activity);
        navigationView = findViewById(R.id.menu);
        navigationView.setNavigationItemSelectedListener(this);


        recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        articleAdapter = new ArticleAdapter(articles, getApplication(), this);
        recyclerView.setAdapter(articleAdapter);
        layoutManager = new LinearLayoutManager(this);

        displayingFavs = false;
        favArticlesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //If all articles are shown, on click show the favs, else (favs are shown) show all articles
                if (!displayingFavs) {
                    articleAdapter.setArticles(favArticles);
                    recyclerView.setAdapter(articleAdapter);
                    layoutManager = new LinearLayoutManager(context);
                    displayingFavs = true;
                } else {
                    articleAdapter.setArticles(articles);
                    recyclerView.setAdapter(articleAdapter);
                    layoutManager = new LinearLayoutManager(context);
                    displayingFavs = false;
                }
            }
        });
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

    @Override
    public void onFavClick(int position) {
        saveArticle(position);
    }

    //to fav an article
    public void saveArticle(int position) {
        Article article = articles.get(position);
        favArticles.add(article);

        SharedPreferences.Editor editor = sharedPreferences.edit();
        try {
            editor.putString("userPrefs", ObjectSerializer.serialize((Serializable) favArticles));
        } catch (IOException e) {
            e.printStackTrace();
        }

        editor.apply();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_apod:
                openApodActivity();
                break;
            case R.id.menu_send_a_question:
                showBuildingToast();
                break;
            case R.id.menu_settings:
                showBuildingToast();
                break;
            case R.id.menu_about_us:
                showBuildingToast();
                break;
        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void showBuildingToast() {
        StyleableToast.makeText(context, "This feature is not yet build !", R.style.featureBuildingToast).show();
    }


}
