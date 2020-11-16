package com.example.pedintemobile.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.pedintemobile.R;
import com.example.pedintemobile.facade.ClienteFacade;
import com.example.pedintemobile.json.ClienteJSON;
import com.example.pedintemobile.model.Cliente;
import com.example.pedintemobile.service.ClienteCallback;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

public class ClienteDetailActivity extends Activity {

    private static String TAG = "Detalhe Cliente";
    private TextView textID;
    private EditText editNome;
    private EditText editSobrenome;
    private EditText editCpf;
    private ImageButton buttonRemover;
    private ClienteFacade facade;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cliente_detail);
        Toolbar toolbar = findViewById(R.id.toolbar);

        textID = (TextView) findViewById(R.id.textID);
        editNome = (EditText) findViewById(R.id.editNome);
        editSobrenome = (EditText) findViewById(R.id.editSobrenome);
        editCpf = (EditText) findViewById(R.id.editCpf);
        buttonRemover = (ImageButton) findViewById(R.id.buttonRemover);

        Cliente cliente = (Cliente) getIntent().getSerializableExtra("Cliente");
        if (cliente != null) {
            textID.setText(String.valueOf(cliente.getId()));
            editNome.setText(cliente.getNome());
            editSobrenome.setText(cliente.getSobrenome());
            editCpf.setText(cliente.getCpf());
        } else {
            textID.setText("");
            editNome.setText("");
            editSobrenome.setText("");
            editCpf.setText("");
            buttonRemover.setVisibility(View.INVISIBLE);
        }
    }

    public void salvarCliente(View view) {
        Cliente c = new Cliente();
        c.setNome(String.valueOf(editNome.getText()));
        c.setSobrenome(String.valueOf(editSobrenome.getText()));
        c.setCpf(String.valueOf(editCpf.getText()));
        if ("".equals(String.valueOf(textID.getText()))) {
            Log.i(TAG, "salvarCliente: Invocando Facade para salvar.");
            facade.salvarCliente(ClienteJSON.map(c), new ClienteCallback() {
                @Override
                public Cliente onSuccess(ClienteJSON cliente) {
                    Log.i(TAG, "onSuccess: Cliente salvo com sucesso!");
                    voltarParaLista();
                    return null;
                }

                @Override
                public List<Cliente> onSuccess(List<ClienteJSON> result) {
                    return null;
                }

                @Override
                public void onFailure(Throwable t) {
                    Log.e(TAG, "onFailure: ", t);
                }
            });
        } else {
            Log.i(TAG, "salvarCliente: Invocando Facade para atualizar.");
            c.setId(Integer.valueOf(textID.getText().toString()));
            facade.atualizarCliente(ClienteJSON.map(c), new ClienteCallback() {
                @Override
                public Cliente onSuccess(ClienteJSON cliente) {
                    Log.i(TAG, "onSuccess: Cliente salvo com sucesso!");
                    voltarParaLista();
                    return null;
                }

                @Override
                public List<Cliente> onSuccess(List<ClienteJSON> result) {
                    return null;
                }
                @Override
                public void onFailure(Throwable t) {
                    Log.e(TAG, "onFailure: ", t);
                }
            });
        }
    }

    public void removerCliente(View view) {
        int toRemove = Integer.valueOf(textID.getText().toString());
        facade.removerCliente(toRemove, new ClienteCallback() {
            @Override
            public Cliente onSuccess(ClienteJSON cliente) {
                Log.i(TAG, "onSuccess: Cliente removido com sucesso!");
                voltarParaLista();
                return null;
            }

            @Override
            public List<Cliente> onSuccess(List<ClienteJSON> result) {
                return null;
            }
            @Override
            public void onFailure(Throwable t) {
            }
        });
    }

    public void voltarParaLista() {
        Intent it = new Intent(this, ClienteActivity.class);
        startActivity(it);
    }
}