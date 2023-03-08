package com.example.exam2bookapp.ui.splash;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.exam2bookapp.R;
import com.example.exam2bookapp.repository.UsersDataBase;
import com.example.exam2bookapp.ui.account.CreateAccountActivity;
import com.example.exam2bookapp.ui.login.LoginActivity;
import com.example.exam2bookapp.ui.main.MainActivity;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        int splashTime = 2000;

        UsersDataBase usersDataBase = UsersDataBase.getInstance();
        usersDataBase.getAccessForData(this);

        new Handler().postDelayed(() -> {
            if (usersDataBase.getUsers().isEmpty() && usersDataBase.getCurrUser().isEmpty()) {
                startActivity(new Intent(this, CreateAccountActivity.class));
                onBackPressed();

            } else if (!usersDataBase.getUsers().isEmpty() && usersDataBase.getCurrUser().isEmpty()) {
                startActivity(new Intent(this, LoginActivity.class));
                onBackPressed();

            } else {
                startActivity(new Intent(this, MainActivity.class));
                onBackPressed();
            }
        }, splashTime);
    }
}