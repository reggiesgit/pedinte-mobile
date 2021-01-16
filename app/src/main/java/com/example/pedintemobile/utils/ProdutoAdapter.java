package com.example.pedintemobile.utils;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pedintemobile.R;
import com.example.pedintemobile.json.ProdutoJSON;

import java.util.List;

public class ProdutoAdapter extends RecyclerView.Adapter<ProdutoAdapter.ProdutoViewHolder> {

    private List<ProdutoJSON> produtos;

    public ProdutoAdapter(List<ProdutoJSON> produtos) {
        this.produtos = produtos;
    }

    public class ProdutoViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        AppCompatTextView id;
        AppCompatTextView description;

        public ProdutoViewHolder(@NonNull View view) {
            super(view);
            id = view.findViewById(R.id.textViewId);
            description = view.findViewById(R.id.textViewDescription);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

        }
    }


    @NonNull
    @Override
    public ProdutoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.produto_list_item_layout, parent, false);
        return new ProdutoAdapter.ProdutoViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ProdutoViewHolder holder, int position) {
        holder.id.setText(String.valueOf(produtos.get(position).getId()));
        holder.description.setText(produtos.get(position).getDescricao());
    }

    @Override
    public int getItemCount() {
        return produtos.size() > 0 ? produtos.size() : 0;
    }

}