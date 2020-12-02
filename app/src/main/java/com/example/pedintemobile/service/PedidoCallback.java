package com.example.pedintemobile.service;

import com.example.pedintemobile.json.ClienteJSON;
import com.example.pedintemobile.json.PedidoJSON;
import com.example.pedintemobile.model.Cliente;
import com.example.pedintemobile.model.Pedido;

import java.util.List;

public interface PedidoCallback {
    public Pedido onSuccess(PedidoJSON pedido);
    public List<Pedido> onSuccess(List<PedidoJSON> result);
    public void onFailure(Throwable t);
}
