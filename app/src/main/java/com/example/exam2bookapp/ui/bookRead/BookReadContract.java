package com.example.exam2bookapp.ui.bookRead;

public interface BookReadContract {

    interface View {
        void setBookData();

        void closeScreen();
    }

    interface Presenter {
        void setBookData();

        void clickBackButton();
    }
}
