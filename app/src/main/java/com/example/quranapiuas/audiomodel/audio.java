package com.example.quranapiuas.audiomodel;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class audio {
    @SerializedName("audio_files")
    private List<audiofile> audioFiles;

    public List<audiofile> getAudioFiles(){
        return audioFiles;
    }

    @Override
    public String toString(){
        return
                "Audio{" +
                        "audio_files = '" + audioFiles + '\'' +
                        "}";
    }
}
