package com.example.pedintemobile.facade;

import android.os.AsyncTask;
import android.util.Log;

import com.example.pedintemobile.json.ClienteJSON;
import com.example.pedintemobile.service.ClienteCallback;
import com.example.pedintemobile.service.ClienteService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ClienteFacade implements ClienteService {

    private static String TAG = "Facade Cliente";
    private static String BASE_URL = "https://sistemapedidosapi.herokuapp.com/";

    public static void findAll(final ClienteCallback callback) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ClienteService service = retrofit.create(ClienteService.class);
        Call<List<ClienteJSON>> call = service.findAll();

        Log.i(TAG, "findAll: Enfileirando request");
        call.enqueue(new Callback<List<ClienteJSON>>() {
            @Override
            public void onResponse(Call<List<ClienteJSON>> call, Response<List<ClienteJSON>> response) {
                if (response.isSuccessful()) {
                    Log.i(TAG, "onResponse: Resposta recebida do WS: " + response.body());
                    callback.onSuccess(response.body());
                } else {
                    callback.onFailure(new Exception(response.errorBody().toString()));
                }
            }
            @Override
            public void onFailure(Call<List<ClienteJSON>> call, Throwable t) {
                callback.onFailure(t);
            }
        });
    }

    public static void salvarCliente(ClienteJSON json, final ClienteCallback callback) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ClienteService service = retrofit.create(ClienteService.class);
        Call<ClienteJSON> call = service.salvarCliente(json);
        Log.i(TAG, "salvarCliente: Enfileirando request.");
        call.enqueue(new Callback<ClienteJSON>() {
            @Override
            public void onResponse(Call<ClienteJSON> call, Response<ClienteJSON> response) {
                if (response.isSuccessful()) {
                    Log.i(TAG, "onResponse: request satisfeito.");
                    callback.onSuccess(response.body());
                } else {
                    callback.onFailure(new Exception(response.errorBody().toString()));
                }
            }
            @Override
            public void onFailure(Call<ClienteJSON> call, Throwable t) {
                callback.onFailure(t);
            }
        });
    }

    public static void atualizarCliente(ClienteJSON json, final ClienteCallback callback) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ClienteService service = retrofit.create(ClienteService.class);
        Call<ClienteJSON> call = service.atualizarCliente(json);
        Log.i(TAG, "removerCliente: Enfileirando request.");
        call.enqueue(new Callback<ClienteJSON>() {
            @Override
            public void onResponse(Call<ClienteJSON> call, Response<ClienteJSON> response) {
                if (response.isSuccessful()) {
                    Log.i(TAG, "onResponse: request satisfeito.");
                    callback.onSuccess(response.body());
                } else {
                    callback.onFailure(new Exception(response.errorBody().toString()));
                }
            }
            @Override
            public void onFailure(Call<ClienteJSON> call, Throwable t) {
                callback.onFailure(t);
            }
        });
    }

    public static void removerCliente(int toRemove, final ClienteCallback callback) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ClienteService service = retrofit.create(ClienteService.class);
        Call<ClienteJSON> call = service.removerCliente(toRemove);
        Log.i(TAG, "removerCliente: Enfileirando request.");
        call.enqueue(new Callback<ClienteJSON>() {
            @Override
            public void onResponse(Call<ClienteJSON> call, Response<ClienteJSON> response) {
                if (response.isSuccessful()) {
                    Log.i(TAG, "onResponse: request satisfeito.");
                    callback.onSuccess(response.body());
                } else {
                    callback.onFailure(new Exception(response.errorBody().toString()));
                }
            }
            @Override
            public void onFailure(Call<ClienteJSON> call, Throwable t) {
                callback.onFailure(t);
            }
        });
    }

    @Override
    public Call<List<ClienteJSON>> findAll() {
        return null;
    }

    @Override
    public Call<ClienteJSON> findById() {
        return null;
    }

    @Override
    public Call<ClienteJSON> salvarCliente(ClienteJSON json) {
        return null;
    }

    @Override
    public Call<ClienteJSON> atualizarCliente(ClienteJSON json) {
        return null;
    }

    @Override
    public Call<ClienteJSON> removerCliente(int id) {
        return null;
    }
}
