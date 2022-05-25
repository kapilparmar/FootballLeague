package com.coveiot.android.footballleagues

import android.util.Log
import com.coveiot.android.footballleague.model.AllLeaguesModel
import com.coveiot.android.footballleague.model.LeagueDetailsModel
import com.coveiot.android.footballleague.model.standings.StandingsModel
import com.coveiot.android.footballleague.model.season.SeasonData
import com.coveiot.android.footballleague.network.SportErrorModel
import com.coveiot.android.footballleagues.network.SportsApiClient
import com.coveiot.android.footballleagues.network.SportsApiErrorModel
import com.coveiot.android.footballleagues.network.SportsApiListener
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SportsApiManager {
    companion object {
        fun getFootballLeagues(
            l: SportsApiListener<Response<AllLeaguesModel?>?, SportsApiErrorModel?>
        ) {
            SportsApiClient.getService()!!.getSportsClientService()
                .getAllLeagues()
                ?.enqueue(object : Callback<AllLeaguesModel?> {
                    override fun onResponse(
                        call: Call<AllLeaguesModel?>,
                        response: Response<AllLeaguesModel?>
                    ) {
                        if (response.isSuccessful) {
                            l.onSuccess(response)
                        } else {
                            val obj = SportsApiErrorModel(response.code())
                            obj.response = response.errorBody()?.toString()
                            l.onError(obj)
                        }
                    }

                    override fun onFailure(call: Call<AllLeaguesModel?>, t: Throwable) {
                        Log.d("TAG", "onResponse: ${Gson().toJson(t)}")
                        buildErrorObject(t)
                    }
                })

        }

        fun getFootballLeaguesDetails(
            id: String,
            l: SportsApiListener<Response<LeagueDetailsModel?>?, SportsApiErrorModel?>
        ) {
            SportsApiClient.getService()!!.getSportsClientService()
                .getLeagueDetails(id)
                ?.enqueue(object : Callback<LeagueDetailsModel?> {
                    override fun onResponse(
                        call: Call<LeagueDetailsModel?>,
                        response: Response<LeagueDetailsModel?>
                    ) {
                        if (response.isSuccessful) {
                            l.onSuccess(response)
                        } else {
                            val obj = SportsApiErrorModel(response.code())
                            obj.response = response.errorBody()?.toString()
                            l.onError(obj)
                        }
                    }

                    override fun onFailure(call: Call<LeagueDetailsModel?>, t: Throwable) {
                        Log.d("TAG", "onResponse: ${Gson().toJson(t)}")
                        buildErrorObject(t)

                    }
                })

        }

        fun getSeasonList(
            id: String,
            l: SportsApiListener<Response<SeasonData?>?, SportsApiErrorModel?>
        ) {
            SportsApiClient.getService()!!.getSportsClientService()
                .getAllSeason(id)
                ?.enqueue(object : Callback<SeasonData?> {
                    override fun onResponse(
                        call: Call<SeasonData?>,
                        response: Response<SeasonData?>
                    ) {
                        if (response.isSuccessful) {
                            l.onSuccess(response)
                        } else {
                            val obj = SportsApiErrorModel(response.code())
                            obj.response = response.errorBody()?.toString()
                            l.onError(obj)
                        }
                    }

                    override fun onFailure(call: Call<SeasonData?>, t: Throwable) {
                        Log.d("TAG", "onResponse: ${Gson().toJson(t)}")
                        buildErrorObject(t)
                    }
                })

        }

        fun getStandings(
            id: String,
            l: SportsApiListener<Response<StandingsModel?>?, SportsApiErrorModel?>
        ) {
            SportsApiClient.getService()!!.getSportsClientService()
                .getStandings(id)
                ?.enqueue(object : Callback<StandingsModel?> {
                    override fun onResponse(
                        call: Call<StandingsModel?>,
                        response: Response<StandingsModel?>
                    ) {
                        if (response.isSuccessful) {
                            l.onSuccess(response)
                        } else {
                            val obj = SportsApiErrorModel(response.code())
                            obj.response = response.errorBody()?.toString()
                            l.onError(obj)
                        }
                    }

                    override fun onFailure(call: Call<StandingsModel?>, t: Throwable) {
                        Log.d("TAG", "onResponse: ${Gson().toJson(t)}")
                        buildErrorObject(t)

                    }
                })

        }

        fun buildErrorObject(t: Throwable): SportErrorModel? {
            val errMsg: String?
            errMsg = if (t.message != null) {
                t.message
            } else {
                "Error"
            }
            return SportErrorModel(errMsg!!)
        }
    }
}