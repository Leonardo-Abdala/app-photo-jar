package br.ifmg.edu.bsi.progmovel.photojar1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.net.Uri;
import android.os.Bundle;

import br.ifmg.edu.bsi.progmovel.photojar1.databinding.ActivityRandomPhotoBinding;
import br.ifmg.edu.bsi.progmovel.photojar1.model.PhotoRecord;

public class RandomPhotoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityRandomPhotoBinding binding = ActivityRandomPhotoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        RandomPhotoViewModel vm = new ViewModelProvider(this).get(RandomPhotoViewModel.class);
        vm.getDescription().observe(this, (v) -> binding.textView2.setText(v));
        vm.getPhotoUri().observe(this, (v) -> binding.imageView3.setImageURI(v));
        vm.loadRandomPhoto();

        binding.closeButton.setOnClickListener((v) -> finish());
    }
}