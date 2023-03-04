package com.example.exam2bookapp.ui.account;

public interface CreateAccContract {

    interface View {
        boolean checkDatas(String name, String pass, String confirm);

        void closeScreen();

        void showToast(String message);

        void openMainActivity();
    }

    interface Presenter {

        void showInfo(String info);

        void saveAccountToDB(String name, String password);

        boolean clickSubmitButton(String name, String pass, String confirm);

        void closeWindow();

        void openMainActivity();
    }
}