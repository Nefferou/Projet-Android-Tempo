package com.example.b3tempofertilati;

import com.google.gson.annotations.SerializedName;

public enum TempoColor {

    @SerializedName("TEMPO_ROUGE")
    RED(R.color.tempo_red_day_bg, R.string.tempo_red_color_text),

    @SerializedName("TEMPO_BLANC")
    WHITE(R.color.tempo_white_day_bg, R.string.tempo_white_color_text),

    @SerializedName("TEMPO_BLEU")
    BLUE(R.color.tempo_blue_day_bg, R.string.tempo_blue_color_text),

    @SerializedName("NON_DEFINI")
    UNKNOWN(R.color.tempo_undecided_day_bg, R.string.tempo_undecided_color_text);

    private final int colorResId;
    private final int stringResId;

    // Ctor
    TempoColor(int colorResId, int stringResId) {
        this.colorResId = colorResId;
        this.stringResId =stringResId;
    }

    public int getColorResId() {
        return colorResId;
    }

    public int getStringResId() {
        return stringResId;
    }
}
