package com.example.pedintemobile.facade;

import android.util.Log;

import com.example.pedintemobile.json.PedidoJSON;
import com.example.pedintemobile.service.PedidoCallback;
import com.example.pedintemobile.service.PedidoService;

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
        Call<Void> call = service.salvarPedido(json);
        Log.i(TAG, "salvarPedido: Enfileirando request.");
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    Log.i(TAG, "onResponse: request satisfeito.");
                    callback.onSuccess();
                } else {
                    callback.onFailure(new Exception(response.errorBody().toString()));
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
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
    public Call<Void> salvarPedido(PedidoJSON json) {
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
