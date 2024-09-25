package br.ifmg.edu.bsi.progmovel.photojar1.model;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface PhotoDao {

    @Insert
    void insert(PhotoRecord photoRecord);

    @Query("SELECT * FROM photos ORDER BY RANDOM() LIMIT 1")
    PhotoRecord getRandomPhoto();
}
