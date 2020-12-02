package com.example.pedintemobile.json;

import android.util.Log;

import com.example.pedintemobile.model.Cliente;

import java.util.ArrayList;
import java.util.List;

public class ClienteJSON {

    private int id;
    private String nome;
    private String sobrenome;
    private String cpf;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public static List<Cliente> map(List<ClienteJSON> json) {
        List<Cliente> response = new ArrayList<>();
        for (ClienteJSON each : json) {
            response.add(map(each));
        }
        return response;
    }

    public static ClienteJSON map(Cliente cliente) {
        ClienteJSON response = new ClienteJSON();
        if (cliente != null) {
            response.setId(cliente.getId());
            response.setNome(cliente.getNome());
            response.setSobrenome(cliente.getSobrenome());
            response.setCpf(cliente.getCpf());
        }
        return response;
    }

    public static Cliente map(ClienteJSON json) {
        Log.i("CLIENTE_JSON", "Mapeamndo ClienteJSON -> Cliente.");
        Cliente cliente = new Cliente();
        cliente.setId(json.getId());
        cliente.setCpf(json.getCpf());
        cliente.setNome(json.getNome());
        cliente.setSobrenome(json.getSobrenome());
        return cliente;
    }
}
