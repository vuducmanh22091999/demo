package com.example.themovie.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.themovie.R;
import com.example.themovie.data.KeywordModel;
import com.example.themovie.data.ListKeyword;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class ListKeywordAdapter extends RecyclerView.Adapter<ListKeywordAdapter.ViewHolder> {
    private ArrayList<KeywordModel> keywordModelArrayList;

    public ListKeywordAdapter(ArrayList<KeywordModel> keywordModelArrayList) {
        this.keywordModelArrayList = keywordModelArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_keyword, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bindDataKeyword(keywordModelArrayList.get(position));
    }

    @Override
    public int getItemCount() {
        return keywordModelArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.itemKeyword_tvKeyword)
        TextView tvKeyword;

        Unbinder unbinder;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            unbinder = ButterKnife.bind(this, itemView);
        }

        private void bindDataKeyword(KeywordModel keywordModel) {
            tvKeyword.setText(keywordModel.getName());
        }
    }
}
