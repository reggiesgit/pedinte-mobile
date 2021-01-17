package com.example.pedintemobile.config.retrofit;

import com.example.pedintemobile.service.ClienteService;
import com.example.pedintemobile.service.PedidoService;
import com.example.pedintemobile.service.ProdutoService;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitConfig {

    private static Retrofit retrofit;
    private static final String BASE_URL = "https://sistemapedidosapi.herokuapp.com";

    public static Retrofit getRetrofitInstance() {
        if (retrofit == null) {
            retrofit = new retrofit2.Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

    public static ProdutoService getProdutoService() {
        return getRetrofitInstance().create(ProdutoService.class);
    }

    public static ClienteService getClienteService() {
        return getRetrofitInstance().create(ClienteService.class);
    }

    public static PedidoService getPedidoService() {
        return getRetrofitInstance().create(PedidoService.class);
    }

}