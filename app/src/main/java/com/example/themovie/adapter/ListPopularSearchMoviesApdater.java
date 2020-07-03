package com.example.themovie.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.themovie.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class ListPopularSearchMoviesApdater extends RecyclerView.Adapter<ListPopularSearchMoviesApdater.ViewHolder> {
    private ArrayList<DataListPopularSearchMovies> dataListPopularSearchMovies;

    public ListPopularSearchMoviesApdater(ArrayList<DataListPopularSearchMovies> dataListPopularSearchMovies) {
        this.dataListPopularSearchMovies = dataListPopularSearchMovies;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_popular_movies, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bindDataListPopularMovies(dataListPopularSearchMovies.get(position));
    }

    @Override
    public int getItemCount() {
        return dataListPopularSearchMovies.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.itemPopularMovies_imgPosterMovies)
        ImageView imgPosterMovies;

        @BindView(R.id.itemPopularMovies_tvNameMovies)
        TextView tvNameMovies;

        @BindView(R.id.itemPopularMovies_tvTimeOfMovies)
        TextView tvTimeOfMovies;

        @BindView(R.id.itemPopularMovies_tvCategoriesMovies)
        TextView tvCategoriesMovies;

        @BindView(R.id.itemPopularMovies_tvNameDirector)
        TextView tvNameDirector;

        @BindView(R.id.itemPopularMovies_tvPointEvaluate)
        TextView tvPointEvaluate;

        @BindView(R.id.itemPopularMovies_tvNumberReviews)
        TextView tvNumberReviews;

        Unbinder unbinder;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            unbinder = ButterKnife.bind(this, itemView);
        }

        private void bindDataListPopularMovies(DataListPopularSearchMovies dataListPopularSearchMovies){
            RequestOptions requestOptions = new RequestOptions();
            requestOptions = requestOptions.transforms(new CenterCrop(), new RoundedCorners(20));
            Glide.with(itemView.getContext()).load(dataListPopularSearchMovies.getPosterMovies()).apply(requestOptions).into(imgPosterMovies);
            tvNameMovies.setText(dataListPopularSearchMovies.getNameMovies());
            tvTimeOfMovies.setText(String.valueOf(dataListPopularSearchMovies.getTimeOfMovies()));
            tvCategoriesMovies.setText(dataListPopularSearchMovies.getCategoriesMovies());
            tvNameDirector.setText(dataListPopularSearchMovies.getNameDirector());
            tvPointEvaluate.setText(String.valueOf(dataListPopularSearchMovies.getPointEvaluate()));
            tvNumberReviews.setText(String.valueOf(dataListPopularSearchMovies.getNumberReviews()));
        }
    }
}
