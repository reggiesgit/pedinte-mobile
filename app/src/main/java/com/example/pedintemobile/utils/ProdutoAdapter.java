package com.example.pedintemobile.utils;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pedintemobile.R;
import com.example.pedintemobile.json.ProdutoJSON;
import com.example.pedintemobile.model.Cliente;
import com.example.pedintemobile.model.Produto;

import java.util.ArrayList;
import java.util.List;

public class ProdutoAdapter extends RecyclerView.Adapter<ProdutoAdapter.ProdutoViewHolder> {
    private List<Produto> produtoData;
    private ItemClickListener clickListener;

    public ProdutoAdapter(List<Produto> produtoData, ItemClickListener listener) {
        this.produtoData = produtoData;
        this.clickListener = listener;
    }

    public class ProdutoViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView descricao;
        TextView id;
        ItemClickListener clickListener;

        public ProdutoViewHolder(View view, ItemClickListener listener) {
            super(view);
            view.setOnClickListener(this);
            descricao = view.findViewById(R.id.textViewDescription);
            id = view.findViewById(R.id.textViewId);
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
    public ProdutoAdapter.ProdutoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.produto_list_item_layout, parent, false);
        return new ProdutoAdapter.ProdutoViewHolder(itemView, clickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ProdutoAdapter.ProdutoViewHolder holder, int position) {
        holder.descricao.setText(produtoData.get(position).getDescricao());
        holder.id.setText("Código: " + produtoData.get(position).getId());
    }

    @Override
    public int getItemCount() {
        if (this.produtoData == null) {
            this.produtoData = new ArrayList<>();
        }
        return this.produtoData.size();
    }
}