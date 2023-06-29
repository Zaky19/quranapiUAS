package com.example.quranapiuas.ayatmodel;

import com.google.gson.annotations.SerializedName;

public class translateName{

    @SerializedName("name")
    private String name;

    @SerializedName("language_name")
    private String languageName;

    public String getName(){
        return name;
    }

    public String getLanguageName(){
        return languageName;
    }

    @Override
    public String toString(){
        return
                "TranslatedName{" +
                        "name = '" + name + '\'' +
                        ",language_name = '" + languageName + '\'' +
                        "}";
    }
}
