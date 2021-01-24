package com.example.pedintemobile.json;

import android.util.Log;

import com.example.pedintemobile.model.ItemDoPedido;
import com.example.pedintemobile.model.Produto;

import java.util.ArrayList;
import java.util.List;

public class ItemDoPedidoJSON {
    int quantity;
    ProdutoJSON product;

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public ProdutoJSON getProduct() {
        return product;
    }

    public void setProduct(ProdutoJSON product) {
        this.product = product;
    }

    public static List<ItemDoPedidoJSON> mapItens(List<ItemDoPedido> itens) {
        List<ItemDoPedidoJSON> response = new ArrayList<>();
        for (ItemDoPedido item : itens) {
            response.add(map(item));
        }
        return response;
    }

    public static List<ItemDoPedido> map(List<ItemDoPedidoJSON> json) {
        List<ItemDoPedido> response = new ArrayList<>();
        for (ItemDoPedidoJSON each : json) {
            response.add(map(each));
        }
        return response;
    }

    public static ItemDoPedidoJSON map(ItemDoPedido item) {
        ItemDoPedidoJSON response = new ItemDoPedidoJSON();
        if (item != null) {
            response.setProduct(ProdutoJSON.map(item.getProduct()));
            response.setQuantity(item.getQuantity());
        }
        return response;
    }

    public static ItemDoPedido map(ItemDoPedidoJSON json) {
        Log.i("ITEM_DO_PEDIDO_JSON", "Mapeando ItemDoPedidoJSON -> ItemDoPedido.");
        ItemDoPedido item = new ItemDoPedido();

        ProdutoJSON produtoJson = json.getProduct();
        Produto produto = ProdutoJSON.map(produtoJson);
        item.setProduct(produto);

        int quantidade = json.getQuantity();
        item.setQuantity(quantidade);

        return item;
    }
}
