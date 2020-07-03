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
import com.example.themovie.data.Constant;
import com.example.themovie.data.PeopleModel;
import com.example.themovie.interfaces.ListenerPeople;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class SearchPeopleAdapter extends RecyclerView.Adapter<SearchPeopleAdapter.ViewHolder> {
    private ArrayList<PeopleModel> peopleModelArrayList;
    private ListenerPeople listenerPeople;

    public SearchPeopleAdapter(ArrayList<PeopleModel> peopleModelArrayList, ListenerPeople listenerPeople) {
        this.peopleModelArrayList = peopleModelArrayList;
        this.listenerPeople = listenerPeople;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_people, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bindDataPeople(peopleModelArrayList.get(position));
    }

    @Override
    public int getItemCount() {
        return peopleModelArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.itemPeople_imgAvatarPeople)
        ImageView imgAvatarPeople;

        @BindView(R.id.itemPeople_tvNamePeople)
        TextView tvNamePeople;

        @BindView(R.id.itemPeople_tvNumberMovies)
        TextView tvNumberMovies;

        Unbinder unbinder;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            unbinder = ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listenerPeople.clickPeople(getAdapterPosition());
                }
            });
        }

        private void bindDataPeople(PeopleModel peopleModel) {
            if (!peopleModel.getProfile_path().isEmpty())
            Glide.with(itemView.getContext()).load(peopleModel.getProfile_path()).into(imgAvatarPeople);
            tvNamePeople.setText(peopleModel.getName());
            tvNumberMovies.setText(String.valueOf(peopleModel.getKnown_for().size()));
        }
    }
}
