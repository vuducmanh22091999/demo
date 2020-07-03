package com.example.themovie.ui.search;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.themovie.R;
import com.example.themovie.adapter.DataListActors;
import com.example.themovie.adapter.DataListMovies;
import com.example.themovie.adapter.SearchMoviesAdapter;
import com.example.themovie.api.HomeApi;
import com.example.themovie.base.BaseFragment;
import com.example.themovie.data.ListMovies;
import com.example.themovie.data.RetrofitClient;
import com.example.themovie.other.Utils;
import com.example.themovie.other.dialog.CustomDialogSearchMovies;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchMoviesFragment extends BaseFragment implements SearchMoviesContract.View {
    private ArrayList<DataListMovies> dataSearch = new ArrayList<>();
    private RetrofitClient retrofitClient;
    private SearchMoviesAdapter searchMoviesAdapter;
    private SearchMoviesPresenter searchMoviesPresenter;


    @BindView(R.id.fragSearchMovies_rcvResultSearch)
    RecyclerView rcvResultSearch;

    @BindView(R.id.fragSearchMovies_etSearch)
    EditText etSearch;

    @BindView(R.id.fragSearchMovies_imgSearch)
    ImageView imgSearch;

    @BindView(R.id.fragSearchMovies_imgDelete)
    ImageView imgDelete;

    Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search_movies, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @OnClick({R.id.fragSearchMovies_imgSearch, R.id.fragSearchMovies_imgDelete})
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.fragSearchMovies_imgSearch:
                searchMoviesApi(etSearch.getText().toString());
                break;
            case R.id.fragSearchMovies_imgDelete:
                clearDataSearch();
                break;
        }

    }

    private void searchMoviesApi(String query) {
        showLoading();
//        retrofitClient = new RetrofitClient();
//        HomeApi homeApi = retrofitClient.getClientRetrofit().create(HomeApi.class);
//        homeApi.searchMovies(getString(R.string.api_key_movie), query).enqueue(new Callback<ListMovies>() {
//            @Override
//            public void onResponse(Call<ListMovies> call, Response<ListMovies> response) {
//                if (response.isSuccessful()){
//                    dataSearch = response.body().getDataListMovies();
//                    initSearchAdapter();
//                    hideLoading();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<ListMovies> call, Throwable t) {
//                Log.d("error", t.getMessage());
//                hideLoading();
//            }
//        });
    }

    private void initSearchAdapter () {
        searchMoviesAdapter = new SearchMoviesAdapter(dataSearch);

        Utils.initRecyclerView(getContext(), rcvResultSearch, searchMoviesAdapter, LinearLayoutManager.VERTICAL);
    }

    private void clearDataSearch() {
        etSearch.setText("");
        dataSearch.clear();
        searchMoviesAdapter.notifyDataSetChanged();
    }

    @Override
    public void sendSearchApiMovies(ArrayList<DataListMovies> dataListMoviesArrayList) {
        dataSearch = dataListMoviesArrayList;
        initSearchAdapter();
        searchMoviesPresenter.clearDisposable();
        hideLoading();
    }

    @Override
    public void sendError(String messageError) {
        Toast.makeText(getContext(), messageError, Toast.LENGTH_SHORT).show();
    }
}
