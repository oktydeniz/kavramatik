package com.kavramatik.kavramatik.model;

import com.google.gson.annotations.SerializedName;

public class EmotionModel {

    @SerializedName("id")
    private int id;

    @SerializedName("emotion_name")
    private String emotionName;

    @SerializedName("emotion_text")
    private String emotionText;

    @SerializedName("emotion_image")
    private String emotionImage;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmotionName() {
        return emotionName;
    }

    public void setEmotionName(String emotionName) {
        this.emotionName = emotionName;
    }

    public String getEmotionText() {
        return emotionText;
    }

    public void setEmotionText(String emotionText) {
        this.emotionText = emotionText;
    }

    public String getEmotionImage() {
        return emotionImage;
    }

    public void setEmotionImage(String emotionImage) {
        this.emotionImage = emotionImage;
    }
}
