package com.example.starwaze.util;

import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.starwaze.R;
import com.example.starwaze.modeles.Article;


public class ArticleActivity extends AppCompatActivity {
    private static final String TAG = "ArticleActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_article);

        getSupportActionBar().hide();

        Article article = null;

        if (getIntent().hasExtra("selected_article")) {
            article = (Article) getIntent().getParcelableExtra("selected_article");
            Log.d(TAG, "onCreate: " + article.toString());
        }

        ImageView articleImageView = findViewById(R.id.image_article_activity);
        TextView articleTitle = findViewById(R.id.title_article_activity);
        TextView articleDescription = findViewById(R.id.description_article_activity);

        articleImageView.setImageResource(article.getImageId());
        articleTitle.setText(article.getTitle());
        articleDescription.setText(article.getDescription());
    }
}
