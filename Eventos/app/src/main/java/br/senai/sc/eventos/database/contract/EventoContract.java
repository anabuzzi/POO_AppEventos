package br.senai.sc.eventos.database.contract;

import br.senai.sc.eventos.database.entity.EventoEntity;

public final class EventoContract {

    private EventoContract () {}

    public static final String criarTabela(){
        return "CREATE TABLE " + EventoEntity.TABLE_NAME + "(" +
                EventoEntity._ID + " INTEGER PRIMARY KEY," +
                EventoEntity.COLUM_NAME_NOME + " TEXT," +
                EventoEntity.COLUM_NAME_LOCAL + " TEXT," +
                EventoEntity.COLUM_NAME_DATA + " INTEGER)";
    }

    public static final String removerTabela(){

        return "DROP TABLE IF EXISTS" + EventoEntity.TABLE_NAME;
    }
}
