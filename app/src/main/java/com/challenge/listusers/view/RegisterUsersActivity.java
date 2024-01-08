package com.challenge.listusers.view;

import static com.challenge.listusers.Utils.showPersonTypeDialog;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.challenge.listusers.R;
import com.challenge.listusers.Utils;
import com.challenge.listusers.databinding.ActivityRegisterUsersBinding;
import com.google.android.material.appbar.MaterialToolbar;

import java.util.Objects;

public class RegisterUsersActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityRegisterUsersBinding binding = ActivityRegisterUsersBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        onBackPressedToolBar(binding);
        showPersonTypeDialog(
                this,
                () -> Utils.updateHintBasedOnPersonType(binding.cpfCnpjTxtInputLayout, "CPF"),
                () -> Utils.updateHintBasedOnPersonType(binding.cpfCnpjTxtInputLayout, "CNPJ")
        );

        Glide.with(this)
                .load(R.drawable.camera)
                .centerCrop()
                .placeholder(R.drawable.camera)
                .transform(new CircleCrop())
                .into(binding.registerImgView);

    }

    private void onBackPressedToolBar(ActivityRegisterUsersBinding binding) {
        MaterialToolbar toolbar = binding.registerToolbar;
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(view -> {
            onBackPressed();
        });
    }

}