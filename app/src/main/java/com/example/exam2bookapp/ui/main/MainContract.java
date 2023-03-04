package com.example.exam2bookapp.ui.main;

import com.example.exam2bookapp.model.BookData;

import java.util.List;

public interface MainContract {


    interface View {
        void closeScreen();

        void setBooks();

        void openAddBookActivity();

        void openReadBookActivity(int i);
    }

    interface Presenter {
        void closeWindow();

        String getBookTitle();
        String getBookAuthor();
        String getBookDesc();

        void setBooks();

        void clickAddButton();

        void clickBookItem(int i);
    }
}
