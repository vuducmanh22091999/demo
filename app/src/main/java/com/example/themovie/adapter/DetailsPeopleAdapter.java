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

public class DetailsPeopleAdapter extends RecyclerView.Adapter<DetailsPeopleAdapter.ViewHolder> {
    private ArrayList<DataListMovies> dataListMoviesArrayList;
    private ListenerMovies listenerMovies;

    public DetailsPeopleAdapter(ArrayList<DataListMovies> dataListMoviesArrayList, ListenerMovies listenerMovies) {
        this.dataListMoviesArrayList = dataListMoviesArrayList;
        this.listenerMovies = listenerMovies;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_details_people, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bindDataDetailsPeople(dataListMoviesArrayList.get(position));
    }

    @Override
    public int getItemCount() {
        return dataListMoviesArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.itemDetailsPeople_imgPosterMovies)
        ImageView imgPosterMovies;

        @BindView(R.id.itemDetailsPeople_tvTitleMovies)
        TextView tvTitleMovies;

        @BindView(R.id.itemDetailsPeople_tvPointAverage)
        TextView tvPointAverage;

        @BindView(R.id.itemDetailsPeople_tvTypeMovies)
        TextView tvTypeMovies;

        @BindView(R.id.itemDetailsPeople_tvDate)
        TextView tvDate;

        @BindView(R.id.itemDetailsPeople_tvContentOverview)
        TextView tvContentOverview;

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

        private void bindDataDetailsPeople(DataListMovies dataListMovies){
            RequestOptions requestOptions = new RequestOptions();
            requestOptions = requestOptions.transforms(new CenterCrop(), new RoundedCorners(20));
            Glide.with(itemView.getContext()).load(dataListMovies.getPoster_path()).apply(requestOptions).into(imgPosterMovies);
            tvTitleMovies.setText(dataListMovies.getTitle());
            tvPointAverage.setText(String.valueOf(dataListMovies.getVote_average()));
            tvTypeMovies.setText(dataListMovies.getMedia_type());
            tvDate.setText(dataListMovies.getRelease_date());
            tvContentOverview.setText(dataListMovies.getOverview());
        }
    }
}
