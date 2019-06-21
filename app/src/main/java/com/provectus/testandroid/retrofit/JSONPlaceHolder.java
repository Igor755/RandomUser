package com.provectus.testandroid.retrofit;

import com.provectus.testandroid.pojo.User;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface JSONPlaceHolder {
    @GET("api/")
    public Call<User> getPostWithID(@Path("results") int id);
}
