package com.example.exam2bookapp.ui.main;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.exam2bookapp.R;
import com.example.exam2bookapp.ui.addBook.AddBookActivity;
import com.example.exam2bookapp.ui.bookRead.ReadBookActivity;
import com.example.exam2bookapp.ui.login.LoginActivity;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity implements MainContract.View {

    private FrameLayout noBook, yesBook;
    private LinearLayout container;
    private LayoutInflater inflater;
    private ImageView imgLogout;
    private TextView userName, userName2;

    private String[] titles;
    private String[] authors;
    private String[] descs;

    private String[] users;
    private String currUser;

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
        ImageButton addButton = findViewById(R.id.img_add);
        imgLogout = findViewById(R.id.img_logOut);
        userName = findViewById(R.id.txt_userName);
        userName2 = findViewById(R.id.txt_userName2);

        container = findViewById(R.id.container);
        inflater = LayoutInflater.from(this);

        loadBooks();

        presenter.setBooks();

        imgLogout.setOnClickListener(view -> {
            new AlertDialog.Builder(this)
                    .setCancelable(false)
                    .setTitle("Exit").setMessage("Do you want to quit")
                    .setPositiveButton("Yes", (dialogInterface, i) -> {
                        presenter.logOut();
                        presenter.closeWindow();
                    })
                    .setNegativeButton("No", ((dialogInterface, i) -> dialogInterface.cancel())).create().show();
        });

        addButton.setOnClickListener(view -> presenter.clickAddButton());
    }

    private void loadBooks() {
        users = presenter.getAllUsers().split("#");
        currUser = presenter.getCurrUser();
        int startIndex = -1, endIndex = -1;

        for (int i = 0; i < users.length; i++) {
            if (users[i].equals(currUser)) {
                startIndex = Integer.parseInt(users[i + 1]);
                if (i + 3 <= users.length) endIndex = Integer.parseInt(users[i + 3]);
            }
        }
        // 1.user - 0/3, 2.user - 3/4/7, 3.user - 7/0/7
        if (endIndex != -1) {
            String[] title = presenter.getBookTitle().trim().split("#");
            String[] author = presenter.getBookAuthor().trim().split("#");
            String[] desc = presenter.getBookDesc().trim().split("#");

            titles = new String[endIndex - startIndex];
            authors = new String[endIndex - startIndex];
            descs = new String[endIndex - startIndex];

            int j = 0;
            for (int i = startIndex; i < endIndex; i++) {
                titles[j] = title[i];
                authors[j] = author[i];
                descs[j++] = desc[i];
            }
        } else if (startIndex >= 0 && endIndex == -1 && presenter.getBookTitle().isEmpty()) {
            titles = null;
            authors = null;
            descs = null;

        } else if (endIndex == -1 && startIndex >= 0 && !(presenter.getBookTitle().isEmpty())) {
            String[] title = presenter.getBookTitle().trim().split("#");
            String[] author = presenter.getBookAuthor().trim().split("#");
            String[] desc = presenter.getBookDesc().trim().split("#");

            titles = new String[title.length - startIndex];
            authors = new String[title.length - startIndex];
            descs = new String[title.length - startIndex];

            int j = 0;
            for (int i = startIndex; i < title.length; i++) {
                titles[j] = title[i];
                authors[j] = author[i];
                descs[j++] = desc[i];
            }

        } else {
            titles = null;
            authors = null;
            descs = null;
        }
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
            userName.setText(presenter.getCurrUser());

        } else {
            noBook.setVisibility(View.INVISIBLE);
            yesBook.setVisibility(View.VISIBLE);
            userName2.setText(presenter.getCurrUser());
            imgLogout.setVisibility(View.VISIBLE);

            Log.d("AAA", Arrays.toString(titles));

            for (int i = 0; i < titles.length; i++) {
                View view = inflater.inflate(R.layout.item_book, container, false);
                view.<TextView>findViewById(R.id.txt_book_name).setText(titles[i]);
                view.<TextView>findViewById(R.id.txt_book_author).setText(authors[i]);

                int finalI = i;
                view.setOnClickListener(v -> presenter.clickBookItem(finalI));
                container.addView(view);
            }
        }
    }

    @Override
    public void openLoginActivity() {
        startActivity(new Intent(this, LoginActivity.class));
        onBackPressed();
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
        intent.putExtra("desc", descs[finalI]);
        startActivity(intent);
    }
}