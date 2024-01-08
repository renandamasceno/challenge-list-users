package com.challenge.listusers.network.service;

import com.challenge.listusers.model.User;
import com.challenge.listusers.network.model.ApiResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiService {
    @POST("Desafio/rest/desafioRest")
    Call<ApiResponse> enviarUsuario(@Body User user);
}
