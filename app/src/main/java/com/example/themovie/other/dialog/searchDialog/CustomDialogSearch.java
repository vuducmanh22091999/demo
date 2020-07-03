package com.example.themovie.other.dialog.searchDialog;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;


import com.example.themovie.R;
import com.example.themovie.adapter.DataListMovies;
import com.example.themovie.adapter.ShowMoviesApdater;
import com.example.themovie.base.BaseFragment;
import com.example.themovie.data.RoomMovies;
import com.example.themovie.data.database.MyRoomDatabase;
import com.example.themovie.interfaces.DAOMovies;
import com.example.themovie.interfaces.ListenerDialog;
import com.example.themovie.interfaces.ListenerMovies;
import com.example.themovie.interfaces.UpdateMoviesListener;
import com.example.themovie.other.Utils;

import com.example.themovie.other.dialog.CustomDialogUpdateMovies;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class CustomDialogSearch extends BaseFragment implements UpdateMoviesListener {
    @BindView(R.id.customDialogSearch_etSearch)
    EditText etSearch;
    @BindView(R.id.customDialogSearch_rcvResultSearch)
    RecyclerView rcvResultSearch;

    Unbinder unbinder;

    MyRoomDatabase myRoomDatabase;
    private ShowMoviesApdater showMoviesApdater;
    private List<RoomMovies> roomMoviesList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.custom_dialog_search, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initRoomDatabase();
        initAdapter();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        assert unbinder != null;
        unbinder.unbind();
    }

    @OnClick({R.id.customDialogSearch_imgSearch, R.id.customDialogSearch_tvUpdate, R.id.customDialogSearch_tvDelete})
    public void OnClick(View view) {
        switch (view.getId()) {
            case R.id.customDialogSearch_imgSearch:
//                findMoviesById(Long.parseLong(etSearch.getText().toString()));
                searchLikeId(Long.parseLong(etSearch.getText().toString()));
                break;
            case R.id.customDialogSearch_tvUpdate:
//                showDialogUpdate(Long.parseLong(etSearch.getText().toString()));
                break;
            default:
                deleteMoviesById(Long.parseLong(etSearch.getText().toString()));
                break;
        }
    }

    private void initRoomDatabase() {
        myRoomDatabase = Room.databaseBuilder(Objects.requireNonNull(getContext()), MyRoomDatabase.class, "mydbmovies")
                .allowMainThreadQueries()
                .build();
    }

    private void findMoviesById(long id) {
        DAOMovies daoMovies = myRoomDatabase.getDAOMovies();
        RoomMovies roomMovies = daoMovies.getRoomMoviesById(id);
        if (roomMoviesList.size() != 0)
            roomMoviesList.clear();
        if (roomMovies != null)
            roomMoviesList.add(roomMovies);
        showMoviesApdater.notifyDataSetChanged();
    }

    private void initAdapter() {
        roomMoviesList = new ArrayList<>();
        showMoviesApdater = new ShowMoviesApdater(roomMoviesList, new ListenerMovies() {
            @Override
            public void clickMovie(int index) {
                showDialogUpdate(roomMoviesList.get(index));
            }
        });
        Utils.initRecyclerView(getContext(), rcvResultSearch, showMoviesApdater, LinearLayoutManager.VERTICAL);
    }

    private void showDialogUpdate(RoomMovies roomMovies) {
//        DAOMovies daoMovies = myRoomDatabase.getDAOMovies();
//        RoomMovies roomMovies = daoMovies.getRoomMoviesById(id);


        CustomDialogUpdateMovies customDialogUpdateMovies = new CustomDialogUpdateMovies(this);
        customDialogUpdateMovies.setRoomMovies(roomMovies);


        assert getFragmentManager() != null;
        customDialogUpdateMovies.show(getFragmentManager(), CustomDialogUpdateMovies.class.getSimpleName());
    }

    @Override
    public void onClickUpdateMovies(RoomMovies roomMovies) {
        myRoomDatabase.getDAOMovies().updateMovies(roomMovies);
    }

    @Override
    public void onClickCancelUpdateMovies() {

    }

    private void searchLikeId(long id) {
        String search = "%" + id + "%";
        DAOMovies daoMovies = myRoomDatabase.getDAOMovies();
        List<RoomMovies> roomMoviesList = daoMovies.getLikeById(search);
        if (this.roomMoviesList.size() != 0)
            this.roomMoviesList.clear();
        if (roomMoviesList != null && roomMoviesList.size() != 0)
            this.roomMoviesList.addAll(roomMoviesList);
        showMoviesApdater.notifyDataSetChanged();
    }

    private void deleteMoviesById(long id) {
        DAOMovies daoMovies = myRoomDatabase.getDAOMovies();
        RoomMovies roomMovies = daoMovies.getRoomMoviesById(id);
        daoMovies.deleteMovies(roomMovies);
        searchLikeId(id);
    }
}
