package com.example.vuongvanhau.ute_dictionaty;

public class Dictionary {

    private String word;
    public String getWord() {
        return word;
    }
    public void setWord(String word){this.word = word;}

    private String nghia;
    public String getNghia() {
        return nghia;
    }
    public void setNghia(String nghia){this.nghia = nghia;}

    private String dnghia;
    public String getDnghia() {
        return dnghia;
    }
    public void setDnghia(String dnghia){this.dnghia = dnghia;}

    private String image;
    public String getImage() {
        return image;
    }
    public void setImage(String image){this.image = image;}

    public Dictionary() {

    }
    public Dictionary(String word,String nghia, String dnghia, String image) {
        this.word=word;
        this.nghia = nghia;
        this.dnghia = dnghia;
        this.image=image;
    }
}
