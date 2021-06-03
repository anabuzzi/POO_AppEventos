package br.senai.sc.eventos.database.entity;

import android.provider.BaseColumns;

public class LocalEntity implements BaseColumns {

    private LocalEntity(){}

    public static final String TABLE_NAME  = "local";
    public static final String COLUMN_NAME_NOME_LOCAL  = "nomeLocal";
    public static final String COLUMN_NAME_NOME_CIDADE  = "nomeCidade";
    public static final String COLUMN_NAME_NOME_BAIRRO  = "nomeBairro";
    public static final String COLUMN_NAME_CAPACIDADE_MAXIMA = "capacidadeMaxima";

}
