package com.example.exam2bookapp.ui.addBook;

import com.example.exam2bookapp.repository.UsersDataBase;

public class AddBookPresenter implements AddBookContract.Presenter {

    private AddBookContract.View view;
    private UsersDataBase usersDataBase;

    AddBookPresenter(AddBookContract.View view) {
        this.view = view;
        usersDataBase = UsersDataBase.getInstance();
    }


    @Override
    public void clickSaveButton() {
        view.openMainScreen();
    }

    @Override
    public void setBookToDB() {
        usersDataBase.setBook(view.setBook().getName());
        usersDataBase.setAuthor(view.setBook().getAuthor());
        usersDataBase.setDesc(view.setBook().getDescription());
    }

    @Override
    public void closeWindow() {
        view.closeWindow();
    }
}
