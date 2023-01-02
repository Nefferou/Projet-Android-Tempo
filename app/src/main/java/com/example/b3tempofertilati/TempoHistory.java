package com.example.b3tempofertilati;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;
/**
 * This class was created with a POJO generator (http://www.jsonschema2pojo.org/) from the
 * following JSON sample:
 *
 * {"dates":
 *  [
 *   {"date":"2020-09-01","couleur":"TEMPO_BLEU"},
 *   {"date":"2020-09-02","couleur":"TEMPO_BLEU"},
 *   {"date":"2021-01-30","couleur":"TEMPO_BLEU"}
 *  ]
 * }
 *
 */
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
