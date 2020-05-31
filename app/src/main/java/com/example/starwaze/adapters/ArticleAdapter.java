package com.example.starwaze.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.starwaze.R;
import com.example.starwaze.modeles.Article;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ArticleAdapter extends RecyclerView.Adapter<ArticleAdapter.ViewHolder> {
    private List<Article> articles;
    private OnArticleListener onArticleListener;
    private Context context;

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }

    public ArticleAdapter(List<Article> articles, Context context, OnArticleListener onArticleListener) {
        this.articles = articles;
        this.context = context;
        this.onArticleListener = onArticleListener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView imageView;
        TextView title;
        TextView description;
        ImageView favImage;
        View layout;
        RelativeLayout itemContainer;
        OnArticleListener onArticleListener;

        public ViewHolder(@NonNull View itemView, OnArticleListener onArticleListener) {
            super(itemView);
            favImage = itemView.findViewById(R.id.fav);
            itemContainer = itemView.findViewById(R.id.item_container);
            layout = itemView;
            imageView = (ImageView) itemView.findViewById(R.id.imageView);
            title = (TextView) itemView.findViewById(R.id.title);
            description = (TextView) itemView.findViewById(R.id.description);
            this.onArticleListener = onArticleListener;

            itemView.setOnClickListener(this);

            favImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onArticleListener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION)
                            onArticleListener.onFavClick(position);
                    }
                }
            });
        }

        @Override
        public void onClick(View v) {
            onArticleListener.onArticleClick(getAdapterPosition());
        }
    }

    public void add(int position, Article article) {
        this.articles.add(position, article);
        notifyItemInserted(position);
    }

    public void remove(Article article) {
        int position = articles.indexOf(article);
        articles.remove(position);
        notifyItemRemoved(position);
    }

    // Create new views (invoked by the layout manager)
    @NotNull
    @Override
    public ArticleAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                        int viewType) {
        //Inflating the row layout
        LayoutInflater inflater = LayoutInflater.from(
                parent.getContext());
        View view =
                inflater.inflate(R.layout.row_layout, parent, false);

        return new ViewHolder(view, onArticleListener);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        //animation
        holder.imageView.setAnimation(AnimationUtils.loadAnimation(context, R.anim.fade_transition_animation));
        holder.itemContainer.setAnimation(AnimationUtils.loadAnimation(context, R.anim.fade_scale_animation));

        holder.imageView.setImageResource(articles.get(position).getImageId());
        holder.description.setText(articles.get(position).getDescription());
        holder.title.setText(articles.get(position).getTitle());
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return articles.size();
    }

    public interface OnArticleListener {
        void onArticleClick(int position);

        void onFavClick(int position);
    }

}