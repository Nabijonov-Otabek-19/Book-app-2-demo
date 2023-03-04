package com.example.exam2bookapp.ui.account;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import com.example.exam2bookapp.R;
import com.example.exam2bookapp.ui.main.MainActivity;

public class CreateAccountActivity extends AppCompatActivity implements CreateAccContract.View {

    private EditText login, password, confirmPassword;
    private AppCompatButton submitButton;

    private CreateAccContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        presenter = new CreateAccPresenter(this);
        loadView();

    }

    private void loadView() {
        login = findViewById(R.id.edt_crt_login);
        password = findViewById(R.id.edt_crt_password);
        confirmPassword = findViewById(R.id.edt_crt_confirmPassword);
        submitButton = findViewById(R.id.btn_submit);

        submitButton.setOnClickListener(view -> {

            String name = login.getText().toString().trim();
            String pass = password.getText().toString().trim();
            String confirm = confirmPassword.getText().toString().trim();

            if (presenter.clickSubmitButton(name, pass, confirm)) {
                //presenter.saveAccountToDB(name, pass);
                presenter.openMainActivity();

            }else presenter.showInfo("Something went wrong");
        });
    }

    @Override
    public boolean checkDatas(String name, String pass, String confirm) {
        if (name.isEmpty() && pass.isEmpty() && confirm.isEmpty()) {
            presenter.showInfo("Create a new account");
            return false;

        } else if (!pass.equals(confirm)) {
            presenter.showInfo("Password must be similar");
            return false;

        } else return true;
    }

    @Override
    public void closeScreen() {
        onBackPressed();
    }

    @Override
    public void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void openMainActivity() {
        startActivity(new Intent(CreateAccountActivity.this, MainActivity.class));
        presenter.closeWindow();
    }
}