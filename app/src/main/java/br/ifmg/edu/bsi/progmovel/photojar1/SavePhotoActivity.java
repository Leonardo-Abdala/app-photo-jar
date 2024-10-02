package br.ifmg.edu.bsi.progmovel.photojar1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

import br.ifmg.edu.bsi.progmovel.photojar1.databinding.ActivitySavePhotoBinding;
import br.ifmg.edu.bsi.progmovel.photojar1.model.PhotoRecord;

public class SavePhotoActivity extends AppCompatActivity {

    public static final String EXTRA_URI = "br.ifmg.edu.bsi.progmovel.photojar1.SavePhotoActivity.uri";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivitySavePhotoBinding binding = ActivitySavePhotoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent intent = getIntent();
        Uri uri = intent == null ? null : intent.getParcelableExtra(EXTRA_URI);
        if (uri != null) {
            binding.imageView.setImageURI(uri);
        }

        binding.button.setOnClickListener((v) -> {
            PhotoApplication app = (PhotoApplication) getApplication();
            app.executor.execute(() -> {
                PhotoRecord photoRecord = new PhotoRecord(binding.editTextTextMultiLine.getText().toString(),
                        new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date()), uri.toString(), binding.editLocal.getText().toString());
                app.getPhotoDatabase().photoDao().insert(photoRecord);
                runOnUiThread(() -> Toast.makeText(app, "Image saved!", Toast.LENGTH_LONG).show());
                finish();
            });
        });
    }
}