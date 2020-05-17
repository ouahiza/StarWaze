package com.example.starwaze.modeles;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class Apod {

    private String copyright;
    private Date date;
    private String explanation;
    private String hdurl;
    private String media_type;
    private String service_version;
    @SerializedName("title")
    private String apodTitle;
    private String url;

    public String getCopyright() {
        return copyright;
    }

    public Date getDate() {
        return date;
    }

    public String getExplanation() {
        return explanation;
    }

    public String getHdurl() {
        return hdurl;
    }

    public String getMedia_type() {
        return media_type;
    }

    public String getService_version() {
        return service_version;
    }

    public String getApodTitle() {
        return apodTitle;
    }

    public String getUrl() {
        return url;
    }
}
