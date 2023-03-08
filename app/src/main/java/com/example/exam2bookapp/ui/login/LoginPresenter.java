package com.example.exam2bookapp.ui.login;

import android.content.Context;

import com.example.exam2bookapp.repository.UsersDataBase;

public class LoginPresenter implements LoginContract.Presenter {

    private LoginContract.View view;
    private UsersDataBase usersDataBase;
    private String[] users;
    private String[] passwords;

    LoginPresenter(LoginContract.View view) {
        this.view = view;
        usersDataBase = UsersDataBase.getInstance();
        usersDataBase.getAccessForData((Context) view);
    }


    @Override
    public void openCreateActivity() {
        view.openCreateWindow();
    }

    @Override
    public void setCurrUser(String name) {
        usersDataBase.setCurrUser(name);
    }

    @Override
    public void showInfo(String info) {
        view.showToast(info);
    }

    @Override
    public boolean hasUser(String login, String pass) {
        boolean trueUser = false;
        boolean truePass = false;
        users = usersDataBase.getUsers().split("#");
        passwords = usersDataBase.getPasswords().split("#");

        for (int i = 0; i < users.length; i += 2) {
            if (users[i].equals(login)) {
                trueUser = true;
                break;
            }
        }
        for (String password : passwords) {
            if (password.equals(pass)) {
                truePass = true;
                break;
            }
        }
        return truePass && trueUser;
    }

    @Override
    public void openMainActivity() {
        view.openMainWindow();
    }
}
