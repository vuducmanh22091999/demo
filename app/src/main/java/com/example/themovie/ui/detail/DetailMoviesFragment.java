package com.example.themovie.ui.detail;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.themovie.R;
import com.example.themovie.adapter.DataListMovies;
import com.example.themovie.adapter.ListKeywordAdapter;
import com.example.themovie.adapter.ListMovieAdapter;
import com.example.themovie.api.HomeApi;
import com.example.themovie.base.BaseActivity;
import com.example.themovie.base.BaseFragment;
import com.example.themovie.data.Constant;
import com.example.themovie.data.KeywordModel;
import com.example.themovie.data.ListKeyword;
import com.example.themovie.data.ListResultVideo;
import com.example.themovie.data.RetrofitClient;
import com.example.themovie.interfaces.ListenerMovies;
import com.example.themovie.other.Utils;
import com.example.themovie.ui.search.SearchMoviesFragment;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class DetailMoviesFragment extends BaseFragment implements DetailsMoviesContract.View {
    private ArrayList<DataListMovies> dataListMoviesArrayList = new ArrayList<>();
    private ListMovieAdapter listMovieAdapter;
    private SearchMoviesFragment searchMoviesFragment = new SearchMoviesFragment();

    private ArrayList<KeywordModel> keywordModelArrayList = new ArrayList<>();
    private ListKeywordAdapter listKeywordAdapter;

    @BindView(R.id.fragDetailMovies_rcvKeyword)
    RecyclerView rcvKeyword;

    @BindView(R.id.fragDetailMovies_imgFavoriteMovies)
    ImageView imgFavoriteMovies;

    @BindView(R.id.fragDetailMovies_rtbEvaluate)
    RatingBar rtbEvaluate;

    @BindView(R.id.fragDetailMovies_imgPosterDetailMovies)
    ImageView imgPosterDetailMovies;

    @BindView(R.id.fragDetailMovies_tvTitleMovies)
    TextView tvTitleMovies;

    @BindView(R.id.fragDetailMovies_tvOriginalTitle)
    TextView tvOriginalTitle;

    @BindView(R.id.fragDetailMovies_tvNumberId)
    TextView tvNumberId;

    @BindView(R.id.fragDetailMovies_tvDate)
    TextView tvDate;

    @BindView(R.id.fragDetailMovies_tvPointMetascore)
    TextView tvPointMetascore;

    @BindView(R.id.fragDetailMovies_tvPointEvaluate)
    TextView tvPointEvaluate;

    @BindView(R.id.fragDetailMovies_tvNumberVoteCount)
    TextView tvNumberVoteCount;

    @BindView(R.id.fragDetailMovies_tvAboutMovies)
    TextView tvAboutMovies;

    Unbinder unbinder;
    private String url;
    private static final int RECOVERY_REQUEST = 1;

    private Retrofit retrofit;
    private DetailsMoviesPresenter detailsMoviesPresenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail_movies, container, false);

        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initAdapter();
//        Utils.initRecyclerView(getContext(), grvKeyword, listMovieAdapter, LinearLayoutManager.HORIZONTAL);
        getDataFragment();
    }

    private void getDataFragment() {
        assert getArguments() != null;
        DataListMovies dataListMovies = getArguments().getParcelable("detailMovies");
        assert dataListMovies != null;
        Glide.with(getContext()).load(dataListMovies.getPoster_path()).into(imgPosterDetailMovies);
        tvTitleMovies.setText(dataListMovies.getTitle());
        tvOriginalTitle.setText(dataListMovies.getOriginal_title());
        tvNumberId.setText(String.valueOf(dataListMovies.getId()));
        tvDate.setText(dataListMovies.getRelease_date());
        tvPointMetascore.setText(String.valueOf(dataListMovies.getVote_average()));
        tvPointEvaluate.setText(String.valueOf(dataListMovies.getVote_average()));
        tvNumberVoteCount.setText(String.valueOf(dataListMovies.getVote_count()));
        tvAboutMovies.setText(dataListMovies.getOverview());

        detailsMoviesPresenter = new DetailsMoviesPresenter();
        detailsMoviesPresenter.setView(this);

        showLoading();

        detailsMoviesPresenter.getVideoRx(dataListMovies.getId());
    }


    @OnClick(R.id.fragDetailMovies_imgBack)
    public void onClickButtonBack() {
//        assert getActivity() != null;
        Objects.requireNonNull(getActivity()).onBackPressed();
    }

    @OnClick(R.id.fragDetailMovies_imgSearchMovies)
    void onClickSearchMovies() {
        addFragment(searchMoviesFragment, R.id.actMain_flContentScreens);
    }

    @OnClick(R.id.fragDetailMovies_imgFavoriteMovies)
    void onClickFavoriteMovies() {
        boolean adult;
        assert getArguments() != null;
        adult = getArguments().getBoolean("adult");
        adult = !adult;
        imgFavoriteMovies.setSelected(adult);
    }

    private void initAdapter() {
        listMovieAdapter = new ListMovieAdapter(dataListMoviesArrayList, new ListenerMovies() {
            @Override
            public void clickMovie(int index) {
                Toast.makeText(getContext(), String.valueOf(index), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initAdapterKeyword() {
        listKeywordAdapter = new ListKeywordAdapter(keywordModelArrayList);
        Utils.initGridView(getContext(), rcvKeyword, listKeywordAdapter, 3);
    }

    @Override
    public void sendKeyWordApi(ListKeyword listKeyword) {
        keywordModelArrayList = listKeyword.getKeywordModelArrayList();
        initAdapterKeyword();
        detailsMoviesPresenter.clearDisposable();
        hideLoading();
    }

    @Override
    public void sendVideoRx(String string, int id) {
        Toast.makeText(getContext(), string, Toast.LENGTH_SHORT).show();
        detailsMoviesPresenter.getKeywordApi(id);
    }

    @Override
    public void sendError(String messageError) {
        Toast.makeText(getContext(), messageError, Toast.LENGTH_SHORT).show();
    }
}

