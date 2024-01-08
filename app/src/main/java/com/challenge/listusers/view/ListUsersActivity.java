package com.challenge.listusers.view;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.challenge.listusers.databinding.ActivityListUsersBinding;
import com.challenge.listusers.viewmodel.UserViewModel;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class ListUsersActivity extends AppCompatActivity {

    private UserViewModel userViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityListUsersBinding binding = ActivityListUsersBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);
        fabClickListener(binding);
        configRecyclerView(binding, userViewModel);
    }

    private void configRecyclerView(ActivityListUsersBinding binding, UserViewModel userViewModel) {
        RecyclerView recyclerView = binding.recyclerView;
        UserAdapter adapter = new UserAdapter();
        userViewModel.getAllUsers().observe(this, adapter::submitList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

    private void fabClickListener(ActivityListUsersBinding binding) {
        binding.addFab.setOnClickListener(view -> {
            Intent intent = new Intent(ListUsersActivity.this, RegisterUsersActivity.class);
            startActivity(intent);
        });
    }
}