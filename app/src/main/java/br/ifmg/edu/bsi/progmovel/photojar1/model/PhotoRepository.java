package br.ifmg.edu.bsi.progmovel.photojar1.model;

import android.util.Log;

public class PhotoRepository {

    private final PhotoDao photoDao;

    public PhotoRepository(PhotoDao photoDao) {
        this.photoDao = photoDao;
    }

    public void logRandomPhoto() {
        PhotoRecord photoRecord = photoDao.getRandomPhoto();
        if (photoRecord != null) {
            Log.d("PhotoRepository", "Random Photo: " + photoRecord.toString());
        } else {
            Log.d("PhotoRepository", "No photo found");
        }
    }
}

