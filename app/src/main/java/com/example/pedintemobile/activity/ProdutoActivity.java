package com.example.pedintemobile.activity;

import android.content.Intent;
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
import com.example.pedintemobile.facade.ProdutoFacade;
import com.example.pedintemobile.json.ProdutoJSON;
import com.example.pedintemobile.model.Produto;
import com.example.pedintemobile.service.ProdutoCallback;
import com.example.pedintemobile.utils.ItemClickListener;
import com.example.pedintemobile.utils.ProdutoAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class ProdutoActivity extends AppCompatActivity implements ItemClickListener {

    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private static String TAG = "Activity Produto";
    private List<Produto> recyclerContent;
    private ProdutoAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_produto);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ProdutoActivity.this, ProdutoDetailActivity.class));
            }
        });

        recyclerContent = obterProdutos();
    }

    private List<Produto> obterProdutos() {
        Log.i(TAG, "obterProdutos: Iniciando requisição por todos os produtos.");
        progressBar = findViewById(R.id.progressBar);

        ProdutoFacade.findAll(new ProdutoCallback() {
            @Override
            public Produto onSuccess(ProdutoJSON produto) {
                return null;
            }

            @Override
            public List<Produto> onSuccess(List<ProdutoJSON> result) {
                recyclerView = (RecyclerView) findViewById(R.id.produto_recycler_view);

                progressBar.setVisibility(View.GONE);
                recyclerView.setVisibility(View.VISIBLE);

                recyclerContent = ProdutoJSON.map(result);
                adapter = new ProdutoAdapter(recyclerContent, ProdutoActivity.this);

                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(ProdutoActivity.this, RecyclerView.VERTICAL, false);
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setItemAnimator(new DefaultItemAnimator());
                recyclerView.setAdapter(adapter);
                adapter.setClickListener(ProdutoActivity.this);
                adapter.notifyDataSetChanged();

                Log.i(TAG, "onSuccess: Retornando clientes.");
                return recyclerContent;
            }

            @Override
            public void onFailure(Throwable t) {
                Log.e(TAG, "onFailure: ", t);
            }
        });
        return null;
    }

    @Override
    public void onClick(int position) {
        Produto selected = new Produto();
        selected.setId(recyclerContent.get(position).getId());
        selected.setDescricao(recyclerContent.get(position).getDescricao());

        Intent it = new Intent(this, ProdutoDetailActivity.class);
        it.putExtra("Produto", selected);
        startActivity(it);
    }
}