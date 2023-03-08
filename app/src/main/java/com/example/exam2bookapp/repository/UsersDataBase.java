package com.example.exam2bookapp.repository;

import android.content.Context;
import android.content.SharedPreferences;

public class UsersDataBase {

    private static UsersDataBase usersDataBase;

    public static final String SHARED_PREF = "shared_pref";

    public static final String BOOK = "book";
    public static final String AUTHOR = "author";
    public static final String DESC = "desc";

    public static final String USERS = "name";
    public static final String PASSWORDS = "password";
    public static final String START_INDEX = "start_index";

    public static final String CURR_USER = "curr_user";


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

    public void setCurrUser(String user) {
        editor.putString(CURR_USER, user).apply();
    }

    public String getCurrUser() {
        return pref.getString(CURR_USER, "");
    }

    public void setUsers(String user) {
        StringBuilder str;
        if (getBook().length() == 0) str = new StringBuilder();
        else str = new StringBuilder(getUsers());

        // Yangi User qo'shganda uni startIndex ni ham o'ziga qo'shib qo'yadi
        str.append(user).append("#").append(getStartIndex()).append("#");
        editor.putString(USERS, str.toString().trim()).apply();

        // yangi user qo'shilsa, o'sha user oynasi ochiladi
        setCurrUser(user);
    }

    public String getUsers() {
        return pref.getString(USERS, "");
    }

    public void setPasswords(String password) {
        StringBuilder str;
        if (getBook().length() == 0) str = new StringBuilder();
        else str = new StringBuilder(getPasswords());

        str.append(password).append("#");
        editor.putString(PASSWORDS, str.toString().trim()).apply();
    }

    public String getPasswords() {
        return pref.getString(PASSWORDS, "");
    }

    public void setStartIndex(int index) {
        editor.putInt(START_INDEX, index).apply();
    }

    public int getStartIndex() {
        return pref.getInt(START_INDEX, 0);
    }


    public void setBook(String bookTitle) {
        StringBuilder str;
        if (getBook().length() == 0) str = new StringBuilder();
        else str = new StringBuilder(getBook());

        // kitob qo'shganda startIndex oshadi
        setStartIndex(getStartIndex() + 1);

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