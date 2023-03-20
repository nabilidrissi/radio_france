package com.nabilRadioFrance.radio;

public class Radio {
    String nomRadio,url;
    int imageId;

    public Radio(String nomRadio, String url, int imageId) {
        this.nomRadio = nomRadio;
        this.url = url;
        this.imageId = imageId;
    }

    public String getNomRadio() {
        return nomRadio;
    }

    public String getUrl() {
        return url;
    }

    public int getImageId() {
        return imageId;
    }

    public void setNomRadio(String nomRadio) {
        this.nomRadio = nomRadio;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }
}
