package com.example.pedintemobile.json;

import android.util.Log;

import com.example.pedintemobile.model.Cliente;

import java.util.ArrayList;
import java.util.List;

public class ClienteJSON {

    private int id;
    private String name;
    private String surname;
    private String cpf;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
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
            response.setName(cliente.getNome());
            response.setSurname(cliente.getSobrenome());
            response.setCpf(cliente.getCpf());
        }
        return response;
    }

    public static Cliente map(ClienteJSON json) {
        Log.i("CLIENTE_JSON", "Mapeamndo ClienteJSON -> Cliente.");
        Cliente cliente = new Cliente();
        cliente.setId(json.getId());
        cliente.setCpf(json.getCpf());
        cliente.setNome(json.getName());
        cliente.setSobrenome(json.getSurname());
        return cliente;
    }
}
