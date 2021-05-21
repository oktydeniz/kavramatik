package com.kavramatik.kavramatik.service;

import com.kavramatik.kavramatik.model.ColorModel;
import com.kavramatik.kavramatik.model.DimensionModel;
import com.kavramatik.kavramatik.model.DirectionModel;
import com.kavramatik.kavramatik.model.EmotionModel;
import com.kavramatik.kavramatik.model.NumberModel;
import com.kavramatik.kavramatik.model.ShapeModel;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.GET;

public interface EducationAPI {

    @GET("colors?token_id=464685648465A468464qw8A544688648W6REEWT6V")
    Single<List<ColorModel>> getColors();

    @GET("shapes?token_id=464685648465A468464qw8A544688648W6REEWT6V")
    Single<List<ShapeModel>> getShapes();

    @GET("directions?token_id=464685648465A468464qw8A544688648W6REEWT6V")
    Single<List<DirectionModel>> getDirections();

    @GET("numbers?token_id=464685648465A468464qw8A544688648W6REEWT6V")
    Single<List<NumberModel>> getNumbers();

    @GET("emotions?token_id=464685648465A468464qw8A544688648W6REEWT6V")
    Single<List<EmotionModel>> getEmotions();

    @GET("dimensions?token_id=464685648465A468464qw8A544688648W6REEWT6V")
    Single<List<DimensionModel>> getDimensions();
}