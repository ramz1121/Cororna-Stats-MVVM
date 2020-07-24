package com.kotlin.corornastats.mvvm.data.remote

import com.google.gson.JsonObject
import io.reactivex.Single
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import javax.inject.Singleton


@Singleton
interface NetworkService {
    @GET(Endpoints.CASES)
    //open fun getFlowers(): Call<JsonElement?>?
    fun getJSON(): Call<JsonObject>
}