package com.kavramatik.kavramatik.model;

import com.google.gson.annotations.SerializedName;

public class ShapeModel {
    @SerializedName("id")
    private int id;

    @SerializedName("shape_name")
    private String shapeName;

    @SerializedName("shape_text")
    private String ShapeText;

    @SerializedName("shape_image")
    private String shapeImage;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getShapeName() {
        return shapeName;
    }

    public void setShapeName(String shapeName) {
        this.shapeName = shapeName;
    }

    public String getShapeText() {
        return ShapeText;
    }

    public void setShapeText(String shapeText) {
        ShapeText = shapeText;
    }

    public String getShapeImage() {
        return shapeImage;
    }

    public void setShapeImage(String shapeImage) {
        this.shapeImage = shapeImage;
    }
}
