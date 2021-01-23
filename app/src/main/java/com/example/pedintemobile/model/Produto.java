package com.example.pedintemobile.model;

import java.io.Serializable;

public class Produto implements Serializable {
    private int id;
    private String descricao;

    public Produto() {

    }

    public Produto(int id, String descricao) {
        this.id = id;
        this.descricao = descricao;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
