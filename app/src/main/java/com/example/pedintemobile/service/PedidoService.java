package com.example.pedintemobile.service;

import com.example.pedintemobile.json.ClienteJSON;
import com.example.pedintemobile.json.PedidoJSON;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface PedidoService {


    @GET("orders/")
    Call<List<PedidoJSON>> findAll();

    @GET("orders/{id}")
    Call<PedidoJSON> findById();

    @POST("orders/")
    Call<PedidoJSON> salvarPedido(@Body PedidoJSON json);

    @PUT("orders/")
    Call<PedidoJSON> atualizarPedido(@Body PedidoJSON json);

    @DELETE("orders/{id}")
    Call<PedidoJSON> removerPedido(@Path("id") int id);
}
