package com.provectus.testandroid.retrofit;

import com.provectus.testandroid.pojo.User;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface JSONPlaceHolder {

    @GET("api/")
    Call<User> getMyJson(@Query("results")int results);



}
