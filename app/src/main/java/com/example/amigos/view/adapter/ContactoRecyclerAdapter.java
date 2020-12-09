package com.example.amigos.view.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.amigos.R;
import com.example.amigos.model.room.pojo.Contacto;
import com.example.amigos.view.listener.RecyclerItemClickListener;

import java.util.ArrayList;
import java.util.List;

public class ContactoRecyclerAdapter extends RecyclerView.Adapter<ContactoRecyclerAdapter.RecyclerViewHolder>{

    private List<Contacto> contacto = new ArrayList<>();
    private RecyclerItemClickListener.OnItemClickListener listener;


    public ContactoRecyclerAdapter(List<Contacto> contacto) {
        this.contacto = contacto;
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new RecyclerViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.obtener_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {
        Contacto contactos = contacto.get(position);
        holder.tvtexto.setText(contacto.get(position).toString());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onItemClick(view,position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return contacto.size();
    }


    public class RecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView tvtexto;

        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            tvtexto = itemView.findViewById(R.id.tvtexto);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
           Log.v("XYZ", contacto.get(2).toString());
        }
    }

}

