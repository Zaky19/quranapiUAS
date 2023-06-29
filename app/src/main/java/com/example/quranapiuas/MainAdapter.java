package com.example.quranapiuas;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quranapiuas.audiomodel.audiofile;
import com.example.quranapiuas.ayatmodel.chaptersItem;

import java.util.List;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {

    private List<chaptersItem> results;
    private List<audiofile> audio;

    public MainAdapter(List<chaptersItem> results,List<audiofile> result) {
        this.results = results;
        this.audio = result;
    }

    @NonNull
    @Override
    public MainAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MainAdapter.ViewHolder holder, int position) {
        final chaptersItem chapters = results.get(position);
        audiofile aud = audio.get(position);

        holder.textViewSurahLatin.setText(chapters.getNameSimple());
        holder.textViewTerjemahanSurah.setText(chapters.getTranslatedName().getName());
        holder.textViewSurahArab.setText(chapters.getNameArabic());

        holder.itemView.setOnClickListener(view -> {
            Intent intent = new Intent(holder.itemView.getContext(), DetailSurah.class);
            intent.putExtra("surat", chapters.getNameSimple());
            intent.putExtra("arab", chapters.getNameArabic());
            intent.putExtra("sekat", chapters.getVersesCount());
            intent.putExtra("latin", chapters.getNameComplex());
            intent.putExtra("id", chapters.getId());
            intent.putExtra("audio_url", aud.getAudioUrl());
            holder.itemView.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return results.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textViewSurahLatin , textViewTerjemahanSurah, textViewSurahArab;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewSurahLatin = itemView.findViewById(R.id.tvSurahLatin);
            textViewTerjemahanSurah = itemView.findViewById(R.id.tvTerjemahanSurah);
            textViewSurahArab = itemView.findViewById(R.id.tvSurahArab);
        }
    }

    public void setData(List<chaptersItem> data, List<audiofile> data1){
        results.clear();
        results.addAll(data);
        audio.clear();
        audio.addAll(data1);
        notifyDataSetChanged();
    }
}