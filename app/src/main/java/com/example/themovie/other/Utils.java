package com.example.themovie.other;

import android.content.Context;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class Utils {
    static public void initRecyclerView(Context context, RecyclerView recyclerView, RecyclerView.Adapter adapter, int i) {
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(context, i, false));
        recyclerView.setAdapter(adapter);
    }

    static public void initGridView(Context context, RecyclerView recyclerView, RecyclerView.Adapter adapter, int colums) {
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(context, colums));
        recyclerView.setAdapter(adapter);
    }
}
