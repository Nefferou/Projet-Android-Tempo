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

import java.util.List;

public class TempoDateAdapter extends RecyclerView.Adapter<TempoDateAdapter.TempoDateViewHolder> {
    private List<TempoDate> tempoDates;
    private Context context;

    public class TempoDateViewHolder extends RecyclerView.ViewHolder {
        private TextView dateTv;
        private FrameLayout colorFl;


        public TempoDateViewHolder(View view) {
            super(view);
            dateTv = itemView.findViewById(R.id.date_tv);
            colorFl = itemView.findViewById(R.id.color_fl);
        }

    }

    public TempoDateAdapter(List<TempoDate> tempoDates, Context context){
        this.tempoDates = tempoDates;
        this.context = context;
    }
    @NonNull
    @Override
    public TempoDateViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.tempo_date_item,parent,false);
        return new TempoDateViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull TempoDateViewHolder holder, int position) {
        holder.dateTv.setText(tempoDates.get(position).getDate());
        holder.colorFl.setBackgroundColor(ContextCompat.getColor(context,tempoDates.get(position).getCouleur().getResId()));
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
