package com.example.pedintemobile.json;

import com.example.pedintemobile.model.Cliente;
import com.example.pedintemobile.model.ItemDoPedido;
import com.example.pedintemobile.model.Pedido;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class PedidoJSON {

    private int id;
    private Date createdAt;
    private Cliente client;
    private List<ItemDoPedido> itens;

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

    public Cliente getClient() {
        return client;
    }

    public void setClient(Cliente client) {
        this.client = client;
    }

    public List<ItemDoPedido> getItens() {
        return itens;
    }

    public void setItens(List<ItemDoPedido> itens) {
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
        Cliente c = new Cliente();

        c.setId(json.getClient().getId());
        c.setNome(json.getClient().getNome());
        c.setSobrenome(json.getClient().getSobrenome());
        c.setCpf(json.getClient().getCpf());

        response.setId(json.getId());
        response.setData(json.getCreatedAt());
        if (json.getItens() == null) {
            json.setItens(new ArrayList<ItemDoPedido>());
        }
        response.setItens(json.getItens());

        response.setCliente(c);
        return response;
    }

}
