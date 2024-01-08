package com.challenge.listusers.network.service;

import com.challenge.listusers.model.User;
import com.challenge.listusers.network.model.ApiResponse;

import retrofit2.Call;
import retrofit2.Callback;


public class UserService {
    private final ApiService apiService;

    public UserService() {
        apiService = RetrofitClient.getClient().create(ApiService.class);
    }

    public void sendUser(User user, Callback<ApiResponse> callback) {
        Call<ApiResponse> call = apiService.enviarUsuario(user);
        call.enqueue(callback);
    }
}
