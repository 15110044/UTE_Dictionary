package com.at.bd_dictionary.model;

import android.widget.ImageView;

import java.io.Serializable;


public class Dictionary implements Serializable {
    String tu;
    String nghia;
    String phienam;
    String dongnghia;
    String trainghia;
    String mp3;
    String anhminhhoa;

    public String getTu() {
        return tu;
    }

    public void setTu(String tu) {
        this.tu = tu;
    }

    public String getNghia() {
        return nghia;
    }

    public void setNghia(String nghia) {
        this.nghia = nghia;
    }

    public String getPhienam() {
        return phienam;
    }

    public void setPhienam(String phienam) {
        this.phienam = phienam;
    }

    public String getDongnghia() {
        return dongnghia;
    }

    public void setDongnghia(String dongnghia) {
        this.dongnghia = dongnghia;
    }

    public String getTrainghia() {
        return trainghia;
    }

    public void setTrainghia(String trainghia) {
        this.trainghia = trainghia;
    }

    public String getMp3() {
        return mp3;
    }

    public void setMp3(String mp3) {
        this.mp3 = mp3;
    }

    public String getAnhminhhoa() {
        return anhminhhoa;
    }

    public void setAnhminhhoa(String anhminhhoa) {
        this.anhminhhoa = anhminhhoa;
    }
}
