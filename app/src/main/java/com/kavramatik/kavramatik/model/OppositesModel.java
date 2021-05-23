package com.kavramatik.kavramatik.model;

import com.google.gson.annotations.SerializedName;

public class OppositesModel {

    @SerializedName("id")
    private String id;

    @SerializedName("opposite_name")
    private String oppositeName;

    @SerializedName("opposite_one_image_text")
    private String oppositeOneImageText;

    @SerializedName("opposite_two_image_text")
    private String oppositeTwoImageText;

    @SerializedName("opposite_one_image")
    private String oppositeOneImage;

    @SerializedName("opposite_two_image")
    private String oppositeTwoImage;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOppositeName() {
        return oppositeName;
    }

    public void setOppositeName(String oppositeName) {
        this.oppositeName = oppositeName;
    }

    public String getOppositeOneImageText() {
        return oppositeOneImageText;
    }

    public void setOppositeOneImageText(String oppositeOneImageText) {
        this.oppositeOneImageText = oppositeOneImageText;
    }

    public String getOppositeTwoImageText() {
        return oppositeTwoImageText;
    }

    public void setOppositeTwoImageText(String oppositeTwoImageText) {
        this.oppositeTwoImageText = oppositeTwoImageText;
    }

    public String getOppositeOneImage() {
        return oppositeOneImage;
    }

    public void setOppositeOneImage(String oppositeOneImage) {
        this.oppositeOneImage = oppositeOneImage;
    }

    public String getOppositeTwoImage() {
        return oppositeTwoImage;
    }

    public void setOppositeTwoImage(String oppositeTwoImage) {
        this.oppositeTwoImage = oppositeTwoImage;
    }
}
