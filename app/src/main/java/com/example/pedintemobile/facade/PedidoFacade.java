package com.example.pedintemobile.facade;

import android.util.Log;

import com.example.pedintemobile.json.ClienteJSON;
import com.example.pedintemobile.json.PedidoJSON;
import com.example.pedintemobile.service.ClienteCallback;
import com.example.pedintemobile.service.ClienteService;
import com.example.pedintemobile.service.PedidoCallback;
import com.example.pedintemobile.service.PedidoService;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PedidoFacade implements PedidoService {
    private static String TAG = "Facade Pedido";
    private static String BASE_URL = "https://sistemapedidosapi.herokuapp.com/";

    public static void findAll(final PedidoCallback callback) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        PedidoService service = retrofit.create(PedidoService.class);
        Call<List<PedidoJSON>> call = service.findAll();

        Log.i(TAG, "findAll: Enfileirando request");
        call.enqueue(new Callback<List<PedidoJSON>>() {
            @Override
            public void onResponse(Call<List<PedidoJSON>> call, Response<List<PedidoJSON>> response) {
                if (response.isSuccessful()) {
                    Log.i(TAG, "onResponse: Resposta recebida do WS: " + response.body());
                    callback.onSuccess(response.body());
                } else {
                    callback.onFailure(new Exception(response.errorBody().toString()));
                }
            }
            @Override
            public void onFailure(Call<List<PedidoJSON>> call, Throwable t) {
                callback.onFailure(t);
            }
        });
    }

    public static void salvarPedido(PedidoJSON json, final PedidoCallback callback) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        PedidoService service = retrofit.create(PedidoService.class);
        Call<PedidoJSON> call = service.salvarPedido(json);
        Log.i(TAG, "salvarPedido: Enfileirando request.");
        call.enqueue(new Callback<PedidoJSON>() {
            @Override
            public void onResponse(Call<PedidoJSON> call, Response<PedidoJSON> response) {
                if (response.isSuccessful()) {
                    Log.i(TAG, "onResponse: request satisfeito.");
                    callback.onSuccess(response.body());
                } else {
//                    try {
//                        String e = response.errorBody().string();
//                        Log.e(TAG, "failure " + e);
//                    } catch (Exception e) {
//                        e.getStackTrace();
//                    }

                    Log.w(TAG, new GsonBuilder().setPrettyPrinting().create().toJson(response));
//                    callback.onFailure(new Exception(response.errorBody().toString()));
                }
            }

            @Override
            public void onFailure(Call<PedidoJSON> call, Throwable t) {
                Log.e(TAG, "onfailure", t);
                callback.onFailure(t);
            }
        });
    }

    @Override
    public Call<List<PedidoJSON>> findAll() {
        return null;
    }

    @Override
    public Call<PedidoJSON> findById() {
        return null;
    }

    @Override
    public Call<PedidoJSON> salvarPedido(PedidoJSON json) {
        return null;
    }

    @Override
    public Call<PedidoJSON> atualizarPedido(PedidoJSON json) {
        return null;
    }

    @Override
    public Call<PedidoJSON> removerPedido(int id) {
        return null;
    }
}
