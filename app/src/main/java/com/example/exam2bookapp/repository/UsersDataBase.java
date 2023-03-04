package com.example.exam2bookapp.repository;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.exam2bookapp.model.BookData;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class UsersDataBase {

    private static UsersDataBase usersDataBase;

    public static final String SHARED_PREF = "shared_pref";

    public static final String BOOK = "book";
    public static final String AUTHOR = "author";
    public static final String DESC = "desc";

    public static final String NAME = "name";
    public static final String PASSWORD = "password";


    private SharedPreferences pref;
    private SharedPreferences.Editor editor;

    private UsersDataBase() {
    }

    public void getAccessForData(Context context) {
        pref = context.getSharedPreferences(SHARED_PREF, Context.MODE_PRIVATE);
        editor = pref.edit();
    }

    public static UsersDataBase getInstance() {
        if (usersDataBase == null) usersDataBase = new UsersDataBase();
        return usersDataBase;
    }

    public void setName(String name) {
        editor.putString(NAME, name).apply();
    }

    public void setPassword(String password) {
        editor.putString(PASSWORD, password).apply();
    }


    public void setBook(String bookTitle) {
        StringBuilder str;
        if (getBook().length() == 0) str = new StringBuilder();
        else str = new StringBuilder(getBook());

        str.append(bookTitle).append("#");
        editor.putString(BOOK, str.toString().trim()).apply();
    }

    public String getBook() {
        return pref.getString(BOOK, "");
    }

    public void setAuthor(String bookAuthor) {
        StringBuilder str;
        if (getAuthor().length() == 0) str = new StringBuilder();
        else str = new StringBuilder(getAuthor());

        str.append(bookAuthor).append("#");
        editor.putString(AUTHOR, str.toString().trim()).apply();
    }

    public String getAuthor() {
        return pref.getString(AUTHOR, "");
    }

    public void setDesc(String bookDesc) {
        StringBuilder str;
        if (getDesc().length() == 0) str = new StringBuilder();
        else str = new StringBuilder(getDesc());

        str.append(bookDesc).append("#");
        editor.putString(DESC, str.toString().trim()).apply();
    }

    public String getDesc() {
        return pref.getString(DESC, "");
    }
}