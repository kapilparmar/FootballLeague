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

class Logo {
    @SerializedName("href")
    @Expose
    var href: String? = null

    @SerializedName("width")
    @Expose
    var width: Double? = null

    @SerializedName("height")
    @Expose
    var height: Double? = null

    @SerializedName("alt")
    @Expose
    var alt: String? = null

    @SerializedName("rel")
    @Expose
    var rel: List<String>? = null

    @SerializedName("lastUpdated")
    @Expose
    var lastUpdated: String? = null
}