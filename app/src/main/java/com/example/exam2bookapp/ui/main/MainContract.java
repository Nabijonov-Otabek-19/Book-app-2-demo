package com.example.exam2bookapp.ui.main;

public interface MainContract {


    interface View {
        void closeScreen();

        void setBooks();
        void openLoginActivity();

        void openAddBookActivity();

        void openReadBookActivity(int i);
    }

    interface Presenter {
        void closeWindow();
        String getCurrUser();
        void logOut();

        String getAllUsers();
        String getBookTitle();
        String getBookAuthor();
        String getBookDesc();

        void setBooks();

        void clickAddButton();

        void clickBookItem(int i);
    }
}
