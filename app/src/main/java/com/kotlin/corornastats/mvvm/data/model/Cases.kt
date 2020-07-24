package com.kotlin.corornastats.mvvm.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

 data class Cases(

    var confirmed: String, var recovered: String, var deaths: String, var totalActive: String, val totalClosed: String
)