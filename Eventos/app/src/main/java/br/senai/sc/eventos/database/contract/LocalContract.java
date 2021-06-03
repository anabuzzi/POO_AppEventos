package br.senai.sc.eventos.database.contract;

import br.senai.sc.eventos.database.entity.LocalEntity;

public class LocalContract {

    private LocalContract(){}

    public static final String criarTabela() {
        return "CREATE TABLE " + LocalEntity.TABLE_NAME + " (" +
                LocalEntity._ID + " INTEGER PRIMARY KEY," +
                LocalEntity.COLUMN_NAME_NOME_LOCAL + " TEXT," +
                LocalEntity.COLUMN_NAME_NOME_CIDADE + " TEXT," +
                LocalEntity.COLUMN_NAME_NOME_BAIRRO + " TEXT," +
                LocalEntity.COLUMN_NAME_CAPACIDADE_MAXIMA + " INTEGER)";
    }
    public static final String removerTabela() {
        return "DROP TABLE IF EXISTS " + LocalEntity.TABLE_NAME;
    }
}
