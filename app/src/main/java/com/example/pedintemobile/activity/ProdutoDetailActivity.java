package com.example.pedintemobile.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;

import com.example.pedintemobile.R;
import com.example.pedintemobile.facade.ClienteFacade;
import com.example.pedintemobile.facade.ProdutoFacade;
import com.example.pedintemobile.json.ClienteJSON;
import com.example.pedintemobile.json.ProdutoJSON;
import com.example.pedintemobile.model.Cliente;
import com.example.pedintemobile.model.Produto;
import com.example.pedintemobile.service.ClienteCallback;
import com.example.pedintemobile.service.ProdutoCallback;

import java.util.List;

public class ProdutoDetailActivity extends Activity {
    private static String TAG = "Detalhe Produto";
    private TextView textID;
    private EditText editDescricao;
    private ImageButton buttonRemover;
    private ProdutoFacade facade;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_produto_detail);
        Toolbar toolbar = findViewById(R.id.toolbar);

        textID = (TextView) findViewById(R.id.textID);
        editDescricao = (EditText) findViewById(R.id.editDescricao);
        buttonRemover = (ImageButton) findViewById(R.id.buttonRemover);

        Produto produto = (Produto) getIntent().getSerializableExtra("Produto");
        if (produto != null) {
            textID.setText(String.valueOf(produto.getId()));
            editDescricao.setText(produto.getDescricao());
        } else {
            textID.setText("");
            editDescricao.setText("");
            buttonRemover.setVisibility(View.INVISIBLE);
        }
    }

    public void salvarProduto(View view) {
        Produto p = new Produto();
        p.setDescricao(String.valueOf(editDescricao.getText()));

        if ("".equals(String.valueOf(textID.getText()))) {
            Log.i(TAG, "salvarProduto: Invocando Facade para salvar.");
            facade.salvarProduto(ProdutoJSON.map(p), new ProdutoCallback() {
                @Override
                public Produto onSuccess(ProdutoJSON produto) {
                    Log.i(TAG, "onSuccess: Cliente salvo com sucesso!");
                    voltarParaLista();
                    return null;
                }

                @Override
                public List<Produto> onSuccess(List<ProdutoJSON> result) {
                    return null;
                }

                @Override
                public void onFailure(Throwable t) {
                    Log.e(TAG, "onFailure: ", t);
                }
            });
        } else {
            Log.i(TAG, "salvarProduto: Invocando Facade para atualizar.");
            p.setId(Integer.valueOf(textID.getText().toString()));
            facade.atualizarProduto(ProdutoJSON.map(p), new ProdutoCallback() {
                @Override
                public Produto onSuccess(ProdutoJSON produto) {
                    Log.i(TAG, "onSuccess: Produto salvo com sucesso!");
                    voltarParaLista();
                    return null;
                }

                @Override
                public List<Produto> onSuccess(List<ProdutoJSON> result) {
                    return null;
                }
                @Override
                public void onFailure(Throwable t) {
                    Log.e(TAG, "onFailure: ", t);
                }
            });
        }
    }

    public void removerProduto(View view) {
        int toRemove = Integer.valueOf(textID.getText().toString());
        facade.removerProduto(toRemove, new ProdutoCallback() {
            @Override
            public Produto onSuccess(ProdutoJSON produto) {
                Log.i(TAG, "onSuccess: Produto removido com sucesso!");
                voltarParaLista();
                return null;
            }

            @Override
            public List<Produto> onSuccess(List<ProdutoJSON> result) {
                return null;
            }
            @Override
            public void onFailure(Throwable t) {
            }
        });
    }

    public void voltarParaLista() {
        Intent it = new Intent(this, ProdutoActivity.class);
        startActivity(it);
    }
}
