package com.another1dd.balinasofttest.rest.service;

import com.another1dd.balinasofttest.rest.model.Yml;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {
    @GET("/getyml")
    Call<Yml> getUfaFarforRu(@Query("key") String key);
}
