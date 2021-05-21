package com.kavramatik.kavramatik.model;

import com.google.gson.annotations.SerializedName;

public class NumberModel {

    @SerializedName("id")
    private int id;

    @SerializedName("number_name")
    private String numberName;

    @SerializedName("number_one_text")
    private String numberText;

    @SerializedName("number_quantity_text")
    private String numberImageText;

    @SerializedName("number_one_image")
    private String numberImage;

    @SerializedName("number_quantity_image")
    private String numberQuantityImage;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumberName() {
        return numberName;
    }

    public void setNumberName(String numberName) {
        this.numberName = numberName;
    }

    public String getNumberText() {
        return numberText;
    }

    public void setNumberText(String numberText) {
        this.numberText = numberText;
    }

    public String getNumberImageText() {
        return numberImageText;
    }

    public void setNumberImageText(String numberImageText) {
        this.numberImageText = numberImageText;
    }

    public String getNumberImage() {
        return numberImage;
    }

    public void setNumberImage(String numberImage) {
        this.numberImage = numberImage;
    }

    public String getNumberQuantityImage() {
        return numberQuantityImage;
    }

    public void setNumberQuantityImage(String numberQuantityImage) {
        this.numberQuantityImage = numberQuantityImage;
    }
}
