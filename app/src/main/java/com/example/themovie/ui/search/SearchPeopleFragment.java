package com.example.themovie.ui.search;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.themovie.R;
import com.example.themovie.adapter.DataListMovies;
import com.example.themovie.adapter.SearchPeopleAdapter;
import com.example.themovie.api.HomeApi;
import com.example.themovie.base.BaseFragment;
import com.example.themovie.data.ListPeople;
import com.example.themovie.data.PeopleModel;
import com.example.themovie.data.RetrofitClient;
import com.example.themovie.interfaces.ListenerPeople;
import com.example.themovie.other.Utils;
import com.example.themovie.ui.detail.DetailsPeopleFragment;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchPeopleFragment extends BaseFragment implements SearchPeopleContract.View {
    private ArrayList<PeopleModel> peopleModelArrayList = new ArrayList<>();
    private SearchPeopleAdapter searchPeopleAdapter;
    private SearchPeoplePresenter searchPeoplePresenter;
    private ListenerPeople listenerPeople;
    private RetrofitClient retrofitClient;

    @BindView(R.id.fragSearchPeople_rcvResultSearch)
    RecyclerView rcvResultSearch;

    @BindView(R.id.fragSearchPeople_etSearch)
    EditText etSearch;

    Unbinder unbinder;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search_people, container, false);

        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @OnClick({R.id.fragSearchPeople_imgSearch, R.id.fragSearchPeople_imgDelete})
    public void onClick(View view){
        switch(view.getId()){
            case R.id.fragSearchPeople_imgSearch:
                searchApiPeople(etSearch.getText().toString());
                break;
            case R.id.fragSearchPeople_imgDelete:
                clearDataSearch();
                break;
        }
    }

    private void searchApiPeople(String query) {
        showLoading();
//        retrofitClient = new RetrofitClient();
//        HomeApi homeApi = retrofitClient.getClientRetrofit().create(HomeApi.class);
//        homeApi.searchPeople(getString(R.string.api_key_movie), query).enqueue(new Callback<ListPeople>() {
//            @Override
//            public void onResponse(Call<ListPeople> call, Response<ListPeople> response) {
//                if (response.isSuccessful()){
//                    peopleModelArrayList = response.body().getPeopleModelArrayList();
//                    initSearchPeopleAdapter();
//                    hideLoading();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<ListPeople> call, Throwable t) {
//
//            }
//        });
    }

    private void initSearchPeopleAdapter() {
        searchPeopleAdapter = new SearchPeopleAdapter(peopleModelArrayList, new ListenerPeople() {
            @Override
            public void clickPeople(int index) {
                sendDataPeople(peopleModelArrayList.get(index));
            }
        });
        Utils.initRecyclerView(getContext(), rcvResultSearch, searchPeopleAdapter, LinearLayoutManager.VERTICAL);
    }

    private void clearDataSearch() {
        etSearch.setText("");
        peopleModelArrayList.clear();
        searchPeopleAdapter.notifyDataSetChanged();
    }

    private void sendDataPeople(PeopleModel peopleModel) {
        Bundle bundle = new Bundle();
        bundle.putParcelable("know_for", peopleModel);
        DetailsPeopleFragment detailsPeopleFragment = new DetailsPeopleFragment();
        detailsPeopleFragment.setArguments(bundle);
        addFragment(detailsPeopleFragment, R.id.actMain_flContentScreens);
    }


    @Override
    public void sendSearchApiPeople(ArrayList<PeopleModel> peopleModelArrayList) {
        peopleModelArrayList = peopleModelArrayList;
        initSearchPeopleAdapter();
        searchPeoplePresenter.clearDisposable();
        hideLoading();
    }

    @Override
    public void sendError(String messageError) {
        Toast.makeText(getContext(), messageError, Toast.LENGTH_SHORT).show();
    }
}
