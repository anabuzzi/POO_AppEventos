package br.senai.sc.eventos.database.entity;

import android.provider.BaseColumns;

public final class EventoEntity implements BaseColumns {

    private EventoEntity() {}

    public static final String TABLE_NAME = "eventos";
    public static final String COLUM_NAME_NOME = "nome";
    public static final int COLUM_NAME_DATA = Integer.parseInt("data");
    public static final String COLUM_NAME_LOCAL = "local";
}
