package com.example.b3tempofertilati;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
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
public class TempoDate {

    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("couleur")
    @Expose
    private TempoColor couleur;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public TempoColor getCouleur() {
        return couleur;
    }

    public void setCouleur(TempoColor couleur) {
        this.couleur = couleur;
    }
}
