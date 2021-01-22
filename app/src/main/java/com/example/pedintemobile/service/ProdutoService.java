package com.example.pedintemobile.service;

import com.example.pedintemobile.json.ClienteJSON;
import com.example.pedintemobile.json.ProdutoJSON;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ProdutoService {

    @GET("/products")
    Call<List<ProdutoJSON>> findAll();

    @GET("products/{id}")
    Call<ProdutoJSON> findById();

    @POST("products/")
    Call<ProdutoJSON> salvarProduto(@Body ProdutoJSON json);

    @PUT("products/")
    Call<ProdutoJSON> atualizarProduto(@Body ProdutoJSON json);

    @DELETE("products/{id}")
    Call<ProdutoJSON> removerProduto(@Path("id") int id);

}