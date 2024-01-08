package com.challenge.listusers.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.challenge.listusers.data.repository.UserRepository;
import com.challenge.listusers.model.User;
import com.challenge.listusers.network.model.ApiResponse;
import com.challenge.listusers.network.service.UserService;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@HiltViewModel
public class UserViewModel extends ViewModel {
    private final UserRepository userRepository;
    private final UserService userService;
    private final LiveData<List<User>> allUsers;

    @Inject
    public UserViewModel(UserRepository userRepository, UserService userService) {
        this.userRepository = userRepository;
        this.allUsers = userRepository.getAllUsers();
        this.userService = userService;
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

    public LiveData<Boolean> isUsernameAvailable(String username) {
        return userRepository.isUsernameAvailable(username);
    }

    public void sendUser(User user) {
        userService.sendUser(user, new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                if (response.isSuccessful()) {
                    // TODO: Aguardando conseguir requistar API
                } else {
                    // TODO: Aguardando conseguir requistar API
                }
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                // TODO: Aguardando conseguir requistar API
            }
        });
    }
}
