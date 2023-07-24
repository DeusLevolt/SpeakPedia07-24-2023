package com.example.speakpedia;

import com.google.gson.annotations.SerializedName;

public class WordResponse {
    @SerializedName("hwi")
    private Hwi hwi;

    @SerializedName("shortdef")
    private String[] shortDef;

    @SerializedName("def")
    private Definition[] definitions;

    // Getters and setters...

    public Hwi getHwi() {
        return hwi;
    }

    public void setHwi(Hwi hwi) {
        this.hwi = hwi;
    }

    public String[] getShortDef() {
        return shortDef;
    }

    public void setShortDef(String[] shortDef) {
        this.shortDef = shortDef;
    }

    public Definition[] getDefinitions() {
        return definitions;
    }

    public void setDefinitions(Definition[] definitions) {
        this.definitions = definitions;
    }
}

class Hwi {
    @SerializedName("prs")
    private Prs[] phonetics;

    // Getters and setters...

    public Prs[] getPhonetics() {
        return phonetics;
    }

    public void setPhonetics(Prs[] phonetics) {
        this.phonetics = phonetics;
    }
}

class Prs {
    @SerializedName("mw")
    private String mw;

    // Getters and setters...

    public String getMw() {
        return mw;
    }

    public void setMw(String mw) {
        this.mw = mw;
    }
}

class Definition {
    @SerializedName("sseq")
    private Object[][] sseq;

    // Getters and setters...

    public Object[][] getSseq() {
        return sseq;
    }

    public void setSseq(Object[][] sseq) {
        this.sseq = sseq;
    }
}
