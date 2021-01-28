package com.example.pedintemobile.service;

import com.example.pedintemobile.json.ProdutoJSON;
import com.example.pedintemobile.model.Produto;

import java.util.List;

public interface ProdutoCallback {
    public void onSuccess();
    public List<Produto> onSuccess(List<ProdutoJSON> result);
    public void onFailure(Throwable t);
}
