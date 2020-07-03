package com.example.themovie.ui.home;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.themovie.R;
import com.example.themovie.adapter.CategoriesAdapter;
import com.example.themovie.adapter.DataCategories;
import com.example.themovie.adapter.DataListMovies;
import com.example.themovie.adapter.ListMovieAdapter;
import com.example.themovie.base.BaseFragment;
import com.example.themovie.data.AuthenticationModel;
import com.example.themovie.data.Constant;
import com.example.themovie.data.CoreData;
import com.example.themovie.data.ListMovies;
import com.example.themovie.data.ListPopular;
import com.example.themovie.data.RetrofitClient;
import com.example.themovie.data.RoomMovies;
import com.example.themovie.data.SessionModel;
import com.example.themovie.data.database.MyRoomDatabase;
import com.example.themovie.interfaces.DAOMovies;
import com.example.themovie.interfaces.ListenerCategories;
import com.example.themovie.interfaces.ListenerDialog;
import com.example.themovie.interfaces.ListenerDialogSearchMovies;
import com.example.themovie.interfaces.ListenerDialogSearchPeople;
import com.example.themovie.other.Utils;
import com.example.themovie.other.dialog.CustomDialog;
import com.example.themovie.other.dialog.CustomDialogSearchMovies;
import com.example.themovie.other.dialog.CustomDialogSearchPeople;
import com.example.themovie.other.dialog.ShowMoviesDialog;
import com.example.themovie.other.dialog.searchDialog.CustomDialogSearch;
import com.example.themovie.other.dialog.searchDialog.SearchDialog;
import com.example.themovie.ui.detail.DetailMoviesFragment;
import com.example.themovie.ui.search.SearchMoviesFragment;
import com.example.themovie.ui.search.SearchPeopleFragment;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class HomeFragment extends BaseFragment implements ListenerDialog, HomeContract.View, ListenerDialogSearchPeople, ListenerDialogSearchMovies {
    @BindView(R.id.fragHome_rcvCategories)
    RecyclerView rcvCategories;

    @BindView(R.id.fragHome_rcvMyListMovies)
    RecyclerView rcvMyListMovies;

    @BindView(R.id.fragHome_rcvPopularOnNetflix)
    RecyclerView rcvPopularOnNetflix;

    @BindView(R.id.fragHome_imgPoster)
    ImageView imgPoster;

    private ArrayList<DataCategories> dataCategoriesArrayList = new ArrayList<>();
    private ArrayList<DataListMovies> dataListMoviesArrayList = new ArrayList<>();
    private ArrayList<DataListMovies> dataListPopularMoviesArrayList = new ArrayList<>();

    private RetrofitClient retrofitClient;


    private DetailMoviesFragment detailMoviesFragment = new DetailMoviesFragment();
    private SearchMoviesFragment searchMoviesFragment = new SearchMoviesFragment();
    private SearchPeopleFragment searchPeopleFragment = new SearchPeopleFragment();

    Unbinder unbinder;
    int checkedItem = 0;
    private CustomDialog customDialog;
    private CustomDialogSearchMovies customDialogSearchMovies;
    private CustomDialogSearchPeople customDialogSearchPeople;
    private HomePresenter homePresenter;
    private MyRoomDatabase myRoomDatabase;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initCategories();
        loadImage();
        showLoading();
        homePresenter = new HomePresenter();
        homePresenter.setView(this);
        homePresenter.getListPopular();
        homePresenter.getListMovies(Constant.API_KEY);
        homePresenter.getAuthentication(Constant.API_KEY);

        initRoomDatabase();


//        getListPopular();
//        getListMovies();
    }

    @OnClick(R.id.fragHome_imgMenu)
    public void onClickMenu() {
        dialogSearch();
    }

    private void dialogSearch() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(getContext());
        alertDialog.setTitle("Select Search");

        String[] selectSearch = {"Search Movies", "Search People"};

        alertDialog.setSingleChoiceItems(selectSearch, checkedItem, (dialogInterface, index) -> {
            checkedItem = index;
        });

        alertDialog.setPositiveButton("OK", (dialogInterface, index) -> {
//            switch (checkedItem) {
//                case 0:
//                    customDialogSearchMovies = new CustomDialogSearchMovies(this);
//                    assert getFragmentManager() != null;
//                    customDialogSearchMovies.show(getFragmentManager(), CustomDialogSearchPeople.class.getSimpleName());
////                    addFragment(searchMoviesFragment, R.id.actMain_flContentScreens);
//                    break;
//                case 1:
//                    customDialogSearchPeople = new CustomDialogSearchPeople(this);
//                    assert getFragmentManager() != null;
//                    customDialogSearchPeople.show(getFragmentManager(), CustomDialogSearchPeople.class.getSimpleName());
////                    addFragment(searchPeopleFragment, R.id.actMain_flContentScreens);
//                    break;
//            }

            SearchDialog searchDialog = new SearchDialog(selectSearch[checkedItem]);
            assert getFragmentManager() != null;
            searchDialog.show(getFragmentManager(), SearchDialog.class.getSimpleName());


        });

        alertDialog.setNegativeButton("Cancel", (dialogInterface, index) -> {

        });

        AlertDialog dialog = alertDialog.create();
        dialog.show();
    }


    private void initPopularOnNetflix() {
        ListMovieAdapter listPopularMoviesAdapter = new ListMovieAdapter(dataListPopularMoviesArrayList,
                index -> {
                    sendDataFragment(index, false);
                });
        Utils.initRecyclerView(getContext(), rcvPopularOnNetflix, listPopularMoviesAdapter, LinearLayoutManager.HORIZONTAL);
    }

    private void initListMovie() {
        ListMovieAdapter listMovieAdapter = new ListMovieAdapter(dataListMoviesArrayList,
                index -> {
                    sendDataFragment(index, true);
                });
        Utils.initRecyclerView(getContext(), rcvMyListMovies, listMovieAdapter, LinearLayoutManager.HORIZONTAL);
    }

    private void initCategories() {
        dataCategoriesArrayList = CoreData.dataListCategories();
        CategoriesAdapter categoriesAdapter = new CategoriesAdapter(dataCategoriesArrayList, new ListenerCategories() {
            @Override
            public void clickCategories(int index) {
                switch (index) {
                    case 0:
                        addMovies();
                        break;
                    case 1:
//                        updateMovies(1L);
                        searchMovies();
                        break;
                    default:
                        showMovies();
                        break;
                }

            }
        });
        Utils.initRecyclerView(getContext(), rcvCategories, categoriesAdapter, LinearLayoutManager.HORIZONTAL);
    }

    private void loadImage() {
        RequestOptions requestOptions = new RequestOptions();
        requestOptions = requestOptions.transforms(new CenterCrop(), new RoundedCorners(20));
        Glide.with(Objects.requireNonNull(getContext())).load(R.drawable.img_poster).apply(requestOptions).into(imgPoster);
    }

    private void sendDataFragment(int index, boolean isType) {
//        Bundle bundle = new Bundle();
//
//
//        bundle.putParcelable("detailMovies", isType ? dataListMoviesArrayList.get(index) : dataListPopularMoviesArrayList.get(index));
//
//        detailMoviesFragment.setArguments(bundle);
//        addFragment(detailMoviesFragment, R.id.actMain_flContentScreens);

        customDialog = new CustomDialog(this);
        customDialog.initDialog(isType ? dataListMoviesArrayList.get(index) : dataListPopularMoviesArrayList.get(index));
        customDialog.show(Objects.requireNonNull(getFragmentManager()), CustomDialog.class.getSimpleName());

    }

