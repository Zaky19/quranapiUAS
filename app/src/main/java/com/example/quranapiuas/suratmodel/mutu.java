package com.example.quranapiuas.suratmodel;

import com.google.gson.annotations.SerializedName;

public class mutu {

    @SerializedName("filters")
    private filter filters;

    public filter getFilters() {
        return filters;
    }

    @Override
    public String toString() {
        return
                "Meta{" +
                        "filters = '" + filters + '\'' +
                        "}";
    }
}
