package com.coveiot.android.footballleague.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import com.coveiot.android.footballleague.model.AllLeaguesModel
import com.coveiot.android.footballleagues.SportsApiManager
import com.coveiot.android.footballleagues.network.SportsApiErrorModel
import com.coveiot.android.footballleagues.network.SportsApiListener
import com.google.gson.Gson
import retrofit2.Response

class FootBallLeagueViewModel(application: Application) : AndroidViewModel(application) {
    lateinit var footballLeagueListener :FootballLeagueListener;


    fun getFootballLeagues(){
        SportsApiManager.getFootballLeagues(
            object : SportsApiListener<Response<AllLeaguesModel?>?, SportsApiErrorModel?> {
                override fun onSuccess(obj: Response<AllLeaguesModel?>?) {
                    Log.d("TAG", "onSuccess: ${Gson().toJson(obj)}")
                    footballLeagueListener.onSuccess(obj)
                }

                override fun onError(obj: SportsApiErrorModel?) {
                    footballLeagueListener.onFailure(obj)
                    Log.d("TAG", "onError: ${Gson().toJson(obj)}")
                }
            })
    }


    interface FootballLeagueListener {
        fun onSuccess(obj: Response<AllLeaguesModel?>?)
        fun onFailure(obj: SportsApiErrorModel?)
    }
}