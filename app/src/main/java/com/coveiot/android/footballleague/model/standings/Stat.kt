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

class Stat {
    @SerializedName("name")
    @Expose
    var name: String? = null

    @SerializedName("displayName")
    @Expose
    var displayName: String? = null

    @SerializedName("shortDisplayName")
    @Expose
    var shortDisplayName: String? = null

    @SerializedName("description")
    @Expose
    var description: String? = null

    @SerializedName("abbreviation")
    @Expose
    var abbreviation: String? = null

    @SerializedName("type")
    @Expose
    var type: String? = null

    @SerializedName("value")
    @Expose
    var value: Double? = null

    @SerializedName("displayValue")
    @Expose
    var displayValue: String? = null

    @SerializedName("id")
    @Expose
    var id: String? = null

    @SerializedName("summary")
    @Expose
    var summary: String? = null
}