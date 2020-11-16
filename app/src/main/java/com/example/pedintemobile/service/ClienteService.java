package com.example.pedintemobile.service;

import com.example.pedintemobile.json.ClienteJSON;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ClienteService {

    @GET("cliente/")
    Call<List<ClienteJSON>> findAll();

    @GET("cliente/{id}")
    Call<ClienteJSON> findById();

    @POST("cliente/")
    Call<ClienteJSON> salvarCliente(@Body ClienteJSON json);

    @PUT("cliente/")
    Call<ClienteJSON> atualizarCliente(@Body ClienteJSON json);

    @DELETE("cliente/{id}")
    Call<ClienteJSON> removerCliente(@Path("id") int id);
}
