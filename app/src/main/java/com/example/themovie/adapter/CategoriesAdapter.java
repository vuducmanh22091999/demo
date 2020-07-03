package com.example.themovie.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.themovie.R;
import com.example.themovie.interfaces.ListenerCategories;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesAdapter.ViewHolder> {
    ArrayList<DataCategories> dataCategories;
    ListenerCategories listenerCategories;

    public CategoriesAdapter(ArrayList<DataCategories> dataCategories, ListenerCategories listenerCategories ) {
        this.dataCategories = dataCategories;
        this.listenerCategories = listenerCategories;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.custom_textview, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bindDataHolder(dataCategories.get(position));
    }

    @Override
    public int getItemCount() {
        return dataCategories.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.customTextView_tvCategories)
        TextView tvNameCategories;

        Unbinder unbinder;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            unbinder = ButterKnife.bind(this, itemView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listenerCategories.clickCategories(getAdapterPosition());
                }
            });
        }

        private void bindDataHolder(DataCategories dataCategories){
            tvNameCategories.setText(dataCategories.getNameCategories());
        }

    }
}
