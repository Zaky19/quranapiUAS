package com.example.quranapiuas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.example.quranapiuas.suratmodel.sekat;
import com.example.quranapiuas.suratmodel.sekatitem;
import com.example.quranapiuas.Arti.idn;
import com.example.quranapiuas.Arti.translateitem;
import com.example.quranapiuas.retrofit.ApiService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class DetailSurah extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ayatAdapter ayatAdapter;
    private final List<sekatitem> versesItemList = new ArrayList<>();
    private final List<translateitem> translationsItemList = new ArrayList<>();
    List<sekatitem> ayat;
    List<translateitem> arti;
    TextView namalengkap, namapendek, jumlah, no, arab;
    Button audio;
    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_surah);

        int id = getIntent().getIntExtra("id", 1);
        no = findViewById(R.id.no1);
        no.setText(String.valueOf(id));

        String namaLengkap = getIntent().getStringExtra("latin");
        namalengkap = findViewById(R.id.first);
        namalengkap.setText((namaLengkap));

        String Latin = getIntent().getStringExtra("surat");
        namapendek = findViewById(R.id.surat);
        namapendek.setText((Latin));

        String Arab = getIntent().getStringExtra("arab");
        arab = findViewById(R.id.arab);
        arab.setText("Nama Arab " + Arab);

        int totalAyat = getIntent().getIntExtra("sekat", 1);
        jumlah = findViewById(R.id.total);
        jumlah.setText("Jumlah Ayat " + totalAyat);

        mediaPlayer = new MediaPlayer();
        String audioUrl = getIntent().getStringExtra("audio_url");
        audio = findViewById(R.id.tekan);
        audio.setOnClickListener(view -> {
            if (mediaPlayer.isPlaying()) {
                pauseAudio();
            } else {
                playAudio(audioUrl);
            }
        });

        setUpView();
        setUpRecyclerView();
        System.out.println(id);
        getDatafromApi(id);
    }

    private void pauseAudio() {
        if (mediaPlayer.isPlaying()) {
            mediaPlayer.pause();
        }
    }

    private void playAudio(String audio) {
        try {
            mediaPlayer.reset();
            mediaPlayer.setDataSource(audio);
            mediaPlayer.prepare();
            mediaPlayer.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void getDatafromApi(int id) {
        ApiService.endpoint().getIndo(id).enqueue(new Callback<idn>() {
            @Override
            public void onResponse(@NonNull Call<idn> call, @NonNull Response<idn> response) {
                if (response.isSuccessful()) {
                    assert response.body() != null;
                    DetailSurah.this.arti = response.body().getTranslations();
                    getVersesData(getIntent().getIntExtra("id", 1));
                }
            }
            @Override
            public void onFailure(@NonNull Call<idn> call, @NonNull Throwable t) {
                Log.d("error", t.toString());
            }
        });
    }

    private void getVersesData(int id) {
        ApiService.endpoint().getAyat(id).enqueue(new Callback<sekat>() {
            @Override
            public void onResponse(@NonNull Call<sekat> call, @NonNull Response<sekat> response) {
                if (response.isSuccessful()) {
                    assert response.body() != null;
                    DetailSurah.this.ayat = response.body().getVerses();
                    ayatAdapter.setData(ayat, arti);
                }
            }

            @Override
            public void onFailure(@NonNull Call<sekat> call, @NonNull Throwable t) {
                Log.d("error", t.toString());
            }
        });
    }

    private void setUpRecyclerView() {
        ayatAdapter = new ayatAdapter(versesItemList, translationsItemList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(ayatAdapter);
    }

    private void setUpView() {
        recyclerView = findViewById(R.id.tvAyat);
    }
}