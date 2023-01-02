package com.example.b3tempofertilati;

import com.google.gson.annotations.SerializedName;

public enum TempoColor {

    @SerializedName("TEMPO_ROUGE")
    RED(R.color.tempo_red_day_bg),

    @SerializedName("TEMPO_BLANC")
    WHITE(R.color.tempo_white_day_bg),

    @SerializedName("TEMPO_BLEU")
    BLUE(R.color.tempo_blue_day_bg),

    @SerializedName("NON_DEFINI")
    UNKNOWN(R.color.tempo_undecided_day_bg);

    private int resId;

    // Ctor
    TempoColor(int resId) {
        this.resId = resId;
    }

    public int getResId() {
        return resId;
    }
}
