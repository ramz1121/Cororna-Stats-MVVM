package com.kotlin.corornastats.mvvm.data.respository

import android.accounts.NetworkErrorException
import com.google.gson.JsonArray
import com.google.gson.JsonObject
import com.kotlin.corornastats.mvvm.data.local.db.DatabaseService
import com.kotlin.corornastats.mvvm.data.local.prefs.UserPreferences
import com.kotlin.corornastats.mvvm.data.model.Cases
import com.kotlin.corornastats.mvvm.data.remote.NetworkService
import com.kotlin.corornastats.mvvm.utils.common.NumberFormater
import com.mindorks.bootcamp.instagram.utils.log.Logger
import io.reactivex.Single
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepository @Inject constructor(
    private val networkService: NetworkService,
    private val databaseService: DatabaseService,
    private val userPreferences: UserPreferences
) {
    companion object {
        private const val TAG = "User Repo"
    }

    fun saveCurrentUser(case: Cases) {
        case.confirmed
        case.recovered
        case.deaths
        case.totalActive
        case.totalClosed
    }

    fun getCaseNumbers():Cases {
        var confirmedString: String = ""
        var deathsString: String = ""
        var recoveredString: String = ""
        var totalActiveString: String = ""
        var totalClosedString: String = ""

        try {
            networkService.getJSON()
                ?.enqueue(object : Callback<JsonObject> {
                    override fun onResponse(
                        call: Call<JsonObject>,
                        response: Response<JsonObject>
                    ) {

                        var confirmed = 0.0
                        var recovered = 0.0
                        var deaths = 0.0
                        var active = 0.0
                        var closed = 0.0
                        val jsonResponse = response.body()
                        val set = jsonResponse?.entrySet()
                        val it = set?.iterator()
                        while (it!!.hasNext()) {
                            val item = it.next()
                            val country = item.key
                            val countryData = item.value
                            val last =
                                countryData.asJsonArray.get((countryData as JsonArray).size() - 1)
                            print("value" + last)
                            confirmed = confirmed + last.asJsonObject["confirmed"].asDouble
                            recovered = recovered + last.asJsonObject["recovered"].asDouble
                            deaths = deaths + last.asJsonObject["deaths"].asDouble
                        }

                        confirmedString = NumberFormater.formatNumber(confirmed)
                        deathsString = NumberFormater.formatNumber(deaths)
                        recoveredString = NumberFormater.formatNumber(recovered)
                        //get total active cases
                        active = confirmed - (deaths + recovered)
                        totalActiveString = NumberFormater.formatNumber(active)
                        //get total closed cases
                        closed = deaths + recovered
                        totalClosedString = NumberFormater.formatNumber(closed)
                        Cases(
                            confirmedString,
                            recoveredString,
                            deathsString,
                            totalActiveString,
                            totalClosedString
                        )

                    }

                    override fun onFailure(
                        call: Call<JsonObject>,
                        t: Throwable
                    ) {
                        println(t.message)
                    }
                })
        } catch (e: Exception) {
            println(e.message)
        }
        return Cases(
            confirmedString,
            recoveredString,
            deathsString,
            totalActiveString,
            totalClosedString
        )
    }

}
