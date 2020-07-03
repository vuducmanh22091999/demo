package com.example.themovie.adapter;

import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.themovie.R;
import com.example.themovie.data.ListMovies;
import com.example.themovie.data.RoomMovies;
import com.example.themovie.interfaces.ListenerMovies;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class ShowMoviesApdater extends RecyclerView.Adapter<ShowMoviesApdater.ViewHolder> {
    private List<RoomMovies> roomMoviesList;
    private ListenerMovies listenerMovies;

    public ShowMoviesApdater(List<RoomMovies> roomMoviesList, ListenerMovies listenerMovies) {
        this.roomMoviesList = roomMoviesList;
        this.listenerMovies = listenerMovies;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_show_movies, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bindDataShowMovies(roomMoviesList.get(position));
    }

    @Override
    public int getItemCount() {
        return roomMoviesList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.itemShowMovies_tvId)
        TextView tvId;
        @BindView(R.id.itemShowMovies_tvTitle)
        TextView tvTitle;

        Unbinder unbinder;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            unbinder = ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listenerMovies.clickMovie(getAdapterPosition());
                }
            });
        }


        public void bindDataShowMovies(RoomMovies roomMovies) {
            tvId.setText(String.valueOf(roomMovies.getIdMovies()));
            tvTitle.setText(roomMovies.getTitleMovies());
        }
    }
}
