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

public class ListRecentMoviesAdapter extends RecyclerView.Adapter<ListRecentMoviesAdapter.ViewHolder> {
    private ArrayList<DataListMovies> dataListMovies;

    public ListRecentMoviesAdapter(ArrayList<DataListMovies> dataListMovies) {
        this.dataListMovies = dataListMovies;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_recent_movies, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bindDataListRecentMovies(dataListMovies.get(position));
    }

    @Override
    public int getItemCount() {
        return dataListMovies.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.itemRecentMovies_imgPosterMovies)
        ImageView imgPosterMovies;

        @BindView(R.id.itemRecentMovies_tvNameMovies)
        TextView tvNameMovies;

        Unbinder unbinder;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            unbinder = ButterKnife.bind(this, itemView);
        }

        private void bindDataListRecentMovies(DataListMovies dataListMovies){
//            tvNameMovies.setText(dataListMovies.getNameListMovies());
//            RequestOptions requestOptions = new RequestOptions();
//            requestOptions = requestOptions.transforms(new CenterCrop(), new RoundedCorners(20));
//            Glide.with(itemView.getContext()).load(dataListMovies.getPosterListMovies()).apply(requestOptions).into(imgPosterMovies);
        }
    }
}
