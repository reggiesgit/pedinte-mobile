package com.example.pedintemobile.utils;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pedintemobile.R;
import com.example.pedintemobile.model.ItemDoPedido;
import com.example.pedintemobile.model.Pedido;

import java.util.ArrayList;
import java.util.List;

public class PedidoAdapter extends RecyclerView.Adapter<PedidoAdapter.PedidoViewHolder> {
    private List<Pedido> pedidoData;
    private ItemClickListener clickListener;

    public PedidoAdapter(List<Pedido> pedidoData, ItemClickListener clickListener) {
        this.pedidoData = pedidoData;
        this.clickListener = clickListener;
    }

    public class PedidoViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView nome;
        TextView sobrenome;
        TextView quantidade;
        ItemClickListener clickListener;

        public PedidoViewHolder(View view, ItemClickListener listener) {
            super(view);
            view.setOnClickListener(this);
            nome = view.findViewById(R.id.textViewClienteNome);
            sobrenome = view.findViewById(R.id.textViewClienteSobrenome);
            quantidade = view.findViewById(R.id.textViewQuantidade);
            this.clickListener = listener;
        }

        @Override
        public void onClick(View view) {
            if (clickListener != null) {
                clickListener.onClick(getAdapterPosition());
            }
        }
    }

    public void setClickListener(ItemClickListener listener) {
        this.clickListener = listener;
    }

    @NonNull
    @Override
    public PedidoAdapter.PedidoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.pedido_list_item_layout, parent, false);
        return new PedidoAdapter.PedidoViewHolder(itemView, clickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull PedidoAdapter.PedidoViewHolder holder, int position) {
        holder.nome.setText(pedidoData.get(position).getCliente().getNome());
        holder.sobrenome.setText(pedidoData.get(position).getCliente().getSobrenome());

        List<ItemDoPedido> itens = pedidoData.get(position).getItens();
        int quantidade = 0;
        for (ItemDoPedido i : itens) {
            quantidade += i.getQuantity();
        }

        holder.quantidade.setText(String.valueOf(quantidade));
    }

    @Override
    public int getItemCount() {
        if (this.pedidoData == null) {
            this.pedidoData = new ArrayList<>();
        }
        return this.pedidoData.size();
    }
}
