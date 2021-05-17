package com.kavramatik.kavramatik.model;

import com.google.gson.annotations.SerializedName;

public class ColorModel {

    @SerializedName("id")
    private int id;

    @SerializedName("color_name")
    private String colorName;

    @SerializedName("color_text")
    private String colorText;

    @SerializedName("color_image")
    private String colorImage;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getColorName() {
        return colorName;
    }

    public void setColorName(String colorName) {
        this.colorName = colorName;
    }

    public String getColorText() {
        return colorText;
    }

    public void setColorText(String colorText) {
        this.colorText = colorText;
    }

    public String getColorImage() {
        return colorImage;
    }

    public void setColorImage(String colorImage) {
        this.colorImage = colorImage;
    }
}
