package br.senai.sc.eventos.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseDBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "db.evento";
    public static final int DATABASE_VERSION = 1;

    public DatabaseDBHelper(@Nullable Context context, int version) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
