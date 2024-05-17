package com.codegym.model.DTO;

import org.springframework.web.multipart.MultipartFile;


public class MusicForm {

    private int id;

    private String name;

    private String artist;

    private String musicGenre;

    private MultipartFile song;

    public MusicForm() {

    }

    public MusicForm(int id, String name, String artist, String musicGenre, MultipartFile song) {
        this.id = id;
        this.name = name;
        this.artist = artist;
        this.musicGenre = musicGenre;
        this.song = song;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getMusicGenre() {
        return musicGenre;
    }

    public void setMusicGenre(String musicGenre) {
        this.musicGenre = musicGenre;
    }

    public MultipartFile getSong() {
        return song;
    }

    public void setSong(MultipartFile song) {
        this.song = song;
    }
}
