package com.example.quranapiuas.ayatmodel;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class chapters {

    @SerializedName("chapters")
    private List<chaptersItem> chapters;

    public void setChapters(List<chaptersItem> chapters){
        this.chapters = chapters;
    }

    public List<chaptersItem> getChapters(){
        return chapters;
    }

    @Override
    public String toString(){
        return
                "Chapters{" +
                        "chapters = '" + chapters + '\'' +
                        "}";
    }
}
