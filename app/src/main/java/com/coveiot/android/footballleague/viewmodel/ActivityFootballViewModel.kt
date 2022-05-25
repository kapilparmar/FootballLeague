package com.coveiot.android.footballleague.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import com.coveiot.android.footballleague.model.LeagueDetailsModel
import com.coveiot.android.footballleague.model.standings.StandingsModel
import com.coveiot.android.footballleague.model.season.SeasonData
import com.coveiot.android.footballleagues.SportsApiManager
import com.coveiot.android.footballleagues.network.SportsApiErrorModel
import com.coveiot.android.footballleagues.network.SportsApiListener
import com.google.gson.Gson
import retrofit2.Response

class ActivityFootballViewModel(application: Application) : AndroidViewModel(application) {


    lateinit var footballLeagueListener:FootballDetailListener

    fun getLeagueDetails(id:String){
        SportsApiManager.getFootballLeaguesDetails(id,
            object : SportsApiListener<Response<LeagueDetailsModel?>?, SportsApiErrorModel?> {
                override fun onSuccess(obj: Response<LeagueDetailsModel?>?) {
                    Log.d("TAG", "onSuccess: ${Gson().toJson(obj)}")
                    footballLeagueListener.onSuccessLeagueDetails(obj)
                }

                override fun onError(obj: SportsApiErrorModel?) {
                    Log.d("TAG", "onError: ${Gson().toJson(obj)}")
                    footballLeagueListener.onFailureLeagueDetails(obj)
                }
            })
    }

    fun getFootballSeason(id:String){
        SportsApiManager.getSeasonList(id,
            object : SportsApiListener<Response<SeasonData?>?, SportsApiErrorModel?> {
                override fun onSuccess(obj: Response<SeasonData?>?) {
                    Log.d("TAG", "onSuccess: ${Gson().toJson(obj)}")
                    footballLeagueListener.onSuccessSeason(obj)
                }

                override fun onError(obj: SportsApiErrorModel?) {
                    Log.d("TAG", "onError: ${Gson().toJson(obj)}")
                    footballLeagueListener.onFailureSeason(obj)
                }
            })
    }
    fun getStandings(id:String){
        SportsApiManager.getStandings(id,
            object : SportsApiListener<Response<StandingsModel?>?, SportsApiErrorModel?> {
                override fun onSuccess(obj: Response<StandingsModel?>?) {
                    Log.d("TAG", "onSuccess: ${Gson().toJson(obj)}")
                    footballLeagueListener.onSuccessStandings(obj)
                }

                override fun onError(obj: SportsApiErrorModel?) {
                    Log.d("TAG", "onError: ${Gson().toJson(obj)}")
                    footballLeagueListener.onFailureLStandings(obj)
                }
            })
    }

    interface FootballDetailListener {
        fun onSuccessLeagueDetails(obj: Response<LeagueDetailsModel?>?)
        fun onSuccessSeason(obj: Response<SeasonData?>?)
        fun onSuccessStandings(obj: Response<StandingsModel?>?)
        fun onFailureLeagueDetails(obj: SportsApiErrorModel?)
        fun onFailureSeason(obj: SportsApiErrorModel?)
        fun onFailureLStandings(obj: SportsApiErrorModel?)
    }
}