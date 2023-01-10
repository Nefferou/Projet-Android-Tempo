package com.example.b3tempofertilati;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TempoHistory {
    @SerializedName("dates")
    @Expose
    private List<TempoDate> dates = null;

    public List<TempoDate> getTempoDates() {
        return dates;
    }

    public void setTempoDates(List<TempoDate> dates) {
        this.dates = dates;
    }
}
