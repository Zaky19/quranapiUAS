package com.example.quranapiuas.retrofit;

import com.example.quranapiuas.suratmodel.sekat;
import com.example.quranapiuas.ayatmodel.chapters;
import com.example.quranapiuas.Arti.idn;
import com.example.quranapiuas.audiomodel.audio;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiEndPoint {
    @GET("chapters?language=id")
    Call<chapters> getSurah();
    @GET("quran/verses/uthmani?")
    Call<sekat> getAyat (@Query("chapter_number") int id);
    @GET("quran/translations/33?")
    Call<idn> getIndo (@Query("chapter_number") int id);
    @GET("chapter_recitations/33?")
    Call<audio> getAudio();

}
