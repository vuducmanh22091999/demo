package com.example.themovie.base;

import androidx.fragment.app.Fragment;


import com.example.themovie.ui.main.MainActivity;


public class BaseFragment extends Fragment {
    public void addFragment (Fragment fragment, int viewId) {
        if (getActivity() instanceof BaseActivity){
            ((BaseActivity) getActivity()).addFragment(fragment, viewId);
        }
    }

    public void showLoading() {
        if (getActivity() instanceof MainActivity){
            ((MainActivity) getActivity()).showLoading();
        }
    }

    public void hideLoading() {
        if (getActivity() instanceof MainActivity){
            ((MainActivity) getActivity()).hideLoading();
        }
    }
}
