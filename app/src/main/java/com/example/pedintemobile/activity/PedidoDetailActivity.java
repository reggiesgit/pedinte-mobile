package com.example.pedintemobile.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.pedintemobile.R;
import com.example.pedintemobile.json.ItemDoPedidoJSON;
import com.example.pedintemobile.model.Cliente;
import com.example.pedintemobile.model.ItemDoPedido;
import com.example.pedintemobile.model.Pedido;
import com.example.pedintemobile.utils.ItemDoPedidoAdapter;

import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class PedidoDetailActivity extends AppCompatActivity {

    private static String TAG = "Detalhe Pedido";
    private Pedido pedido;

    private RecyclerView recyclerViewItens;
    private ItemDoPedidoAdapter adapter;

    private TextView textID;
    private TextView textNomeCliente;
    private TextView textCpfCliente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedido_detail);
//        Toolbar toolbar = findViewById(R.id.toolbar);

        textID = findViewById(R.id.textIdPedido);
        textNomeCliente = findViewById(R.id.textNomeCliente);
        textCpfCliente = findViewById(R.id.textCpfCliente);

        recyclerViewItens = findViewById(R.id.recyclerViewItens);

        this.pedido = (Pedido) getIntent().getSerializableExtra("Pedido");
        if (this.pedido != null) {
            textID.setText(String.valueOf(this.pedido.getId()));

            Cliente c = this.pedido.getCliente();

            String nomeCompleto = c.getNome() + " " + c.getSobrenome();
            textNomeCliente.setText(nomeCompleto);
            textCpfCliente.setText(c.getCpf());

            adapter = new ItemDoPedidoAdapter(this.pedido.getItens());

            recyclerViewItens.setAdapter(adapter);
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(PedidoDetailActivity.this, RecyclerView.VERTICAL, false);
            recyclerViewItens.setLayoutManager(layoutManager);
        } else {
            textID.setText("");
            textNomeCliente.setText("");
            textCpfCliente.setText("");
        }
    }
}