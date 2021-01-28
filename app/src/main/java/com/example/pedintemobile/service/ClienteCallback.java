package com.example.pedintemobile.service;

import com.example.pedintemobile.json.ClienteJSON;
import com.example.pedintemobile.model.Cliente;

import java.util.List;

public interface ClienteCallback {
    public void onSuccess();
    public List<Cliente> onSuccess(List<ClienteJSON> result);
    public void onFailure(Throwable t);
}
