package com.example.pedintemobile.service;

import com.example.pedintemobile.json.ProdutoJSON;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ProdutoService {

    @GET("/products")
    Call<List<ProdutoJSON>> getAllProductos();

}