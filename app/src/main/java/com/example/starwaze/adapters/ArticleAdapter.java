package com.example.starwaze.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.starwaze.R;
import com.example.starwaze.modeles.Article;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class  ArticleAdapter extends RecyclerView.Adapter<ArticleAdapter.ViewHolder> {
    private List<Article> articles;
    private OnArticleListener onArticleListener;
    private Context context;

    public ArticleAdapter(List<Article> articles, Context context, OnArticleListener onArticleListener){
        this.articles = articles;
        this.context = context;
        this.onArticleListener = onArticleListener;
    }
    // Provide a reference to the views for each article item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a article item in a view holder
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        // each article item is just a string in this case
        ImageView imageView;
        TextView title;
        TextView description;
        View layout;
        RelativeLayout itemContainer;
        OnArticleListener onArticleListener;

        public ViewHolder(@NonNull View itemView, OnArticleListener onArticleListener) {
            super(itemView);
            itemContainer = itemView.findViewById(R.id.item_container);
            layout = itemView;
            imageView = (ImageView) itemView.findViewById(R.id.imageView);
            title = (TextView) itemView.findViewById(R.id.title);
            description = (TextView) itemView.findViewById(R.id.description);
            this.onArticleListener = onArticleListener;

            itemView.setOnClickListener(this);
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
    @NotNull @Override
    public ArticleAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
        // create a new view
        LayoutInflater inflater = LayoutInflater.from(
                parent.getContext());
        View view =
                inflater.inflate(R.layout.row_layout, parent, false);
        // set the view's size, margins, paddings and layout parameters
        return new ViewHolder(view, onArticleListener);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        //animation
        holder.imageView.setAnimation(AnimationUtils.loadAnimation(context,R.anim.fade_transition_animation));
        holder.itemContainer.setAnimation(AnimationUtils.loadAnimation(context,R.anim.fade_scale_animation));

        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.imageView.setImageResource(articles.get(position).getImageId());
        holder.description.setText(articles.get(position).getDescription());
        holder.title.setText(articles.get(position).getTitle());
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return articles.size();
    }

    public interface OnArticleListener{
        void onArticleClick(int position);
    }

}