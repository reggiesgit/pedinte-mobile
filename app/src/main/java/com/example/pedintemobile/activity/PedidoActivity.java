package com.example.pedintemobile.activity;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pedintemobile.R;
import com.example.pedintemobile.facade.PedidoFacade;
import com.example.pedintemobile.json.PedidoJSON;
import com.example.pedintemobile.model.Cliente;
import com.example.pedintemobile.model.Pedido;
import com.example.pedintemobile.service.PedidoCallback;
import com.example.pedintemobile.utils.ItemClickListener;
import com.example.pedintemobile.utils.PedidoAdapter;

import java.util.List;

public class PedidoActivity extends AppCompatActivity implements ItemClickListener {
    private static String TAG = "Activity Pedido";
    private RecyclerView recyclerView;
    private List<Pedido> recyclerContent;
    private PedidoAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedido);
        Toolbar toolbar = findViewById(R.id.toolbar_pedido);
        setSupportActionBar(toolbar);

        recyclerContent = obterPedidos();
    }

    private List<Pedido> obterPedidos() {
        Log.i(TAG, "obterPedidos: Iniciando requisição por todos os pedidos.");

        PedidoFacade.findAll(new PedidoCallback() {
            @Override
            public void onSuccess() {}

            @Override
            public Pedido onSuccess(PedidoJSON pedido) {
                return null;
            }

            @Override
            public List<Pedido> onSuccess(List<PedidoJSON> result) {
                recyclerView = (RecyclerView) findViewById(R.id.pedido_recycler_view);
                recyclerContent = PedidoJSON.map(result);
                Log.i(TAG, "setUpRecycler: Recebeu pedidos: " + recyclerContent.get(0).getItens());
                adapter = new PedidoAdapter(recyclerContent, PedidoActivity.this);

                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(PedidoActivity.this, RecyclerView.VERTICAL, false);
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setItemAnimator(new DefaultItemAnimator());
                recyclerView.setAdapter(adapter);
                adapter.setClickListener(PedidoActivity.this);
                adapter.notifyDataSetChanged();

                Log.i(TAG, "onSuccess: Retornando pedidos.");
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
        Pedido selected = new Pedido();
        Cliente c = new Cliente();
        c.setNome(recyclerContent.get(position).getCliente().getNome());
        c.setSobrenome(recyclerContent.get(position).getCliente().getSobrenome());
        selected.setId(recyclerContent.get(position).getId());
        selected.setData(recyclerContent.get(position).getData());
    }
}
