package com.example.pedintemobile.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pedintemobile.R;
import com.example.pedintemobile.config.retrofit.RetrofitConfig;
import com.example.pedintemobile.json.ProdutoJSON;
import com.example.pedintemobile.utils.ProdutoAdapter;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProdutoActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_produto);

        Toolbar toolbar = findViewById(R.id.toolbar_produto);
        setSupportActionBar(toolbar);

        initView();

    }

    private void initView() {
        recyclerView = findViewById(R.id.recyclerView);
        progressBar = findViewById(R.id.progressBar);

        findAllProductos();

    }

    private void findAllProductos() {

        RetrofitConfig.getProdutoService().getAllProductos().enqueue(new Callback<List<ProdutoJSON>>() {

            @Override
            public void onResponse(Call<List<ProdutoJSON>> call, Response<List<ProdutoJSON>> response) {

                Log.d("JSON", response.toString());

                if (!response.body().isEmpty()) {

                    progressBar.setVisibility(View.GONE);
                    recyclerView.setVisibility(View.VISIBLE);

                    ProdutoAdapter adapter = new ProdutoAdapter(response.body());

                    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(ProdutoActivity.this, RecyclerView.VERTICAL, false);
                    recyclerView.setLayoutManager(layoutManager);
                    recyclerView.setItemAnimator(new DefaultItemAnimator());
                    recyclerView.setAdapter(adapter);
//                    adapter.setClickListener(PedidoActivity.this);
                    adapter.notifyDataSetChanged();

                }
            }

            @Override
            public void onFailure(Call<List<ProdutoJSON>> call, Throwable t) {
                Log.e("ERROR", t.getMessage());
            }
        });

    }
}