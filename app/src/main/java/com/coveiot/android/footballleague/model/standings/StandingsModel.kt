package com.coveiot.android.footballleague.model.standings

import com.coveiot.android.footballleague.view.FragmentLeagues.Companion.newInstance
import com.coveiot.android.footballleague.view.FragmentSeasons.Companion.newInstance
import com.coveiot.android.footballleague.view.FragmentStandings.Companion.newInstance
import com.google.gson.annotations.SerializedName
import com.google.gson.annotations.Expose
import com.coveiot.android.footballleague.model.standings.Team
import com.coveiot.android.footballleague.model.standings.Stat
import com.coveiot.android.footballleague.model.standings.Standing
import com.coveiot.android.footballleague.model.Logos
import com.coveiot.android.footballleague.model.Datum
import androidx.fragment.app.FragmentPagerAdapter
import com.coveiot.android.footballleague.view.FragmentLeagues
import com.coveiot.android.footballleague.view.FragmentSeasons
import com.coveiot.android.footballleague.view.FragmentStandings

class StandingsModel {
    @SerializedName("status")
    @Expose
    var status: Boolean? = null

    @SerializedName("data")
    @Expose
    var data: Data? = null

    inner class Data {
        @SerializedName("name")
        @Expose
        var name: String? = null

        @SerializedName("abbreviation")
        @Expose
        var abbreviation: String? = null

        @SerializedName("seasonDisplay")
        @Expose
        var seasonDisplay: String? = null

        @SerializedName("season")
        @Expose
        var season: Int? = null

        @SerializedName("standings")
        @Expose
        var standings: List<Standing>? = null
    }
}