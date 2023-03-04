package com.example.exam2bookapp.ui.addBook;

import android.view.Window;

import com.example.exam2bookapp.model.BookData;

public interface AddBookContract {

    interface View {
        void openMainScreen();
        void closeWindow();

        BookData setBook();
    }

    interface Presenter {
        void clickSaveButton();

        void setBookToDB();

        void closeWindow();
    }
}
