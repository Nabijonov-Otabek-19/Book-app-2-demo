package com.example.exam2bookapp.ui.addBook;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

import com.example.exam2bookapp.R;
import com.example.exam2bookapp.model.BookData;
import com.example.exam2bookapp.ui.main.MainActivity;

public class AddBookActivity extends AppCompatActivity implements AddBookContract.View {

    private AddBookContract.Presenter presenter;

    private EditText bookTitle, bookAuthor, bookDesc;
    private AppCompatButton saveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_book);

        presenter = new AddBookPresenter(this);
        loadView();
    }

    private void loadView() {
        bookTitle = findViewById(R.id.edt_bookName);
        bookAuthor = findViewById(R.id.edt_bookAuthor);
        bookDesc = findViewById(R.id.edt_bookDesc);
        saveButton = findViewById(R.id.btn_save);

        saveButton.setOnClickListener(view -> {
            presenter.setBookToDB();
            presenter.clickSaveButton();
        });
    }

    @Override
    public void openMainScreen() {
        startActivity(new Intent(this, MainActivity.class));
        presenter.closeWindow();
    }

    @Override
    public void closeWindow() {
        onBackPressed();
    }

    @Override
    public BookData setBook() {
        String name = bookTitle.getText().toString().trim();
        String author = bookAuthor.getText().toString().trim();
        String desc = bookDesc.getText().toString().trim();

        return new BookData(name, author, desc);
    }
}