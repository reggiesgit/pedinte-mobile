package com.example.pedintemobile.json;

import com.example.pedintemobile.model.Produto;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class ProdutoJSON {

    @SerializedName("id")
    private Integer id;

    @SerializedName("description")
    private String descricao;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

}