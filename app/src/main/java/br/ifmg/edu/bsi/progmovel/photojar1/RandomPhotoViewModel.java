package br.ifmg.edu.bsi.progmovel.photojar1;

import android.app.Application;
import android.net.Uri;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import br.ifmg.edu.bsi.progmovel.photojar1.model.PhotoRecord;

public class RandomPhotoViewModel extends AndroidViewModel {

    private MutableLiveData<Uri> photoUri = new MutableLiveData<>();
    private MutableLiveData<String> description = new MutableLiveData<>();

    private PhotoApplication app;
    private boolean loaded = false;

    public RandomPhotoViewModel(@NonNull Application application) {
        super(application);
        app = (PhotoApplication) application;
    }

    public LiveData<Uri> getPhotoUri() {
        return photoUri;
    }

    public LiveData<String> getDescription() {
        return description;
    }

    public void loadRandomPhoto() {
        if (!loaded) {
            app.executor.execute(() -> {
                PhotoRecord photoRecord = app.getPhotoDatabase().photoDao().getRandomPhoto();
                if (photoRecord == null) {
                    Log.d("PHOTOJAR", "photoRecord is null");
                    return;
                }
                photoUri.postValue(Uri.parse(photoRecord.getPhotoUri()));
                description.postValue(photoRecord.getDescription());
                loaded = true;
            });
        }
    }
}
