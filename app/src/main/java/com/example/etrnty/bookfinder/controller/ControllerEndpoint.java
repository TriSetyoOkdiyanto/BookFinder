package com.example.etrnty.bookfinder.controller;

import com.example.etrnty.bookfinder.model.books.ModelBooks;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ControllerEndpoint {
    String BASE_URL = "https://www.googleapis.com/";

    @GET("books/v1/volumes/")
    Call<ModelBooks> getRequestBook(@Query("q") String q);
}
