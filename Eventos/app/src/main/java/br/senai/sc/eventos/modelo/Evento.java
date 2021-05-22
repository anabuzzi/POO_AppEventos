package br.senai.sc.eventos.modelo;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Evento implements Serializable {

    private int id;
    private String name;
    private LocalDate date;
    private String location;


    public Evento (int id, String name, LocalDate date, String location) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.location = location;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Evento (String name, LocalDate date, String location) {
        this.name = name;
        this.date = date;
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setData(String data) {

        String format = "dd/MM/yyyy";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        this.date = LocalDate.parse(data, formatter);
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return name + " - " + date + " - " + location ;
    }
}

