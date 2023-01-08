package com.example.b3tempofertilati;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.b3tempofertilati.databinding.TempoDateItemBinding;

import java.util.List;

public class TempoDateAdapter extends RecyclerView.Adapter<TempoDateAdapter.TempoDateViewHolder> {
    private List<TempoDate> tempoDates;
    private Context context;

    public class TempoDateViewHolder extends RecyclerView.ViewHolder {
        TempoDateItemBinding binding ;

        public TempoDateViewHolder(@NonNull TempoDateItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding ;
        }
    }

    public TempoDateAdapter(List<TempoDate> tempoDates, Context context){
        this.tempoDates = tempoDates;
        this.context = context;
    }
    @NonNull
    @Override
    public TempoDateViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.tempo_date_item, parent,false);
        TempoDateItemBinding binding = TempoDateItemBinding.bind(v);

        return new TempoDateViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull TempoDateViewHolder holder, int position) {
        holder.binding.dateTv.setText(tempoDates.get(position).getDate());
        holder.binding.colorFl.setBackgroundColor(ContextCompat.getColor(context,tempoDates.get(position).getCouleur().getResId()));
    }


    @Override
    public int getItemCount() {
        return tempoDates.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
