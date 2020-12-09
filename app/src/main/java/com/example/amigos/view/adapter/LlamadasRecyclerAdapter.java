package com.example.amigos.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.amigos.R;
import com.example.amigos.model.room.pojo.Llamada;
import com.example.amigos.view.listener.RecyclerItemClickListener;

import java.util.List;

public class LlamadasRecyclerAdapter extends RecyclerView.Adapter<LlamadasRecyclerAdapter.RecyclerViewHolder>{

    private List<Llamada> call;
    private RecyclerItemClickListener.OnItemClickListener listener;

    public LlamadasRecyclerAdapter(List<Llamada> l) {

        this.call = l;
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new RecyclerViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.obtener_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {
        Llamada l = call.get(position);
        holder.tvtexto.setText(call.get(position).toString());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //listener.onItemClick(view,position);
            }
        });
    }

    @Override
    public int getItemCount() {
        int size = 0;
        if(call != null){
            size = call.size();
        }
        return size;
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder {

        public TextView tvtexto;

        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            tvtexto = itemView.findViewById(R.id.tvtexto);
        }
    }
}

