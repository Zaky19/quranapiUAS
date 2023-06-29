package com.example.quranapiuas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.example.quranapiuas.audiomodel.audio;
import com.example.quranapiuas.audiomodel.audiofile;
import com.example.quranapiuas.ayatmodel.chapters;
import com.example.quranapiuas.ayatmodel.chaptersItem;
import com.example.quranapiuas.retrofit.ApiService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    private MainAdapter mainAdapter;

    private List<chaptersItem> results = new ArrayList<>();
    private List<audiofile> audio = new ArrayList<>();
    private List<chaptersItem> listSurah;
    private List<audiofile> listAudio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setUpView();
        setUpRecyclerView();
        getDataFromApi();
    }

    private void setUpRecyclerView() {
        mainAdapter = new MainAdapter(results, audio);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(mainAdapter);
    }

    private void setUpView() {
        recyclerView = findViewById(R.id.recyclerView);
    }

    private void getDataFromApi() {
        ApiService.endpoint().getSurah().enqueue(new Callback<chapters>() {
            @Override
            public void onResponse(@NonNull Call<chapters> call, @NonNull Response<chapters> response) {
                if (response.isSuccessful()) {
                    assert response.body() != null;
                    MainActivity.this.listSurah = response.body().getChapters();
                    getDataFromApiAudio();
                }
            }

            @Override
            public void onFailure(@NonNull Call<chapters> call, @NonNull Throwable t) {
                Log.d("ErrorMain", t.toString());
            }
        });
    }

    private void getDataFromApiAudio() {
        ApiService.endpoint().getAudio().enqueue(new Callback<audio>() {
            @Override
            public void onResponse(@NonNull Call<audio> call, @NonNull Response<audio> response) {
                if (response.isSuccessful()) {
                    assert response.body() != null;
                    MainActivity.this.listAudio = response.body().getAudioFiles();
                    mainAdapter.setData(listSurah, listAudio);
                }
            }

            @Override
            public void onFailure(@NonNull Call<audio> call, @NonNull Throwable t) {

            }
        });
    }
}
