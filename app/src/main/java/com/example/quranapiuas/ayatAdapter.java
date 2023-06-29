package com.example.quranapiuas;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quranapiuas.suratmodel.sekatitem;
import com.example.quranapiuas.Arti.translateitem;

import java.util.List;

public class ayatAdapter extends RecyclerView.Adapter<ayatAdapter.ViewHolder> {

    private static List<sekatitem> lista;
    private static List<translateitem> listb;
    public ayatAdapter(List<sekatitem> lista, List<translateitem> listb){
        this.lista = lista;
        this.listb = listb;
    }
    @NonNull
    @Override
    public ayatAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(R.layout.ayat, parent, false)
        );
    }
    @Override
    public void onBindViewHolder(@NonNull ayatAdapter.ViewHolder holder, int position) {
        sekatitem list = lista.get(position);
        translateitem list_ = listb.get(position);

        holder.textayat.setText(list.getTextUthmani());
        holder.textarti.setText(list_.getText());
        holder.textnomor.setText(String.valueOf(position + 1));
    }
    @Override
    public int getItemCount() {
        return listb.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textayat, textnomor, textarti;

        public ViewHolder(View itemView){
            super(itemView);

            textayat = itemView.findViewById(R.id.awal);
            textnomor = itemView.findViewById(R.id.no);
            textarti = itemView.findViewById(R.id.arti);
        }
    }
    public void setData(List<sekatitem> data, List<translateitem> data1) {

        lista.clear();
        lista.addAll(data);

        listb.clear();
        listb.addAll(data1);
        notifyDataSetChanged();
    }

}