package com.example.exam2bookapp.ui.main;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.UserHandle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.exam2bookapp.R;
import com.example.exam2bookapp.model.BookData;
import com.example.exam2bookapp.repository.UsersDataBase;
import com.example.exam2bookapp.ui.addBook.AddBookActivity;
import com.example.exam2bookapp.ui.bookRead.ReadBookActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements MainContract.View {

    private FrameLayout noBook, yesBook;
    private LinearLayout container;
    private LayoutInflater inflater;
    private ImageButton addButton;
    private ImageView imgLogout;

    private String[] titles;
    private String[] authors;
    private String[] desc;

    private MainContract.Presenter presenter;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        presenter = new MainPresenter(this);
        loadView();

    }

    private void loadView() {
        noBook = findViewById(R.id.frame_noBook);
        yesBook = findViewById(R.id.frame_yesBook);
        addButton = findViewById(R.id.img_add);
        imgLogout = findViewById(R.id.img_logOut);

        container = findViewById(R.id.container);
        inflater = LayoutInflater.from(this);

        if (!presenter.getBookTitle().equals("")) {
            titles = presenter.getBookTitle().trim().split("#");
            authors = presenter.getBookAuthor().trim().split("#");
            desc = presenter.getBookDesc().trim().split("#");
        }

        presenter.setBooks();

        imgLogout.setOnClickListener(view -> {
            new AlertDialog.Builder(this)
                    .setCancelable(false)
                    .setTitle("Exit").setMessage("Do you want to quit")
                    .setPositiveButton("Yes", (dialogInterface, i) -> {
                        presenter.closeWindow();
                    })
                    .setNegativeButton("No", ((dialogInterface, i) -> {

                    })).create().show();
        });

        addButton.setOnClickListener(view -> {
            presenter.clickAddButton();
        });
    }

    @Override
    public void closeScreen() {
        onBackPressed();
    }

    @Override
    public void setBooks() {
        if (titles == null) {
            noBook.setVisibility(View.VISIBLE);
            yesBook.setVisibility(View.INVISIBLE);
        } else {
            noBook.setVisibility(View.INVISIBLE);
            yesBook.setVisibility(View.VISIBLE);

            for (int i = 0; i < titles.length; i++) {
                View view = inflater.inflate(R.layout.item_book, container, false);
                view.<TextView>findViewById(R.id.txt_book_name).setText(titles[i]);
                view.<TextView>findViewById(R.id.txt_book_author).setText(authors[i]);

                int finalI = i;
                view.setOnClickListener(v -> {
                    presenter.clickBookItem(finalI);
                });
                container.addView(view);
            }
        }
    }

    @Override
    public void openAddBookActivity() {
        Intent intent = new Intent(MainActivity.this, AddBookActivity.class);
        startActivity(intent);
        presenter.closeWindow();
    }

    @Override
    public void openReadBookActivity(int finalI) {
        Intent intent = new Intent(this, ReadBookActivity.class);
        intent.putExtra("title", titles[finalI]);
        intent.putExtra("author", authors[finalI]);
        intent.putExtra("desc", desc[finalI]);
        startActivity(intent);
    }
}