package com.example.b3tempofertilati;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TempoDaysColor {

    @SerializedName("couleurJourJ")
    @Expose
    private TempoColor couleurJourJ;
    @SerializedName("couleurJourJ1")
    @Expose
    private TempoColor couleurJourJ1;

    public TempoColor getCouleurJourJ() {
        return couleurJourJ;
    }

    public void setCouleurJourJ(TempoColor couleurJourJ) {
        this.couleurJourJ = couleurJourJ;
    }

    public TempoColor getCouleurJourJ1() {
        return couleurJourJ1;
    }

    public void setCouleurJourJ1(TempoColor couleurJourJ1) {
        this.couleurJourJ1 = couleurJourJ1;
    }

}
