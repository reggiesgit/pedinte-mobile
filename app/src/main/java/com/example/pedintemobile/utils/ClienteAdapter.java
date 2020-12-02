package com.example.pedintemobile.utils;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pedintemobile.R;
import com.example.pedintemobile.model.Cliente;

import java.util.ArrayList;
import java.util.List;

public class ClienteAdapter extends RecyclerView.Adapter<ClienteAdapter.ClienteViewHolder> {
    private List<Cliente> clientData;
    private ItemClickListener clickListener;

    public ClienteAdapter(List<Cliente> clientData, ItemClickListener listener) {
        this.clientData = clientData;
        this.clickListener = listener;
    }

    public class ClienteViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView nome;
        TextView sobrenome;
        TextView cpf;
        ItemClickListener clickListener;

        public ClienteViewHolder(View view, ItemClickListener listener) {
            super(view);
            view.setOnClickListener(this);
            nome = view.findViewById(R.id.textViewNome);
            sobrenome = view.findViewById(R.id.textViewSobrenome);
            cpf = view.findViewById(R.id.textViewCpf);
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
    public ClienteAdapter.ClienteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.cliente_list_item_layout, parent, false);
        return new ClienteAdapter.ClienteViewHolder(itemView, clickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ClienteAdapter.ClienteViewHolder holder, int position) {
        holder.nome.setText(clientData.get(position).getNome());
        holder.sobrenome.setText(clientData.get(position).getSobrenome());
        holder.cpf.setText(clientData.get(position).getCpf());
    }

    @Override
    public int getItemCount() {
        if (this.clientData == null) {
            this.clientData = new ArrayList<>();
        }
        return this.clientData.size();
    }
}
