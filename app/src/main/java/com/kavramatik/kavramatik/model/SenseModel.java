package com.kavramatik.kavramatik.model;

import com.google.gson.annotations.SerializedName;

public class SenseModel {

    @SerializedName("id")
    private int id;

    @SerializedName("sense_name")
    private String senseName;

    @SerializedName("sense_one_image_text")
    private String senseOneText;

    @SerializedName("sense_two_image_text")
    private String senseTwoText;

    @SerializedName("sense_one_image")
    private String senseOneImage;

    @SerializedName("sense_two_image")
    private String senseTwoImage;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSenseName() {
        return senseName;
    }

    public void setSenseName(String senseName) {
        this.senseName = senseName;
    }

    public String getSenseOneText() {
        return senseOneText;
    }

    public void setSenseOneText(String senseOneText) {
        this.senseOneText = senseOneText;
    }

    public String getSenseTwoText() {
        return senseTwoText;
    }

    public void setSenseTwoText(String senseTwoText) {
        this.senseTwoText = senseTwoText;
    }

    public String getSenseOneImage() {
        return senseOneImage;
    }

    public void setSenseOneImage(String senseOneImage) {
        this.senseOneImage = senseOneImage;
    }

    public String getSenseTwoImage() {
        return senseTwoImage;
    }

    public void setSenseTwoImage(String senseTwoImage) {
        this.senseTwoImage = senseTwoImage;
    }
}
