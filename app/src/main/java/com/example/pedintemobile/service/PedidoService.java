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


    @GET("pedido/")
    Call<List<PedidoJSON>> findAll();

    @GET("pedido/{id}")
    Call<PedidoJSON> findById();

    @POST("pedido/")
    Call<PedidoJSON> salvarPedido(@Body PedidoJSON json);

    @PUT("pedido/")
    Call<PedidoJSON> atualizarPedido(@Body PedidoJSON json);

    @DELETE("pedido/{id}")
    Call<PedidoJSON> removerPedido(@Path("id") int id);
}
