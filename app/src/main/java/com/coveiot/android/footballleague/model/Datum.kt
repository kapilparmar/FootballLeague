package com.coveiot.android.footballleague.model

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

class Datum {
    @SerializedName("id")
    @Expose
    var id: String? = null

    @SerializedName("name")
    @Expose
    var name: String? = null

    @SerializedName("slug")
    @Expose
    var slug: String? = null

    @SerializedName("abbr")
    @Expose
    var abbr: String? = null

    @SerializedName("logos")
    @Expose
    var logos: Logos? = null
}