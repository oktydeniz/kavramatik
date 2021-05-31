package com.kavramatik.kavramatik.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.kavramatik.kavramatik.model.BaseModel;
import com.kavramatik.kavramatik.model.ColorModel;
import com.kavramatik.kavramatik.model.DimensionModel;
import com.kavramatik.kavramatik.model.DirectionModel;
import com.kavramatik.kavramatik.model.EmotionModel;
import com.kavramatik.kavramatik.model.NumberModel;
import com.kavramatik.kavramatik.model.OppositesModel;
import com.kavramatik.kavramatik.model.QuantityModel;
import com.kavramatik.kavramatik.model.SenseModel;
import com.kavramatik.kavramatik.model.ShapeModel;

import java.util.List;

@Dao
public interface EducationDao {

    //Insert
    @Insert
    void insertAllColor(List<ColorModel> model);

    @Insert
    void insertAllDimension(List<DimensionModel> model);

    @Insert
    void insertAllDirection(List<DirectionModel> model);

    @Insert
    void insertAllEmotion(List<EmotionModel> model);

    @Insert
    void insertAllNumber(List<NumberModel> model);

    @Insert
    void insertAllOpposites(List<OppositesModel> model);

    @Insert
    void insertAllQuantity(List<QuantityModel> model);

    @Insert
    void insertAllSense(List<SenseModel> model);

    @Insert
    void insertAllShape(List<ShapeModel> models);

    //Select
    @Query("SELECT * FROM ColorModel")
    List<ColorModel> getAllColor();

    @Query("SELECT * FROM DimensionModel")
    List<DimensionModel> getAllDimension();

    @Query("SELECT * FROM DirectionModel")
    List<DirectionModel> getAllDirection();

    @Query("SELECT * FROM EmotionModel")
    List<EmotionModel> getAllEmotion();

    @Query("SELECT * FROM NumberModel")
    List<NumberModel> getAllNumber();

    @Query("SELECT * FROM OppositesModel")
    List<OppositesModel> getAllOpposites();

    @Query("SELECT * FROM QuantityModel")
    List<QuantityModel> getAllQuantity();

    @Query("SELECT * FROM SenseModel")
    List<SenseModel> getAllSense();

    @Query("SELECT * FROM ShapeModel")
    List<ShapeModel> getAllShape();

    //Delete
    @Query("DELETE FROM ColorModel ")
    void deleteColors();

    //Delete
    @Query("DELETE FROM DimensionModel ")
    void deleteDimension();

    //Delete
    @Query("DELETE FROM DirectionModel ")
    void deleteDirection();

    //Delete
    @Query("DELETE FROM EmotionModel ")
    void deleteEmotion();

    //Delete
    @Query("DELETE FROM NumberModel ")
    void deleteNumber();

    //Delete
    @Query("DELETE FROM OppositesModel ")
    void deleteOpposites();

    //Delete
    @Query("DELETE FROM QuantityModel ")
    void deleteQuantity();

    //Delete
    @Query("DELETE FROM SenseModel ")
    void deleteSense();

    //Delete
    @Query("DELETE FROM ShapeModel ")
    void deleteShape();
}
