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

public class ListActorsAdapter extends RecyclerView.Adapter<ListActorsAdapter.ViewHolder> {
    private ArrayList<DataListActors> dataListActors;

    public ListActorsAdapter(ArrayList<DataListActors> dataListActors) {
        this.dataListActors = dataListActors;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_actors, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bindDataListActors(dataListActors.get(position));
    }

    @Override
    public int getItemCount() {
        return dataListActors.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.itemActors_imgActors)
        ImageView imgActors;

        @BindView(R.id.itemActors_tvNameActors)
        TextView tvNameActors;

        @BindView(R.id.itemActors_tvNumberLike)
        TextView tvNumberLike;

        Unbinder unbinder;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            unbinder = ButterKnife.bind(this, itemView);
        }

        private void bindDataListActors (DataListActors dataListActors){
            RequestOptions requestOptions = new RequestOptions();
            requestOptions = requestOptions.transforms(new CenterCrop(), new RoundedCorners(1));
            Glide.with(itemView.getContext()).load(dataListActors.getImageActors()).apply(requestOptions).into(imgActors);
            tvNameActors.setText(dataListActors.getNameActors());
            tvNumberLike.setText(String.valueOf(dataListActors.getNumberLikeActors()));
        }
    }
}
