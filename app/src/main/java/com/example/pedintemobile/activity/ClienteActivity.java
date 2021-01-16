package com.example.pedintemobile.activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import com.example.pedintemobile.R;
import com.example.pedintemobile.facade.ClienteFacade;
import com.example.pedintemobile.json.ClienteJSON;
import com.example.pedintemobile.model.Cliente;
import com.example.pedintemobile.service.ClienteCallback;
import com.example.pedintemobile.utils.ClienteAdapter;
import com.example.pedintemobile.utils.ItemClickListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.View;

import java.util.List;

public class ClienteActivity extends AppCompatActivity implements ItemClickListener {
    private static String TAG = "Activity Cliente";
    private RecyclerView recyclerView;
    private List<Cliente> recyclerContent;
    private ClienteAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cliente);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ClienteActivity.this, ClienteDetailActivity.class));
            }
       });

        recyclerContent = obterClientes();
    }

    private List<Cliente> obterClientes() {
        Log.i(TAG, "obterClientes: Iniciando requisição por todos os clientes.");

        ClienteFacade.findAll(new ClienteCallback() {
            @Override
            public Cliente onSuccess(ClienteJSON cliente) {
                return null;
            }

            @Override
            public List<Cliente> onSuccess(List<ClienteJSON> result) {
                recyclerView = (RecyclerView) findViewById(R.id.cliente_recycler_view);

                recyclerContent = ClienteJSON.map(result);
                Log.i(TAG, "setUpRecycler: Recebeu clientes: " + recyclerContent.get(0).getNome());
                adapter = new ClienteAdapter(recyclerContent, ClienteActivity.this);

                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(ClienteActivity.this, RecyclerView.VERTICAL, false);
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setItemAnimator(new DefaultItemAnimator());
                recyclerView.setAdapter(adapter);
                adapter.setClickListener(ClienteActivity.this);
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
        Cliente selected = new Cliente();
        selected.setId(recyclerContent.get(position).getId());
        selected.setNome(recyclerContent.get(position).getNome());
        selected.setSobrenome(recyclerContent.get(position).getSobrenome());
        selected.setCpf(recyclerContent.get(position).getCpf());

        Intent it = new Intent(this, ClienteDetailActivity.class);
        it.putExtra("Cliente", selected);
        startActivity(it);
    }
}