package com.example.quranapiuas.suratmodel;

import com.google.gson.annotations.SerializedName;

public class sekatitem{

    @SerializedName("verse_key")
    private String verseKey;

    @SerializedName("text_uthmani")
    private String textUthmani;

    @SerializedName("id")
    private int id;

    public String getVerseKey(){
        return verseKey;
    }

    public String getTextUthmani(){
        return textUthmani;
    }

    public int getId(){
        return id;
    }

    @Override
    public String toString(){
        return
                "VersesItem{" +
                        "verse_key = '" + verseKey + '\'' +
                        ",text_uthmani = '" + textUthmani + '\'' +
                        ",id = '" + id + '\'' +
                        "}";
    }
}
