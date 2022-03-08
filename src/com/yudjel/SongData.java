package com.yudjel;

/*
A song has: a title, an artist, pure sales, paid streams, free streams, programmed streams and radio

*/

public class SongData {

    private String title;
    private String artist;
    private int pureSales;
    private int paidStreams;
    private int freeStreams;
    private int programmedStreams;
    private int radio;

    public SongData(String title, String artist, int pureSales, int paidStreams, int freeStreams, int programmedStreams, int radio) {
        this.title = title;
        this.artist = artist;
        this.pureSales = pureSales;
        this.paidStreams = paidStreams;
        this.freeStreams = freeStreams;
        this.programmedStreams = programmedStreams;
        this.radio = radio;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public int getPureSales() {
        return pureSales;
    }

    public void setPureSales(int pureSales) {
        this.pureSales = pureSales;
    }

    public int getPaidStreams() {
        return paidStreams;
    }

    public void setPaidStreams(int paidStreams) {
        this.paidStreams = paidStreams;
    }

    public int getFreeStreams() {
        return freeStreams;
    }

    public void setFreeStreams(int freeStreams) {
        this.freeStreams = freeStreams;
    }

    public int getProgrammedStreams() {
        return programmedStreams;
    }

    public void setProgrammedStreams(int programmedStreams) {
        this.programmedStreams = programmedStreams;
    }

    public int getRadio() {
        return radio;
    }

    public void setRadio(int radio) {
        this.radio = radio;
    }

    @Override
    public String toString() {
        return  this.artist +
                " - " + this.title +
                "\n     Pure Sales: " + this.pureSales +
                "\n     Paid Streams: " + this.paidStreams +
                "\n     Free Streams: " + this.freeStreams +
                "\n     Programmed Streams: " + this.programmedStreams +
                "\n     Radio: " + this.radio;
    }

    public String toStringArtistAndTitle(){
        return "Title: " + title + ", Artist: " + artist;
    }
}
