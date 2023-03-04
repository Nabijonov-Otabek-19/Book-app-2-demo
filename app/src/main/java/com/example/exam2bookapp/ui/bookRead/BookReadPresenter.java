package com.example.exam2bookapp.ui.bookRead;

public class BookReadPresenter implements BookReadContract.Presenter {

    private BookReadContract.View view;

    BookReadPresenter(BookReadContract.View view) {
        this.view = view;
    }


    @Override
    public void setBookData() {
        view.setBookData();
    }

    @Override
    public void clickBackButton() {
        view.closeScreen();
    }
}

