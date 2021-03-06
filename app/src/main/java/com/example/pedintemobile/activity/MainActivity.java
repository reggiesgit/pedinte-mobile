package com.example.pedintemobile.activity;

import android.content.Intent;
import android.os.Bundle;

import com.example.pedintemobile.R;
import com.example.pedintemobile.facade.PedidoFacade;
import com.example.pedintemobile.json.PedidoJSON;
import com.example.pedintemobile.model.Cliente;
import com.example.pedintemobile.model.ItemDoPedido;
import com.example.pedintemobile.model.Pedido;
import com.example.pedintemobile.service.PedidoCallback;
import com.example.pedintemobile.utils.ItemClickListener;
import com.example.pedintemobile.utils.PedidoAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.View;

import android.view.Menu;
import android.view.MenuItem;

import java.util.List;


public class MainActivity extends AppCompatActivity implements ItemClickListener {
    private static String TAG = "Activity Pedido";
    private RecyclerView recyclerView;
    private List<Pedido> recyclerContent;
    private PedidoAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(MainActivity.this, NovoPedidoActivity.class), 2);
            }
        });
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
                Log.i(TAG, "setUpRecycler: Recebeu pedidos: " + recyclerContent.get(0).getId());
                adapter = new PedidoAdapter(recyclerContent, MainActivity.this);

                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this, RecyclerView.VERTICAL, false);
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setItemAnimator(new DefaultItemAnimator());
                recyclerView.setAdapter(adapter);
                adapter.setClickListener(MainActivity.this);
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
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_clientes) {
            Log.i("MENU", "onOptionsItemSelected: Invocando ClienteActivity");
            Intent it = new Intent(this, ClienteActivity.class);
            startActivity(it);
        } else if (id == R.id.action_produtos) {
            Intent it = new Intent(this, ProdutoActivity.class);
            startActivity(it);
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(int position) {
        Pedido selected = new Pedido();

        selected.setId(recyclerContent.get(position).getId());

        Cliente c = recyclerContent.get(position).getCliente();
        selected.setCliente(c);

        List<ItemDoPedido> itens = recyclerContent.get(position).getItens();
        selected.setItens(itens);

        Intent it = new Intent(this, PedidoDetailActivity.class);
        it.putExtra("Pedido", selected);
        startActivity(it);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        recyclerContent = obterPedidos();
    }
}