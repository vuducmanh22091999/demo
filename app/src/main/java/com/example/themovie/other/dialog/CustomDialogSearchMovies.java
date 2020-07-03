package com.example.themovie.other.dialog;


import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.themovie.R;
import com.example.themovie.adapter.DataListMovies;
import com.example.themovie.adapter.SearchMoviesAdapter;
import com.example.themovie.interfaces.ListenerDialogSearchMovies;
import com.example.themovie.other.Utils;
import com.example.themovie.ui.search.SearchMoviesContract;
import com.example.themovie.ui.search.SearchMoviesPresenter;

import java.util.ArrayList;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class CustomDialogSearchMovies extends DialogFragment implements SearchMoviesContract.View {
    @BindView(R.id.customDialogSearchMovies_etSearch)
    EditText etSearch;
    @BindView(R.id.customDialogSearchMovies_rcvResult)
    RecyclerView rcvResult;

    Unbinder unbinder;
    private ListenerDialogSearchMovies listenerDialogSearchMovies;
    private SearchMoviesPresenter searchMoviesPresenter;
    private SearchMoviesAdapter searchMoviesAdapter;
    private ArrayList<DataListMovies> dataListMoviesArrayList = new ArrayList<>();

    public CustomDialogSearchMovies(ListenerDialogSearchMovies listenerDialogSearchMovies) {
        this.listenerDialogSearchMovies = listenerDialogSearchMovies;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.custom_dialog_search_movies, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Objects.requireNonNull(getDialog()).setCanceledOnTouchOutside(false);
        searchMoviesPresenter = new SearchMoviesPresenter();
        searchMoviesPresenter.setView(this);

        searchMoviesAdapter = new SearchMoviesAdapter(dataListMoviesArrayList);
        Utils.initRecyclerView(getContext(), rcvResult, searchMoviesAdapter, LinearLayoutManager.VERTICAL);
    }

    @OnClick({R.id.customDialogSearchMovies_tvOk, R.id.customDialogSearchMovies_tvDelete, R.id.customDialogSearchMovies_imgClose})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.customDialogSearchMovies_tvOk:
                searchMoviesPresenter.getSearchApiMovies(etSearch.getText().toString());
                break;
            case R.id.customDialogSearchMovies_tvDelete:
                clearData();
                break;
            default:
                dismiss();
                break;
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        Objects.requireNonNull(Objects.requireNonNull(getDialog()).getWindow()).setLayout(setWidthHeight() - 50,
                WindowManager.LayoutParams.MATCH_PARENT);
    }

    private int setWidthHeight() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        Objects.requireNonNull(getActivity()).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int width = displayMetrics.widthPixels;
        return width;
    }

    @Override
    public void sendSearchApiMovies(ArrayList<DataListMovies> dataListMoviesArrayList) {
        this.dataListMoviesArrayList.addAll(dataListMoviesArrayList);
        searchMoviesAdapter.notifyDataSetChanged();
    }

    @Override
    public void sendError(String messageError) {
        Toast.makeText(getContext(), messageError, Toast.LENGTH_SHORT).show();
    }

    private void clearData() {
        dataListMoviesArrayList.clear();
        searchMoviesAdapter.notifyDataSetChanged();
        etSearch.setText("");
    }
}
