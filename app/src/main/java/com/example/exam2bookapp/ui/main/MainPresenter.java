package com.example.exam2bookapp.ui.main;

import android.content.Context;

import com.example.exam2bookapp.model.BookData;
import com.example.exam2bookapp.repository.UsersDataBase;

import java.util.ArrayList;
import java.util.List;

public class MainPresenter implements MainContract.Presenter {

    private MainContract.View view;
    private UsersDataBase usersDataBase;

    public MainPresenter(MainContract.View view) {
        this.view = view;
        usersDataBase = UsersDataBase.getInstance();
        usersDataBase.getAccessForData((Context) view);
    }


    @Override
    public void closeWindow() {
        view.closeScreen();
    }

    @Override
    public String getBookTitle() {
        return usersDataBase.getBook();
    }

    @Override
    public String getBookAuthor() {
        return usersDataBase.getAuthor();
    }

    @Override
    public String getBookDesc() {
        return usersDataBase.getDesc();
    }

    @Override
    public void setBooks() {
        view.setBooks();
    }

    @Override
    public void clickAddButton() {
        view.openAddBookActivity();
    }

    @Override
    public void clickBookItem(int i) {
        view.openReadBookActivity(i);
    }
}
