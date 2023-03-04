package com.example.exam2bookapp.ui.bookRead;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.exam2bookapp.R;

public class ReadBookActivity extends AppCompatActivity implements BookReadContract.View {

    private TextView title, author, desc;
    private BookReadContract.Presenter presenter;
    private ImageView imgBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_book);

        presenter = new BookReadPresenter(this);
        loadView();
    }

    private void loadView() {
        title = findViewById(R.id.txt_bookName);
        author = findViewById(R.id.txt_bookAuthor);
        desc = findViewById(R.id.txt_desc);
        imgBack = findViewById(R.id.img_back);

        presenter.setBookData();

        imgBack.setOnClickListener(view -> {
            presenter.clickBackButton();
        });

    }

    @Override
    public void setBookData() {
        Intent intent = getIntent();
        title.setText(intent.getStringExtra("title"));
        author.setText(intent.getStringExtra("author"));
        desc.setText(intent.getStringExtra("desc"));
    }

    @Override
    public void closeScreen() {
        onBackPressed();
    }
}