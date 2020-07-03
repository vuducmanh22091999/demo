package com.example.themovie.other.dialog;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.themovie.R;
import com.example.themovie.adapter.ShowMoviesApdater;
import com.example.themovie.data.RoomMovies;
import com.example.themovie.interfaces.ListenerMovies;
import com.example.themovie.other.Utils;

import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class ShowMoviesDialog extends DialogFragment {
    private List<RoomMovies> roomMoviesList;
    @BindView(R.id.customDialogShowMovies_rcvShowMovies)
    RecyclerView rcvShowMovies;

    Unbinder unbinder;

    public ShowMoviesDialog(List<RoomMovies> roomMoviesList) {
        this.roomMoviesList = roomMoviesList;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.custom_dialog_show_movies, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        showMovies();
    }

    @Override
    public void onStart() {
        super.onStart();
        Objects.requireNonNull(Objects.requireNonNull(getDialog()).getWindow()).setLayout(setWidthHeight() - 50,
                WindowManager.LayoutParams.MATCH_PARENT);
    }

    private void showMovies() {
        ShowMoviesApdater showMoviesApdater = new ShowMoviesApdater(roomMoviesList, new ListenerMovies() {
            @Override
            public void clickMovie(int index) {

            }
        });
        Utils.initRecyclerView(getContext(), rcvShowMovies, showMoviesApdater, LinearLayoutManager.VERTICAL);
    }

    private int setWidthHeight() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        Objects.requireNonNull(getActivity()).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int width = displayMetrics.widthPixels;
        return width;
    }
}
