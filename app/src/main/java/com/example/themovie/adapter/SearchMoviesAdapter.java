package com.example.themovie.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.themovie.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class SearchMoviesAdapter extends RecyclerView.Adapter<SearchMoviesAdapter.ViewHolder> {
    private ArrayList<DataListMovies> searchDataListMovies;

    public SearchMoviesAdapter(ArrayList<DataListMovies> searchDataListMovies) {
        this.searchDataListMovies = searchDataListMovies;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_movies_search, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bindDataSearchMovies(searchDataListMovies.get(position));
    }

    @Override
    public int getItemCount() {
        return searchDataListMovies.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.itemMoviesSearch_imgPosterMovies)
        ImageView imgPosterMovies;

        @BindView(R.id.itemMoviesSearch_tvTitleMovies)
        TextView tvTitleMovies;

        @BindView(R.id.itemMoviesSearch_tvPointEvaluate)
        TextView tvPointEvaluate;

        Unbinder unbinder;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            unbinder = ButterKnife.bind(this, itemView);
        }

        private void bindDataSearchMovies(DataListMovies dataListMovies){
            Glide.with(itemView.getContext()).load(dataListMovies.getPoster_path()).into(imgPosterMovies);
            tvTitleMovies.setText(dataListMovies.getTitle());
            tvPointEvaluate.setText(String.valueOf(dataListMovies.getVote_average()));
        }
    }
}
