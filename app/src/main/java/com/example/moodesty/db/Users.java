package com.example.moodesty.db;

import android.provider.BaseColumns;

public final class Users {
    private Users() { }

    public final static String ID = "_id";

    public static class UsersHistory implements BaseColumns {

        public final static String TABLE_NAME = "users";
        public final static String COLUMN1 = "login";
        public final static String COLUMN2 = "password";
    }

    public final static String DB_CREATE = "CREATE TABLE " + UsersHistory.TABLE_NAME + "(" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + UsersHistory.COLUMN1 + " TEXT NOT NULL," + UsersHistory.COLUMN2 + " TEXT);";
    public static final String DB_DELETE = "DROP TABLE IF EXISTS " + UsersHistory.TABLE_NAME;
}