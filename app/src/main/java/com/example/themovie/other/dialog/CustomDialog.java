package com.example.themovie.other.dialog;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.bumptech.glide.Glide;
import com.example.themovie.R;
import com.example.themovie.adapter.DataListMovies;
import com.example.themovie.interfaces.ListenerDialog;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class CustomDialog extends DialogFragment {
    @BindView(R.id.customDialogMenu_imgPosterMovies)
    ImageView imgPosterMovies;
    @BindView(R.id.customDialogMenu_tvTitleMovies)
    TextView tvTitleMovies;
    @BindView(R.id.customDialogMenu_tvVoteAverage)
    TextView tvVoteAverage;
    @BindView(R.id.customDialogMenu_tvVoteCount)
    TextView tvVoteCount;
    @BindView(R.id.customDialogMenu_tvPopularity)
    TextView tPopularity;
    @BindView(R.id.customDialogMenu_tvReleaseDate)
    TextView tvReleaseDate;

    Unbinder unbinder;
    DataListMovies dataListMovies;
    private ListenerDialog listenerDialog;

    public CustomDialog(ListenerDialog listenerDialog) {
        this.listenerDialog = listenerDialog;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.custom_dialog_menu, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Objects.requireNonNull(getDialog()).setCanceledOnTouchOutside(false);
        Glide.with(getContext()).load(dataListMovies.getPoster_path()).into(imgPosterMovies);
        tvTitleMovies.setText(dataListMovies.getTitle());
        tvVoteAverage.setText(getString(R.string.vote_average_format, String.valueOf(dataListMovies.getVote_average())));
        tvVoteCount.setText(getString(R.string.vote_count_format, dataListMovies.getVote_count()));
        tPopularity.setText(getString(R.string.popularity_format, String.valueOf(dataListMovies.getPopularity())));
        tvReleaseDate.setText(getString(R.string.release_date_format, dataListMovies.getRelease_date()));
    }

    public void initDialog(DataListMovies dataListMovies) {
        this.dataListMovies = dataListMovies;
    }

    @OnClick({R.id.customDialogMenu_imgClose, R.id.customDialogMenu_tvOk, R.id.customDialogMenu_tvShowName})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.customDialogMenu_imgClose:
                listenerDialog.onClickClose();
                break;
            case R.id.customDialogMenu_tvOk:
                dismiss();
                listenerDialog.onClickOk(dataListMovies);
                break;
            case R.id.customDialogMenu_tvShowName:
                listenerDialog.onClickShowName(dataListMovies.getTitle());
                break;
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        Objects.requireNonNull(Objects.requireNonNull(getDialog()).getWindow()).setLayout(setWidthHeight() - 50,
                WindowManager.LayoutParams.WRAP_CONTENT);
    }

    private int setWidthHeight() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        Objects.requireNonNull(getActivity()).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int width = displayMetrics.widthPixels;
        return width;
    }
}
