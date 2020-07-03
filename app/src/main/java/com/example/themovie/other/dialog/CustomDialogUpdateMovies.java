package com.example.themovie.other.dialog;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.themovie.R;
import com.example.themovie.data.RoomMovies;
import com.example.themovie.interfaces.ListenerDialog;
import com.example.themovie.interfaces.UpdateMoviesListener;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class CustomDialogUpdateMovies extends DialogFragment {
    @BindView(R.id.customDialogUpdateMovies_etId)
    EditText etId;
    @BindView(R.id.customDialogUpdateMovies_etNameMovies)
    EditText etNameMovies;

    Unbinder unbinder;

    private RoomMovies roomMovies;

    private UpdateMoviesListener updateMoviesListener;

    public CustomDialogUpdateMovies(UpdateMoviesListener updateMoviesListener) {
        this.updateMoviesListener = updateMoviesListener;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.custom_dialog_update_movies, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        etId.setText(String.valueOf(this.roomMovies.getIdMovies()));
        etNameMovies.setText(this.roomMovies.getTitleMovies());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        assert unbinder != null;
        unbinder.unbind();
    }

    @OnClick({R.id.customDialogUpdateMovies_tvOk, R.id.customDialogUpdateMovies_tvCancel})
    public void OnClick(View view) {
        switch (view.getId()) {
            case R.id.customDialogUpdateMovies_tvOk:
                roomMovies.setTitleMovies(etNameMovies.getText().toString());
                updateMoviesListener.onClickUpdateMovies(roomMovies);
                dismiss();
                break;
            default:
                dismiss();
                break;
        }
    }

    public void setRoomMovies(RoomMovies roomMovies){
        this.roomMovies = roomMovies;
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
