package br.senai.sc.eventos.modelo;

import java.io.Serializable;

public class Local implements Serializable {

    private int id;
    private String local;
    private String cidade;
    private String bairro;
    private int capacidadeMaxima;

    public Local(int id, String local, String cidade, String bairro, int capacidadeMaxima) {
        this.id = id;
        this.local = local;
        this.cidade = cidade;
        this.bairro = bairro;
        this.capacidadeMaxima = capacidadeMaxima;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public int getCapacidadeMaxima() {
        return capacidadeMaxima;
    }

    public void setCapacidadeMaxima(int capacidadeMaxima) {
        this.capacidadeMaxima = capacidadeMaxima;
    }

    @Override
    public String toString() {
        return local + cidade + bairro + capacidadeMaxima;
    }
}
