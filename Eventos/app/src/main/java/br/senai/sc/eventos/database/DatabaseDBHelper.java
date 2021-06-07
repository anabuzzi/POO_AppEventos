package br.senai.sc.eventos.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import br.senai.sc.eventos.database.contract.EventoContract;
import br.senai.sc.eventos.database.contract.LocalContract;

public class DatabaseDBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "db.evento";
    public static final int DATABASE_VERSION = 24;

    public DatabaseDBHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(LocalContract.criarTabela());
        db.execSQL(EventoContract.criarTabela());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(EventoContract.removerTabela());
        db.execSQL(LocalContract.removerTabela());
        db.execSQL(EventoContract.criarTabela());
        db.execSQL(LocalContract.criarTabela());
    }
}
