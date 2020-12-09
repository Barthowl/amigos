package com.example.amigos.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.amigos.R;
import com.example.amigos.model.room.pojo.Amigo;
import com.example.amigos.view.listener.RecyclerItemClickListener;

import java.util.List;

public class AmigoRecyclerAdapter extends RecyclerView.Adapter<AmigoRecyclerAdapter.RecyclerViewHolder>{

    private List<Amigo> amigo;
    private RecyclerItemClickListener.OnItemClickListener listener;

    public AmigoRecyclerAdapter(List<Amigo> friend) {

        this.amigo = friend;
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new RecyclerViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.obtener_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {
        Amigo friend = amigo.get(position);
        holder.tvtexto.setText(amigo.get(position).toString());
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
        if(amigo != null){
            size = amigo.size();
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
