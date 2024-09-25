package br.ifmg.edu.bsi.progmovel.photojar1;

import android.app.Application;

import androidx.room.Room;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import br.ifmg.edu.bsi.progmovel.photojar1.model.PhotoDatabase;

public class PhotoApplication extends Application {

    public final Executor executor = Executors.newFixedThreadPool(1);

    private PhotoDatabase photoDatabase;

    public PhotoDatabase getPhotoDatabase() {
        if (photoDatabase == null) {
            photoDatabase = Room.databaseBuilder(this, PhotoDatabase.class, "photo-db").build();
        }
        return photoDatabase;
    }
}
