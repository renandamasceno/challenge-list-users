package com.challenge.listusers.view;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.challenge.listusers.databinding.ActivityListUsersBinding;

public class ListUsersActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityListUsersBinding binding = ActivityListUsersBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        fabClickListener(binding);
    }

    private void fabClickListener(ActivityListUsersBinding binding) {
        binding.addFab.setOnClickListener(view -> {
            Intent intent = new Intent(ListUsersActivity.this, RegisterUsersActivity.class);
            startActivity(intent);
        });
    }
}