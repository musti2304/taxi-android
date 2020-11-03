package com.mustafayousef.taxiviewer;

public class Halteplatz {

    private String mName;
    private int mAuftraege;
    private int mEinstiege;
    private String mWartezeit;


    public Halteplatz(String mName, int mAuftraege, int mEinstiege, String mWartezeit) {
        this.mName = mName;
        this.mAuftraege = mAuftraege;
        this.mEinstiege = mEinstiege;
        this.mWartezeit = mWartezeit;
    }

    public String getName() {
        return mName;
    }

    public int getAuftraege() {
        return mAuftraege;
    }

    public int getEinstiege() {
        return mEinstiege;
    }

    public String getWartezeit() {
        return mWartezeit;
    }

}
