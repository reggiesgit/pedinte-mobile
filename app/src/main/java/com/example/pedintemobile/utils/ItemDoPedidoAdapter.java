package com.example.pedintemobile.utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.pedintemobile.model.ItemDoPedido;

import java.util.ArrayList;
import java.util.List;
import com.example.pedintemobile.R;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ItemDoPedidoAdapter extends RecyclerView.Adapter<ItemDoPedidoAdapter.ItemDoPedidoViewHolder> {
    private List<ItemDoPedido> itens;
    
    public ItemDoPedidoAdapter(List<ItemDoPedido> itens) {
        this.itens = itens;
    }

    @NonNull
    @Override
    public ItemDoPedidoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // ver no vídeo como criar o layout do item da lista
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_item_layout, parent, false);
        return new ItemDoPedidoAdapter.ItemDoPedidoViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemDoPedidoViewHolder holder, int position) {
        ItemDoPedido item = itens.get(position);

        holder.descricaoProduto.setText(item.getProduct().getDescricao());
        holder.quantidade.setText(String.valueOf(item.getQuantity()));
    }

    @Override
    public int getItemCount() {
        if (this.itens == null) {
            this.itens = new ArrayList<>();
        }
        return this.itens.size();
    }

    public class ItemDoPedidoViewHolder extends RecyclerView.ViewHolder {
        TextView quantidade;
        TextView descricaoProduto;
        
        public ItemDoPedidoViewHolder(View view) {
            super(view);

            this.quantidade = view.findViewById(R.id.textQuantidade);
            this.descricaoProduto = view.findViewById(R.id.textDescProduto);
        }
    }
}
