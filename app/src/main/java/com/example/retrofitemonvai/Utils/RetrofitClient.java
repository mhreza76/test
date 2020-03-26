package com.example.retrofitemonvai.Utils;

import com.example.retrofitemonvai.models.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RetrofitClient {
    //https://gsbsc.ringersoft.com/detailsgoschi/users

    @GET("detailsgoschi/users")
    Call<List<User>> getUsers();

}
