package com.example.exam2bookapp.ui.account;


import com.example.exam2bookapp.repository.UsersDataBase;

public class CreateAccPresenter implements CreateAccContract.Presenter {

    private CreateAccContract.View view;
    private UsersDataBase usersDataBase;

    CreateAccPresenter(CreateAccContract.View view) {
        this.view = view;
        usersDataBase = UsersDataBase.getInstance();
    }

    @Override
    public void showInfo(String info) {
        view.showToast(info);
    }

    @Override
    public void saveAccountToDB(String name, String password) {
        String n = name;
        String p = password;
        usersDataBase.setName(n);
        usersDataBase.setPassword(p);
    }

    @Override
    public boolean clickSubmitButton(String name, String pass, String confirm) {
        return view.checkDatas(name, pass, confirm);
    }

    @Override
    public void closeWindow() {
        view.closeScreen();
    }

    @Override
    public void openMainActivity() {
        view.openMainActivity();
    }
}
