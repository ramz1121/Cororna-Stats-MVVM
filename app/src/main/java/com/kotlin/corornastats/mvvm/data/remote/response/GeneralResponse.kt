package com.kotlin.corornastats.mvvm.data.remote.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class GeneralResponse(
    @Expose
    @SerializedName("statuscode")
    var statusCode: String,

    @Expose
    @SerializedName("message")
    var message: String
) {
}