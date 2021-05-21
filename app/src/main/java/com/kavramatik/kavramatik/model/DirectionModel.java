package com.kavramatik.kavramatik.model;

import com.google.gson.annotations.SerializedName;

public class DirectionModel {

    @SerializedName("id")
    private int id;

    @SerializedName("direction_name")
    private String directionName;

    @SerializedName("direction_text")
    private String directionText;

    @SerializedName("direction_image")
    private String directionImage;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDirectionName() {
        return directionName;
    }

    public void setDirectionName(String directionName) {
        this.directionName = directionName;
    }

    public String getDirectionText() {
        return directionText;
    }

    public void setDirectionText(String directionText) {
        this.directionText = directionText;
    }

    public String getDirectionImage() {
        return directionImage;
    }

    public void setDirectionImage(String directionImage) {
        this.directionImage = directionImage;
    }
}
