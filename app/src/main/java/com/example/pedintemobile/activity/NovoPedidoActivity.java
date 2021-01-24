package com.example.pedintemobile.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pedintemobile.R;
import com.example.pedintemobile.facade.ClienteFacade;
import com.example.pedintemobile.facade.PedidoFacade;
import com.example.pedintemobile.facade.ProdutoFacade;
import com.example.pedintemobile.json.ClienteJSON;
import com.example.pedintemobile.json.PedidoJSON;
import com.example.pedintemobile.json.ProdutoJSON;
import com.example.pedintemobile.model.Cliente;
import com.example.pedintemobile.model.ItemDoPedido;
import com.example.pedintemobile.model.Pedido;
import com.example.pedintemobile.model.Produto;
import com.example.pedintemobile.service.ClienteCallback;
import com.example.pedintemobile.service.PedidoCallback;
import com.example.pedintemobile.service.ProdutoCallback;
import com.example.pedintemobile.utils.ItemDoPedidoAdapter;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class NovoPedidoActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private static String TAG = "Activity Novo Pedido";

    // variáveis
    private List<Produto> produtos;
    private List<Cliente> clientes;
    private Cliente clienteSelecionado = null;
    private Produto produtoSelecionado;
    private int quantidadeSelecionada = 1;
    private List<ItemDoPedido> itens;

    // elementos
    private Spinner spinnerProdutos;
    private TextView textQuantidadeItem;
    private TextView textNomeCliente;
    private TextInputEditText inputCpf;
    private Button btnMais;
    private Button btnMenos;
    private Button btnAdicionarItem;
    private Button btnValidar;
    private Button btnSalvarPedido;
    private RecyclerView recyclerItens;
    private ItemDoPedidoAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_novo_pedido);

        spinnerProdutos = findViewById(R.id.spinnerProdutos);
        obterProdutos();
        obterClientes();

        btnMais = findViewById(R.id.btnMais);
        btnMenos = findViewById(R.id.btnMenos);
        btnAdicionarItem = findViewById(R.id.btnAdicionarItem);
        btnValidar = findViewById(R.id.btnValidar);
        btnSalvarPedido = findViewById(R.id.btnSalvarPedido);
        recyclerItens = findViewById(R.id.recyclerItens);

        inputCpf = findViewById(R.id.inputCpf);

        textNomeCliente = findViewById(R.id.textNomeClienteSelecionado);
        textNomeCliente.setText("");

        textQuantidadeItem = findViewById(R.id.textQuantidadeItem);
        textQuantidadeItem.setText(String.valueOf(quantidadeSelecionada));

        itens = new ArrayList<>();

        adapter = new ItemDoPedidoAdapter(itens);

        recyclerItens.setAdapter(adapter);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        recyclerItens.setLayoutManager(layoutManager);

        btnMais.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                quantidadeSelecionada++;
                textQuantidadeItem.setText(String.valueOf(quantidadeSelecionada));
            }
        });

        btnMenos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (quantidadeSelecionada > 1) {
                    quantidadeSelecionada--;
                    textQuantidadeItem.setText(String.valueOf(quantidadeSelecionada));
                }
            }
        });

        btnAdicionarItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ItemDoPedido item = new ItemDoPedido();
                item.setProduct(produtoSelecionado);
                item.setQuantity(quantidadeSelecionada);
                itens.add(item);
                adapter.notifyDataSetChanged();
            }
        });

        btnValidar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String cpfDigitado = inputCpf.getText().toString();

                for (Cliente c : clientes) {
                    if (c.getCpf().equals(cpfDigitado)) {
                        clienteSelecionado = c;
                        String nomeCliente = clienteSelecionado.getNome() + " " + clienteSelecionado.getSobrenome();
                        textNomeCliente.setTextColor(Color.parseColor("#000000"));
                        textNomeCliente.setText("Cliente: " + nomeCliente);
                    }
                }

                if (clienteSelecionado == null) {
                    textNomeCliente.setTextColor(Color.parseColor("#ff0000"));
                    textNomeCliente.setText("Não há cliente cadastrado com este CPF.");
                }
            }
        });

        btnSalvarPedido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                salvarPedido();
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View v, int position, long id) {
        produtoSelecionado = produtos.get(position);
//        Log.i(TAG, "PRODUTO SELECIONADO: " + produtoSelecionado.getDescricao());
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        // TODO Auto-generated method stub
    }

    private void obterProdutos() {
        ProdutoFacade.findAll(new ProdutoCallback() {
            @Override
            public Produto onSuccess(ProdutoJSON produto) {
                return null;
            }

            @Override
            public List<Produto> onSuccess(List<ProdutoJSON> result) {
                produtos = ProdutoJSON.map(result);

                Log.i(TAG, "obterProdutos: Recebeu produtos: " + produtos.get(0).getDescricao());

                String[] nomesProdutos = new String[produtos.size()];
                for (int i = 0; i < produtos.size(); i++) {
                    Produto p = produtos.get(i);
                    nomesProdutos[i] = p.getDescricao();
                }

                ArrayAdapter<String> adapter = new ArrayAdapter<String>(NovoPedidoActivity.this, android.R.layout.simple_spinner_item, nomesProdutos);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                spinnerProdutos.setAdapter(adapter);
                spinnerProdutos.setOnItemSelectedListener(NovoPedidoActivity.this);

                return produtos;
            }

            @Override
            public void onFailure(Throwable t) {
                Log.e(TAG, "Falha ao tentar obter produtos. " + t.getMessage());
            }
        });
    }

    private void obterClientes() {
        ClienteFacade.findAll(new ClienteCallback() {
            @Override
            public Cliente onSuccess(ClienteJSON cliente) {
                return null;
            }

            @Override
            public List<Cliente> onSuccess(List<ClienteJSON> result) {
                clientes = ClienteJSON.map(result);
                Log.i(TAG, "obterClientes: Recebeu clientes: " + clientes.get(0).getNome());
                return clientes;
            }

            @Override
            public void onFailure(Throwable t) {
                Log.e(TAG, "Falha ao tentar buscar clientes.");
            }
        });
    }

    private void salvarPedido() {
        Pedido pedido = new Pedido();
        pedido.setCliente(clienteSelecionado);
        pedido.setItens(itens);
        pedido.setData(new Date());

        PedidoJSON pedidoJson = PedidoJSON.map(pedido);
        PedidoFacade.salvarPedido(pedidoJson, new PedidoCallback() {
            @Override
            public Pedido onSuccess(PedidoJSON pedido) {
                String text = "Pedido nº " + pedido.getId() + " salvo com sucesso.";
                Toast.makeText(NovoPedidoActivity.this, text, Toast.LENGTH_SHORT).show();

                finish();

                return PedidoJSON.map(pedido);
            }

            @Override
            public List<Pedido> onSuccess(List<PedidoJSON> result) {
                return null;
            }

            @Override
            public void onFailure(Throwable t) {
                Toast.makeText(NovoPedidoActivity.this, "Ocorreu um erro ao tentar salvar o pedido.", Toast.LENGTH_SHORT).show();
                Log.e(TAG, "Falha ao tentar salvar o pedido: ", t);
            }
        });
    }
}