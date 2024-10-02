package br.ifmg.edu.bsi.progmovel.photojar1;
import br.ifmg.edu.bsi.progmovel.photojar1.model.PhotoDao;
import br.ifmg.edu.bsi.progmovel.photojar1.model.PhotoRepository;
import br.ifmg.edu.bsi.progmovel.photojar1.model.PhotoDatabase;
import androidx.room.Room;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.room.migration.Migration;

import android.Manifest;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Toast;
import java.util.concurrent.Executors;

import br.ifmg.edu.bsi.progmovel.photojar1.databinding.ActivityMainBinding;
import br.ifmg.edu.bsi.progmovel.photojar1.model.PhotoRecord;

public class MainActivity extends AppCompatActivity {

    private Uri photoUri;

    private ActivityResultLauncher<Uri> cameraLauncher = registerForActivityResult(new ActivityResultContracts.TakePicture(),
            photoTaken -> {
                if (photoTaken) {
                    Intent intent = new Intent(this, SavePhotoActivity.class);
                    intent.putExtra(SavePhotoActivity.EXTRA_URI, photoUri);
                    startActivity(intent);
                }
            });

    private ActivityResultLauncher<String> permissionLauncher =
            registerForActivityResult(new ActivityResultContracts.RequestPermission(), isGranted -> {
                if (isGranted) {
                    captureMoment();
                } else {
                    Toast.makeText(this, "Camera permission not granted.", Toast.LENGTH_LONG);
                }
            });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        PhotoDatabase db = PhotoDatabase.getDatabase(getApplicationContext());
        PhotoDao photoDao = db.photoDao();
        PhotoRepository photoRepository = new PhotoRepository(photoDao);
        Executors.newSingleThreadExecutor().execute(() -> photoRepository.logRandomPhoto());
        binding.capture.setOnClickListener((v) -> tryAndCaptureMoment());
        binding.cheerMeUp.setOnClickListener((v) -> {
            startActivity(new Intent(this, RandomPhotoActivity.class));
        });

        boolean condition = true;

        while (condition) {
            condition = doStuff();
        }

    }

    private boolean doStuff() {return false;}

    public void tryAndCaptureMoment() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
            captureMoment();
        } else {
            permissionLauncher.launch(Manifest.permission.CAMERA);
        }
    }

    public void captureMoment() {
        ContentValues values = new ContentValues();
        values.put(MediaStore.Images.Media.TITLE, "photojar-record");

        Uri contentUri;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            contentUri = MediaStore.Images.Media.getContentUri(MediaStore.VOLUME_EXTERNAL_PRIMARY);
        } else {
            contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
        }

        photoUri = getContentResolver().insert(contentUri, values);

        cameraLauncher.launch(photoUri);
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable("photoUri", photoUri);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        photoUri = savedInstanceState.getParcelable("photoUri");
    }
}