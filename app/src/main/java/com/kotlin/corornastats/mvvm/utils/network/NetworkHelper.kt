package com.kotlin.corornastats.mvvm.utils.network

import android.content.ContentValues.TAG
import android.content.Context
import android.net.ConnectivityManager
import android.util.Log.e
import com.google.gson.GsonBuilder
import com.google.gson.JsonSyntaxException
import com.mindorks.bootcamp.instagram.utils.log.Logger
import retrofit2.HttpException
import java.io.IOException
import java.net.ConnectException

class NetworkHelper constructor(private val context: Context) {
    fun isNetworkConnected(): Boolean {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = cm.activeNetworkInfo
        return activeNetwork?.isConnected ?: false
    }

    fun castToNetworkError(throwable: Throwable): NetworkError{
        val defaultNetworkError = NetworkError()
        try{
            if(throwable is ConnectException) return NetworkError(0,"0")
            if(throwable !is HttpException) return defaultNetworkError
            val error = GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .create()
                .fromJson(throwable.response().errorBody()?.string(), NetworkError::class.java)
            return NetworkError(throwable.code(),error.statuscode,error.message)
        }catch (e: IOException) {
            Logger.e(TAG, e.toString())
        } catch (e: JsonSyntaxException) {
            Logger.e(TAG, e.toString())
        } catch (e: NullPointerException) {
            Logger.e(TAG, e.toString())
        }
        return defaultNetworkError
    }
}