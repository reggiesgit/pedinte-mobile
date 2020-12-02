package com.example.pedintemobile.json;

import com.example.pedintemobile.model.Produto;

import java.util.ArrayList;
import java.util.List;

public class ProdutoJSON {

    private int id;
    private String descricao;

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

    public static ProdutoJSON map(List<Produto> produtos) {
        ProdutoJSON response = new ProdutoJSON();
        System.out.println("Mapeando resposta do CORE: Produto para JSON.");
        List<ProdutoJSON> result = new ArrayList<>();
        for(Produto each : produtos) {
            ProdutoJSON json = new ProdutoJSON();
            json.setId(each.getId());
            json.setDescricao(each.getDescricao());
            result.add(json);
        }
        return response;
    }

    public static ProdutoJSON map(Produto produto) {
        ProdutoJSON response = new ProdutoJSON();
        System.out.println("Mapeando resposta do CORE: Protudo para JSON.");
        ProdutoJSON json = new ProdutoJSON();
        json.setId(produto.getId());
        json.setDescricao(produto.getDescricao());
        return response;
    }

    public static Produto map(ProdutoJSON json) {
        System.out.println("Mapeando requisição do CLIENTE: JSON para Produto.");
        Produto produto = new Produto();
        produto.setId(json.getId());
        produto.setDescricao(json.getDescricao());
        return produto;
    }
}
