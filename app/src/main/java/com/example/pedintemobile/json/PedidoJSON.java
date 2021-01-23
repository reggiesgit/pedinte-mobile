package com.example.pedintemobile.json;

import android.util.Log;

import com.example.pedintemobile.model.Cliente;
import com.example.pedintemobile.model.ItemDoPedido;
import com.example.pedintemobile.model.Pedido;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PedidoJSON {

    private int id;
    private Date createdAt;
    private ClienteJSON client;
    private List<ItemDoPedidoJSON> itens;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public ClienteJSON getClient() {
        return client;
    }

    public void setClient(ClienteJSON client) {
        this.client = client;
    }

    public List<ItemDoPedidoJSON> getItens() {
        return itens;
    }

    public void setItens(List<ItemDoPedidoJSON> itens) {
        this.itens = itens;
    }

    public static List<Pedido> map(List<PedidoJSON> json) {
        List<Pedido> response = new ArrayList<>();
        for (PedidoJSON each : json) {
            response.add(map(each));
        }
        return response;
    }

    public static Pedido map(PedidoJSON json) {
        Pedido response = new Pedido();

        response.setId(json.getId());
        response.setData(json.getCreatedAt());

        ClienteJSON clienteJson = json.getClient();
        Cliente c = ClienteJSON.map(clienteJson);
        response.setCliente(c);

        if (json.getItens() == null) {
            response.setItens(new ArrayList<ItemDoPedido>());
        } else {
            List<ItemDoPedidoJSON> itensJson = json.getItens();
            List<ItemDoPedido> itens = ItemDoPedidoJSON.map(itensJson);
            response.setItens(itens);
        }

        return response;
    }
}