//    private void getListPopular() {
//        showLoading();
//        retrofitClient = new RetrofitClient();
//        HomeApi homeApi = retrofitClient.getClientRetrofit().create(HomeApi.class);
//        homeApi.getListPopular(getString(R.string.api_key_movie)).enqueue(new Callback<ListPopular>() {
//            @Override
//            public void onResponse(Call<ListPopular> call, Response<ListPopular> response) {
//                if (response.isSuccessful()) {
//                    parseListPopular(response.body());
//                }
//            }
//
//            @Override
//            public void onFailure(Call<ListPopular> call, Throwable t) {
//                Log.d("demo", t.getMessage());
//            }
//        });
//    }

//    private void getListMovies() {
//        retrofitClient = new RetrofitClient();
//        HomeApi homeApi = retrofitClient.getClientRetrofit().create(HomeApi.class);
//        homeApi.getListMovies(getString(R.string.api_key_movie)).enqueue(new Callback<ListMovies>() {
//            @Override
//            public void onResponse(Call<ListMovies> call, Response<ListMovies> response) {
//                if (response.isSuccessful()) {
//                    parseListMovies(response.body());
//                    hideLoading();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<ListMovies> call, Throwable t) {
//
//            }
//        });
//    }

    private void parseListPopular(ListPopular listPopular) {
        dataListPopularMoviesArrayList = listPopular.getPopularModelList();
        initPopularOnNetflix();
    }

    private void parseListMovies(ListMovies listMovies) {
        dataListMoviesArrayList = listMovies.getDataListMovies();
        initListMovie();
    }

    @Override
    public void onClickClose() {
//        customDialog.dismiss();
    }

    @Override
    public void onClickOk() {

    }

    @Override
    public void onClickDelete() {

    }

    @Override
    public void onClickOk(DataListMovies dataListMovies) {
        Bundle bundle = new Bundle();
        bundle.putParcelable("detailMovies", dataListMovies);
        detailMoviesFragment.setArguments(bundle);
        addFragment(detailMoviesFragment, R.id.actMain_flContentScreens);
    }

    @Override

    public void onClickShowName(String name) {
        Toast.makeText(getContext(), name, Toast.LENGTH_SHORT).show();
    }


    @Override
    public void sendListMovies(ListMovies listMovies) {
        parseListMovies(listMovies);
        hideLoading();
    }

    @Override
    public void sendListPopular(ListPopular listPopular) {
        parseListPopular(listPopular);
        hideLoading();
    }

    @Override
    public void sendAuthentication(AuthenticationModel authenticationModel) {
        Toast.makeText(getContext(), authenticationModel.getRequest_token(), Toast.LENGTH_SHORT).show();
//        HashMap<String, String> map = new HashMap<>();
//        map.put("request_token", authenticationModel.getRequest_token());
//        homePresenter.getSessionId(map, getString(R.string.api_key_movie));
    }

    @Override
    public void sendSession(SessionModel sessionModel) {
        Toast.makeText(getContext(), sessionModel.getStatus_message(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void sendError(String messageError) {
//        Toast.makeText(getContext(), messageError, Toast.LENGTH_SHORT).show();
    }

    private void initRoomDatabase() {
        myRoomDatabase = Room.databaseBuilder(Objects.requireNonNull(getContext()), MyRoomDatabase.class, "mydbmovies")
                .allowMainThreadQueries()
                .build();
    }

    private void addMovies() {
        DAOMovies daoMovies = myRoomDatabase.getDAOMovies();
        RoomMovies roomMovies = new RoomMovies();
        roomMovies.setTitleMovies("ABC");
        roomMovies.setIdMovies(System.currentTimeMillis());

        daoMovies.insertMovies(roomMovies);

        Toast.makeText(getContext(), "Add Successful!!", Toast.LENGTH_SHORT).show();
    }

    private void updateMovies(long idMovies) {
        DAOMovies daoMovies = myRoomDatabase.getDAOMovies();
        RoomMovies roomMovies = daoMovies.getRoomMoviesById(idMovies);
        roomMovies.setTitleMovies("XYZ");
        daoMovies.updateMovies(roomMovies);

        Toast.makeText(getContext(), "Update Successful!!", Toast.LENGTH_SHORT).show();
    }

    private void deleteMovies(long idMovies) {

    }

    private void showMovies() {
        List<RoomMovies> roomMoviesList = myRoomDatabase.getDAOMovies().getRoomMovies();
        ShowMoviesDialog showMoviesDialog = new ShowMoviesDialog(roomMoviesList);
        assert getFragmentManager() != null;
        showMoviesDialog.show(getFragmentManager(), ShowMoviesDialog.class.getSimpleName());






        Toast.makeText(getContext(), String.valueOf(roomMoviesList.size()), Toast.LENGTH_SHORT).show();
    }

    private void searchMovies() {
        CustomDialogSearch customDialogSearch = new CustomDialogSearch();
        addFragment(new CustomDialogSearch(), R.id.actMain_flContentScreens);
    }
}
