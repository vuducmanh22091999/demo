package com.example.themovie.other.dialog.searchDialog;

import android.os.Bundle;
import android.provider.Contacts;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.themovie.R;
import com.example.themovie.adapter.DataListMovies;
import com.example.themovie.adapter.ListMovieAdapter;
import com.example.themovie.adapter.SearchPeopleAdapter;
import com.example.themovie.data.ListPeople;
import com.example.themovie.data.PeopleModel;
import com.example.themovie.interfaces.ListenerMovies;
import com.example.themovie.interfaces.ListenerPeople;
import com.example.themovie.other.Utils;

import java.util.ArrayList;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class SearchDialog extends DialogFragment implements SearchContract.View {
    @BindView(R.id.customDialogSearchPeople_tvTitle)
    TextView tvTitle;
    @BindView(R.id.customDialogSearchPeople_etSearch)
    EditText etSearch;
    @BindView(R.id.customDialogSearchPeople_rcvResult)
    RecyclerView rcvResult;

    Unbinder unbinder;

    private String param;
    private SearchPresenter searchPresenter;
    private ArrayList<DataListMovies> dataListMoviesArrayList = new ArrayList<>();
    private ArrayList<PeopleModel> peopleModelArrayList = new ArrayList<>();
    private ListMovieAdapter listMovieAdapter;
    private SearchPeopleAdapter searchPeopleAdapter;

    public SearchDialog(String param) {
        this.param = param;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.custom_dialog_search_people, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tvTitle.setText(param);

        searchPresenter = new SearchPresenter();
        searchPresenter.setView(this);

        if (param.equals("Search Movies"))
            initAdapterMovies();
        else
            initAdapterPeople();
    }

    @Override
    public void onStart() {
        super.onStart();
        Objects.requireNonNull(Objects.requireNonNull(getDialog()).getWindow()).setLayout(setWidth() - 50,
                setHeight() - 50);
    }

    @OnClick({R.id.customDialogSearchPeople_tvOk, R.id.customDialogSearchPeople_tvDelete, R.id.customDialogSearchPeople_imgClose})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.customDialogSearchPeople_tvOk:
                handleCallApi(param);
                break;
            case R.id.customDialogSearchPeople_tvDelete:
                clearData();
                break;
            default:
                dismiss();
                break;
        }
    }

    private int setWidth() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        Objects.requireNonNull(getActivity()).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int width = displayMetrics.widthPixels;
        return width;
    }

    private int setHeight() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        Objects.requireNonNull(getActivity()).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int height = displayMetrics.heightPixels;
        return height;
    }

    private void initAdapterMovies() {
        listMovieAdapter = new ListMovieAdapter(dataListMoviesArrayList, new ListenerMovies() {
            @Override
            public void clickMovie(int index) {

            }
        });
        Utils.initRecyclerView(getContext(), rcvResult, listMovieAdapter, LinearLayoutManager.VERTICAL);
    }

    private void initAdapterPeople() {
        searchPeopleAdapter = new SearchPeopleAdapter(peopleModelArrayList, new ListenerPeople() {
            @Override
            public void clickPeople(int index) {

            }
        });
        Utils.initRecyclerView(getContext(), rcvResult, searchPeopleAdapter, LinearLayoutManager.VERTICAL);
    }

    private void handleCallApi(String param) {
        if (param.equals("Search Movies"))
            searchPresenter.handleSearchMovies(etSearch.getText().toString());

        else
            searchPresenter.handleSearchPeople(etSearch.getText().toString());
    }

    private void clearData() {
        if (!dataListMoviesArrayList.isEmpty()) {
            dataListMoviesArrayList.clear();
            listMovieAdapter.notifyDataSetChanged();
        }

        if (!peopleModelArrayList.isEmpty()) {
            peopleModelArrayList.clear();
            searchPeopleAdapter.notifyDataSetChanged();
        }
        etSearch.setText("");
    }

    @Override
    public void getSearchMovies(ArrayList<DataListMovies> dataListMoviesArrayList) {
        this.dataListMoviesArrayList.addAll(dataListMoviesArrayList);
        listMovieAdapter.notifyDataSetChanged();
    }

    @Override
    public void getError(String messageError) {
        Toast.makeText(getContext(), messageError, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void getSearchPeople(ArrayList<PeopleModel> peopleModelArrayList) {
        this.peopleModelArrayList.addAll(peopleModelArrayList);
        searchPeopleAdapter.notifyDataSetChanged();
    }
}
