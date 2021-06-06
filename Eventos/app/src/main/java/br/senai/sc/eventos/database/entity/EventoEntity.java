package br.senai.sc.eventos.database.entity;

import android.provider.BaseColumns;

public final class EventoEntity implements BaseColumns {

    private EventoEntity() {}

    public static final String TABLE_NAME = "evento";
    public static final String COLUM_NAME_NOME = "nome";
    public static final String COLUM_NAME_DATA = "data";
    public static final String COLUM_NAME_ID_LOCAL = "idlocal";
}
