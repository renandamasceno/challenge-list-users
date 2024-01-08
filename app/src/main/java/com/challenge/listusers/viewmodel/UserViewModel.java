package com.challenge.listusers.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.challenge.listusers.data.repository.UserRepository;
import com.challenge.listusers.model.User;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class UserViewModel extends ViewModel {
    private final UserRepository userRepository;
    private final LiveData<List<User>> allUsers;

    @Inject
    public UserViewModel(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.allUsers = userRepository.getAllUsers();
    }

    public LiveData<List<User>> getAllUsers() {
        return allUsers;
    }

    public void insert(User user) {
        userRepository.insert(user);
    }

    public void update(User user) {
        userRepository.update(user);
    }

    public void delete(User user) {
        userRepository.delete(user);
    }
}
