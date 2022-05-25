package com.coveiot.android.footballleagues.network

import com.coveiot.android.footballleague.model.AllLeaguesModel
import com.coveiot.android.footballleague.model.LeagueDetailsModel
import com.coveiot.android.footballleague.model.standings.StandingsModel
import com.coveiot.android.footballleague.model.season.SeasonData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface SportsApiService {
    @GET( "leagues")
    fun getAllLeagues(): Call<AllLeaguesModel?>?

    @GET( "leagues/{id}")
    fun getLeagueDetails(@Path("id")id: String): Call<LeagueDetailsModel?>?

    @GET( "leagues/{id}/seasons")
    fun getAllSeason(@Path("id")id: String): Call<SeasonData?>?

    @GET( "leagues/{id}/standings?season=2020&sort=asc")
    fun getStandings(@Path("id")id:String): Call<StandingsModel?>?
}