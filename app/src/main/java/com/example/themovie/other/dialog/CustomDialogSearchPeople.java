package com.example.themovie.other.dialog;

import android.os.Bundle;
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
import com.example.themovie.adapter.SearchPeopleAdapter;
import com.example.themovie.data.PeopleModel;
import com.example.themovie.interfaces.ListenerDialogSearchPeople;
import com.example.themovie.interfaces.ListenerPeople;
import com.example.themovie.other.Utils;
import com.example.themovie.ui.search.SearchPeopleContract;
import com.example.themovie.ui.search.SearchPeoplePresenter;

import java.util.ArrayList;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class CustomDialogSearchPeople extends DialogFragment implements SearchPeopleContract.View {
    @BindView(R.id.customDialogSearchPeople_etSearch)
    EditText etSearch;
    @BindView(R.id.customDialogSearchPeople_rcvResult)
    RecyclerView rcvResult;

    Unbinder unbinder;
    private ListenerDialogSearchPeople listenerDialogSearchPeople;
    private SearchPeoplePresenter searchPeoplePresenter;
    private SearchPeopleAdapter searchPeopleAdapter;
    private ArrayList<PeopleModel> peopleModelArrayList = new ArrayList<>();

    public CustomDialogSearchPeople(ListenerDialogSearchPeople listenerDialogSearchPeople) {
        this.listenerDialogSearchPeople = listenerDialogSearchPeople;
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
        Objects.requireNonNull(getDialog()).setCanceledOnTouchOutside(false);
        searchPeoplePresenter = new SearchPeoplePresenter();
        searchPeoplePresenter.setView(this);

        searchPeopleAdapter = new SearchPeopleAdapter(peopleModelArrayList, new ListenerPeople() {
            @Override
            public void clickPeople(int index) {

            }
        });
        Utils.initRecyclerView(getContext(), rcvResult, searchPeopleAdapter, LinearLayoutManager.VERTICAL);
    }

    @OnClick({R.id.customDialogSearchPeople_tvOk, R.id.customDialogSearchPeople_tvDelete, R.id.customDialogSearchPeople_imgClose})
    public void OnClick(View view) {
        switch (view.getId()) {
            case R.id.customDialogSearchPeople_tvOk:
                searchPeoplePresenter.getSearchApiPeople(etSearch.getText().toString());
                break;
            case R.id.customDialogSearchPeople_tvDelete:
                clearData();
                break;
            default:
                dismiss();
                break;
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        Objects.requireNonNull(Objects.requireNonNull(getDialog()).getWindow()).setLayout(setWidth() - 50,
                setHeight() - 50);
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


    @Override
    public void sendSearchApiPeople(ArrayList<PeopleModel> peopleModelArrayList) {
        this.peopleModelArrayList.addAll(peopleModelArrayList);
        searchPeopleAdapter.notifyDataSetChanged();
    }

    @Override
    public void sendError(String messageError) {
        Toast.makeText(getContext(), messageError, Toast.LENGTH_SHORT).show();
    }

    private void clearData() {
        peopleModelArrayList.clear();
        searchPeopleAdapter.notifyDataSetChanged();
        etSearch.setText("");
    }
}
