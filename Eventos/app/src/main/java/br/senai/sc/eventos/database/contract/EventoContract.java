package br.senai.sc.eventos.database.contract;

import br.senai.sc.eventos.database.entity.EventoEntity;
import br.senai.sc.eventos.database.entity.LocalEntity;

public final class EventoContract {

    private EventoContract () {}

    public static final String criarTabela(){
        return "CREATE TABLE " + EventoEntity.TABLE_NAME + " (" +
                EventoEntity._ID + " INTEGER PRIMARY KEY," +
                EventoEntity.COLUM_NAME_NOME + " TEXT," +
                EventoEntity.COLUM_NAME_DATA + " TEXT," +
                EventoEntity.COLUM_NAME_ID_LOCAL + " INTEGER," +
                "FOREIGN KEY (" + EventoEntity.COLUM_NAME_ID_LOCAL + ") REFERENCES " +
                LocalEntity.TABLE_NAME + "(" + LocalEntity._ID + "))";

    }

    public static final String removerTabela(){
        return "DROP TABLE IF EXISTS " + EventoEntity.TABLE_NAME;
    }
}
