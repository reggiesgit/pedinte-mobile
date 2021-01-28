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

    @GET("clients/")
    Call<List<ClienteJSON>> findAll();

    @GET("clients/{id}")
    Call<ClienteJSON> findById();

    @POST("clients/")
    Call<Void> salvarCliente(@Body ClienteJSON json);

    @PUT("clients/")
    Call<Void> atualizarCliente(@Body ClienteJSON json);

    @DELETE("clients/{id}")
    Call<Void> removerCliente(@Path("id") int id);
}
