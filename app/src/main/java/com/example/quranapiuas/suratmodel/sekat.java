package com.example.quranapiuas.suratmodel;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class sekat{

    @SerializedName("meta")
    private mutu meta;

    @SerializedName("verses")
    private List<sekatitem> verses;

    public mutu getMeta(){
        return meta;
    }

    public List<sekatitem> getVerses(){
        return verses;
    }

    @Override
    public String toString(){
        return
                "Verses{" +
                        "meta = '" + meta + '\'' +
                        ",verses = '" + verses + '\'' +
                        "}";
    }
}
