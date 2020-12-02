package com.example.pedintemobile.json;

import com.example.pedintemobile.model.Cliente;
import com.example.pedintemobile.model.ItemDoPedido;
import com.example.pedintemobile.model.Pedido;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class PedidoJSON {

    private int id;
    private Date data;
    private Cliente cliente;
    private List<ItemDoPedido> itens;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
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

        c.setId(json.getCliente().getId());
        c.setNome(json.getCliente().getNome());
        c.setSobrenome(json.getCliente().getSobrenome());
        c.setCpf(json.getCliente().getCpf());

        response.setId(json.getId());
        response.setData(json.getData());
        if (json.getItens() == null) {
            json.setItens(new ArrayList<ItemDoPedido>());
        }
        response.setItens(json.getItens());

        response.setCliente(c);
        return response;
    }

}
