package com.example.exam2bookapp.ui.login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.exam2bookapp.R;
import com.example.exam2bookapp.ui.account.CreateAccountActivity;
import com.example.exam2bookapp.ui.main.MainActivity;

public class LoginActivity extends AppCompatActivity implements LoginContract.View {

    private LoginPresenter presenter;

    private EditText login, password;
    private AppCompatButton btnSignIn;
    private TextView createAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        presenter = new LoginPresenter(this);
        loadView();

    }

    private void loadView() {
        login = findViewById(R.id.edt_login_login);
        password = findViewById(R.id.edt_login_password);
        btnSignIn = findViewById(R.id.btn_signIn);
        createAccount = findViewById(R.id.txt_create_account);

        btnSignIn.setOnClickListener(view -> {
            String name = login.getText().toString().trim();
            String pass = password.getText().toString().trim();
            if (presenter.hasUser(name, pass)) {
                presenter.setCurrUser(name);
                presenter.openMainActivity();
            } else {
                presenter.showInfo("Username or password is incorrect");
            }
        });

        createAccount.setOnClickListener(view -> {
            presenter.openCreateActivity();
        });


    }

    @Override
    public void openCreateWindow() {
        startActivity(new Intent(this, CreateAccountActivity.class));
        onBackPressed();
    }

    @Override
    public void openMainWindow() {
        startActivity(new Intent(this, MainActivity.class));
        onBackPressed();
    }

    @Override
    public void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}