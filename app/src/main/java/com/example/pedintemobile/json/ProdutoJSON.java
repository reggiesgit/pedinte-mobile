package com.example.pedintemobile.json;

import android.util.Log;

import com.example.pedintemobile.model.Cliente;
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

    public static List<Produto> map(List<ProdutoJSON> json) {
        List<Produto> response = new ArrayList<>();
        for (ProdutoJSON each : json) {
            response.add(map(each));
        }
        return response;
    }

    public static ProdutoJSON map(Produto produto) {
        ProdutoJSON response = new ProdutoJSON();
        if (produto != null) {
            response.setId(produto.getId());
            response.setDescricao(produto.getDescricao());
        }
        return response;
    }

    public static Produto map(ProdutoJSON json) {
        Produto produto = new Produto();
        if (json != null) {
            produto.setId(json.getId());
            produto.setDescricao(json.getDescricao());
        }
        return produto;
    }

}