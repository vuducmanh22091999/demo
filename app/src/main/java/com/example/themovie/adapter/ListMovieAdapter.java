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
import com.example.themovie.interfaces.ListenerMovies;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class ListMovieAdapter extends RecyclerView.Adapter<ListMovieAdapter.ViewHolder> {
    private ArrayList<DataListMovies> dataListMovies;
    private ListenerMovies listenerMovies;

    public ListMovieAdapter(ArrayList<DataListMovies> dataListMovies, ListenerMovies listenerMovies ) {
        this.dataListMovies = dataListMovies ;
        this.listenerMovies = listenerMovies;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_mylist, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bindDataListMovie(dataListMovies.get(position));
    }

    @Override
    public int getItemCount() {
        return dataListMovies.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.itemMyList_tvTitleMovies)
        TextView tvTitleMovies;

        @BindView(R.id.itemMyList_imgPosterMovies)
        ImageView imgPosterMovies;

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

        private void bindDataListMovie(DataListMovies dataListMovies){
            if(dataListMovies.getTitle().length() > 0){
                tvTitleMovies.setText(dataListMovies.getTitle());
            }
            else{
                tvTitleMovies.setVisibility(View.GONE);
            }
            RequestOptions requestOptions = new RequestOptions();
            requestOptions = requestOptions.transforms(new CenterCrop(), new RoundedCorners(20));
            Glide.with(itemView.getContext()).load(dataListMovies.getPoster_path()).apply(requestOptions).into(imgPosterMovies);
        }


    }
}
