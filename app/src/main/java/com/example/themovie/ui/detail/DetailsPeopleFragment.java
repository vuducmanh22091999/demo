package com.example.themovie.ui.detail;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.themovie.R;
import com.example.themovie.adapter.DataListMovies;
import com.example.themovie.adapter.DetailsPeopleAdapter;
import com.example.themovie.base.BaseFragment;
import com.example.themovie.data.PeopleModel;
import com.example.themovie.other.Utils;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class DetailsPeopleFragment extends BaseFragment {
    private DetailMoviesFragment detailMoviesFragment = new DetailMoviesFragment();
    private DataListMovies dataListMovies;

    @BindView(R.id.fragDetailsPeople_rcvDetailPeople)
    RecyclerView rcvDetailPeople;

    @BindView(R.id.fragDetailsPeople_imgProfilePeople)
    ImageView imgProfilePeople;

    @BindView(R.id.fragDetailsPeople_tvNamePeople)
    TextView tvNamePeople;

    Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_details_people, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getDataDetailsPeople();
    }


    private void getDataDetailsPeople() {
        Bundle bundle = getArguments();
        assert bundle != null;
        PeopleModel peopleModel = bundle.getParcelable("know_for");

        DetailsPeopleAdapter detailsPeopleAdapter = new DetailsPeopleAdapter(peopleModel.getKnown_for(), index ->
                sendDataDetailMovies(peopleModel.getKnown_for().get(index)));
        Glide.with(Objects.requireNonNull(getContext())).load(peopleModel.getProfile_path()).into(imgProfilePeople);
        tvNamePeople.setText(peopleModel.getName());
        Utils.initRecyclerView(getContext(), rcvDetailPeople, detailsPeopleAdapter, LinearLayoutManager.VERTICAL);
    }

    private void sendDataDetailMovies(DataListMovies dataListMovies) {
        Bundle bundle = new Bundle();
        bundle.putParcelable("detailMovies", dataListMovies);
        detailMoviesFragment.setArguments(bundle);
        addFragment(detailMoviesFragment, R.id.actMain_flContentScreens);
    }
}
