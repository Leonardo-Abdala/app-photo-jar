package br.ifmg.edu.bsi.progmovel.photojar1.model;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {PhotoRecord.class}, version=1)
public abstract class PhotoDatabase extends RoomDatabase {
    public abstract PhotoDao photoDao();
}
