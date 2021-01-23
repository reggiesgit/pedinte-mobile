package com.example.pedintemobile.facade;

import android.util.Log;

import com.example.pedintemobile.json.ProdutoJSON;
import com.example.pedintemobile.service.ProdutoCallback;
import com.example.pedintemobile.service.ProdutoService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ProdutoFacade implements ProdutoService {

    private static String TAG = "Facade Cliente";
    private static String BASE_URL = "https://sistemapedidosapi.herokuapp.com/";

    public static void findAll(final ProdutoCallback callback) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ProdutoService service = retrofit.create(ProdutoService.class);
        Call<List<ProdutoJSON>> call = service.findAll();

        Log.i(TAG, "findAll: Enfileirando request");
        call.enqueue(new Callback<List<ProdutoJSON>>() {
            @Override
            public void onResponse(Call<List<ProdutoJSON>> call, Response<List<ProdutoJSON>> response) {
                if (response.isSuccessful()) {
                    Log.i(TAG, "onResponse: Resposta recebida do WS: " + response.body());
                    callback.onSuccess(response.body());
                } else {
                    callback.onFailure(new Exception(response.errorBody().toString()));
                }
            }
            @Override
            public void onFailure(Call<List<ProdutoJSON>> call, Throwable t) {
                callback.onFailure(t);
            }
        });
    }

    public static void salvarProduto(ProdutoJSON json, final ProdutoCallback callback) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ProdutoService service = retrofit.create(ProdutoService.class);
        Call<ProdutoJSON> call = service.salvarProduto(json);
        Log.i(TAG, "salvarProduto: Enfileirando request.");
        call.enqueue(new Callback<ProdutoJSON>() {
            @Override
            public void onResponse(Call<ProdutoJSON> call, Response<ProdutoJSON> response) {
                if (response.isSuccessful()) {
                    Log.i(TAG, "onResponse: request satisfeito.");
                    callback.onSuccess(response.body());
                } else {
                    callback.onFailure(new Exception(response.errorBody().toString()));
                }
            }
            @Override
            public void onFailure(Call<ProdutoJSON> call, Throwable t) {
                callback.onFailure(t);
            }
        });
    }

    public static void atualizarProduto(ProdutoJSON json, final ProdutoCallback callback) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ProdutoService service = retrofit.create(ProdutoService.class);
        Call<ProdutoJSON> call = service.atualizarProduto(json);
        call.enqueue(new Callback<ProdutoJSON>() {
            @Override
            public void onResponse(Call<ProdutoJSON> call, Response<ProdutoJSON> response) {
                if (response.isSuccessful()) {
                    Log.i(TAG, "onResponse: request satisfeito.");
                    callback.onSuccess(response.body());
                } else {
                    callback.onFailure(new Exception(response.errorBody().toString()));
                }
            }
            @Override
            public void onFailure(Call<ProdutoJSON> call, Throwable t) {
                callback.onFailure(t);
            }
        });
    }

    public static void removerProduto(int toRemove, final ProdutoCallback callback) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ProdutoService service = retrofit.create(ProdutoService.class);
        Call<ProdutoJSON> call = service.removerProduto(toRemove);
        Log.i(TAG, "removerProduto: Enfileirando request.");
        call.enqueue(new Callback<ProdutoJSON>() {
            @Override
            public void onResponse(Call<ProdutoJSON> call, Response<ProdutoJSON> response) {
                if (response.isSuccessful()) {
                    Log.i(TAG, "onResponse: request satisfeito.");
                    callback.onSuccess(response.body());
                } else {
                    callback.onFailure(new Exception(response.errorBody().toString()));
                }
            }
            @Override
            public void onFailure(Call<ProdutoJSON> call, Throwable t) {
                callback.onFailure(t);
            }
        });
    }

    @Override
    public Call<List<ProdutoJSON>> findAll() { return null; }

    @Override
    public Call<ProdutoJSON> findById() { return null; }

    @Override
    public Call<ProdutoJSON> salvarProduto(ProdutoJSON json) { return null; }

    @Override
    public Call<ProdutoJSON> atualizarProduto(ProdutoJSON json) { return null; }

    @Override
    public Call<ProdutoJSON> removerProduto(int id) { return null; }
}
