package br.senai.sc.eventos.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import br.senai.sc.eventos.database.entity.EventoEntity;
import br.senai.sc.eventos.database.entity.LocalEntity;
import br.senai.sc.eventos.modelo.Evento;
import br.senai.sc.eventos.modelo.Local;

public class EventoDAO {
    private final String SQL_LISTAR_TODOS = "SELECT * FROM " + EventoEntity.TABLE_NAME;
    private DBGateway dbGateway;

    public EventoDAO(Context context) {
        dbGateway = DBGateway.getInstance(context);
    }

    public boolean salvar(Evento evento) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(EventoEntity.COLUM_NAME_NOME, evento.getNome());
        contentValues.put(EventoEntity.COLUM_NAME_ID_LOCAL, evento.getLocal().getId());
        contentValues.put(EventoEntity.COLUM_NAME_DATA, evento.getData());
        if (evento.getId() > 0) {
            return dbGateway.getDatabase().update(EventoEntity.TABLE_NAME,
                    contentValues,
                    EventoEntity._ID + "=?",
                    new String[]{String.valueOf(evento.getId())}) > 0;
        }
        return dbGateway.getDatabase().insert(EventoEntity.TABLE_NAME,
                null, contentValues) > 0;
    }

    public List<Evento> listar() {

        List<Evento> eventos = new ArrayList<>();
        Cursor cursor = dbGateway.getDatabase().rawQuery(SQL_LISTAR_TODOS, null);

        while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndex(EventoEntity._ID));
            String nome = cursor.getString(cursor.getColumnIndex(EventoEntity.COLUM_NAME_NOME));
            String data = cursor.getString((cursor.getColumnIndex(EventoEntity.COLUM_NAME_DATA)));
            int idLocal = cursor.getInt(cursor.getColumnIndex(EventoEntity.COLUM_NAME_ID_LOCAL));
            String nomeLocal = cursor.getString(cursor.getColumnIndex(LocalEntity.COLUMN_NAME_NOME_LOCAL));
            String nomeCidade = cursor.getString(cursor.getColumnIndex(LocalEntity.COLUMN_NAME_NOME_CIDADE));
            String nomeBairro = cursor.getString(cursor.getColumnIndex(LocalEntity.COLUMN_NAME_NOME_BAIRRO));
            int capacidadeMaxima = Integer.parseInt(cursor.getString(cursor.getColumnIndex(LocalEntity.COLUMN_NAME_CAPACIDADE_MAXIMA)));
            Local local = new Local(idLocal, nomeLocal, nomeCidade, nomeBairro, capacidadeMaxima);
            eventos.add(new Evento(id, nome, data, local));
        }
        cursor.close();
        return eventos;
    }

    public int excluir(Evento evento) {
        return dbGateway.getDatabase().delete(EventoEntity.TABLE_NAME, EventoEntity._ID + "=?", new String[]{String.valueOf(evento.getId())});
    }
}
