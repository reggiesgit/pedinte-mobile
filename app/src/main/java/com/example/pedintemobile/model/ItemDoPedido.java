package com.example.pedintemobile.model;

import java.io.Serializable;

public class ItemDoPedido implements Serializable {
    int quantity;
    Produto product;

    public ItemDoPedido(int quantity, Produto product) {
        this.quantity = quantity;
        this.product = product;
    }

    public ItemDoPedido() {
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Produto getProduct() {
        return product;
    }

    public void setProduct(Produto product) {
        this.product = product;
    }
}
