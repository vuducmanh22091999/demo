package com.example.themovie.ui.detail;

import com.example.themovie.data.ListKeyword;

public interface DetailsMoviesContract {
    interface View {
        void sendKeyWordApi(ListKeyword listKeyword);
        void sendVideoRx(String string, int id);
        void sendError(String messageError);
    }

    interface Presenter {
        void getKeywordApi(int id);
        void getVideoRx(int id);
    }
}
