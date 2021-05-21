package com.kavramatik.kavramatik.model;

import com.google.gson.annotations.SerializedName;

public class DimensionModel {
    @SerializedName("id")
    private int id;

    @SerializedName("dimension_name")
    private String dimensionName;

    @SerializedName("dimension_text")
    private String dimensionText;

    @SerializedName("dimension_one_text")
    private String dimensionImageTextOne;

    @SerializedName("dimension_two_text")
    private String dimensionImageTextTwo;

    @SerializedName("dimension_one_image")
    private String imageOne;

    @SerializedName("dimension_two_image")
    private String imageTwo;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDimensionName() {
        return dimensionName;
    }

    public void setDimensionName(String dimensionName) {
        this.dimensionName = dimensionName;
    }

    public String getDimensionText() {
        return dimensionText;
    }

    public void setDimensionText(String dimensionText) {
        this.dimensionText = dimensionText;
    }

    public String getDimensionImageTextOne() {
        return dimensionImageTextOne;
    }

    public void setDimensionImageTextOne(String dimensionImageTextOne) {
        this.dimensionImageTextOne = dimensionImageTextOne;
    }

    public String getDimensionImageTextTwo() {
        return dimensionImageTextTwo;
    }

    public void setDimensionImageTextTwo(String dimensionImageTextTwo) {
        this.dimensionImageTextTwo = dimensionImageTextTwo;
    }

    public String getImageOne() {
        return imageOne;
    }

    public void setImageOne(String imageOne) {
        this.imageOne = imageOne;
    }

    public String getImageTwo() {
        return imageTwo;
    }

    public void setImageTwo(String imageTwo) {
        this.imageTwo = imageTwo;
    }
}
