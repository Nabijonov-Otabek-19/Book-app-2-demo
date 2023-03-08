package com.example.exam2bookapp.ui.login;

public interface LoginContract {

    interface Model {
    }

    interface View {
        void openCreateWindow();
        void openMainWindow();
        void showToast(String message);
    }

    interface Presenter {
        void openCreateActivity();
        void setCurrUser(String name);
        void showInfo(String info);
        boolean hasUser(String login, String pass);
        void openMainActivity();
    }
}
