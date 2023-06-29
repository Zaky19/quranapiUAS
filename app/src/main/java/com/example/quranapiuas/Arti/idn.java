package com.example.quranapiuas.Arti;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class idn{

    @SerializedName("translations")
    private List<translateitem> translations;


    public List<translateitem> getTranslations(){
        return translations;
    }


    @Override
    public String toString(){
        return
                "Indo{" +
                        "translations = '" + translations +
                        "}";
    }
}
