package com.example.b3tempofertilati;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TempoDaysLeft {

    @SerializedName("PARAM_NB_J_BLANC")
    @Expose
    private Integer paramNbJBlanc;
    @SerializedName("PARAM_NB_J_ROUGE")
    @Expose
    private Integer paramNbJRouge;
    @SerializedName("PARAM_NB_J_BLEU")
    @Expose
    private Integer paramNbJBleu;

    public Integer getParamNbJBlanc() {
        return paramNbJBlanc;
    }

    public void setParamNbJBlanc(Integer paramNbJBlanc) {
        this.paramNbJBlanc = paramNbJBlanc;
    }

    public Integer getParamNbJRouge() {
        return paramNbJRouge;
    }

    public void setParamNbJRouge(Integer paramNbJRouge) {
        this.paramNbJRouge = paramNbJRouge;
    }

    public Integer getParamNbJBleu() {
        return paramNbJBleu;
    }

    public void setParamNbJBleu(Integer paramNbJBleu) {
        this.paramNbJBleu = paramNbJBleu;
    }

}