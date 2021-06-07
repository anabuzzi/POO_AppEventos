package br.senai.sc.eventos.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import br.senai.sc.eventos.database.entity.EventoEntity;
import br.senai.sc.eventos.database.entity.LocalEntity;
import br.senai.sc.eventos.modelo.Evento;
import br.senai.sc.eventos.modelo.Local;

public class LocalDAO {

    private final String SQL_LISTAR_TODOS = "SELECT * FROM " + LocalEntity.TABLE_NAME;
    private DBGateway dbGateway;

    public LocalDAO(Context context) { dbGateway = DBGateway.getInstance(context); }

    public boolean salvar(Local local) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(LocalEntity.COLUMN_NAME_NOME_LOCAL, local.getLocal());
        contentValues.put(LocalEntity.COLUMN_NAME_NOME_CIDADE, local.getCidade());
        contentValues.put(LocalEntity.COLUMN_NAME_NOME_BAIRRO, local.getBairro());
        contentValues.put(LocalEntity.COLUMN_NAME_CAPACIDADE_MAXIMA, local.getCapacidadeMaxima());
        if (local.getId() > 0) {
            return dbGateway.getDatabase().update(LocalEntity.TABLE_NAME,
                    contentValues,
                    LocalEntity._ID + "=?",
                    new String[]{String.valueOf(local.getId())}) > 0;
        }
        return dbGateway.getDatabase().insert(LocalEntity.TABLE_NAME,
                null, contentValues) > 0;
    }

    public List<Local> listar() {
        List<Local> locais = new ArrayList<>();
        Cursor cursor = dbGateway.getDatabase().rawQuery(SQL_LISTAR_TODOS, null);
        while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndex(LocalEntity._ID));
            String local = cursor.getString(cursor.getColumnIndex(LocalEntity.COLUMN_NAME_NOME_LOCAL));
            String cidade = cursor.getString(cursor.getColumnIndex(LocalEntity.COLUMN_NAME_NOME_CIDADE));
            String bairro = cursor.getString(cursor.getColumnIndex(LocalEntity.COLUMN_NAME_NOME_BAIRRO));
            int capacidadeMaxima  = cursor.getInt(cursor.getColumnIndex(LocalEntity.COLUMN_NAME_CAPACIDADE_MAXIMA));
            locais.add(new Local(id, local, cidade, bairro, capacidadeMaxima));
        }
        return locais;
    }

    public int excluir(Local local) {
        return dbGateway.getDatabase().delete(EventoEntity.TABLE_NAME, EventoEntity._ID + "=?", new String[]{String.valueOf(local.getId())});
    }
}
