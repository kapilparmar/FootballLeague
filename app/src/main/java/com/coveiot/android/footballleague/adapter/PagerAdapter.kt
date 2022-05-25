package com.coveiot.android.footballleague.adapter

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
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

class PagerAdapter(
    private val myContext: Context,
    fm: FragmentManager?,
    var totalTabs: Int,
    var id: String
) : FragmentPagerAdapter(
    fm!!
) {
    // this is for fragment tabs  
    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> {
                FragmentLeagues.newInstance(id)
            }
            1 -> {
                FragmentSeasons.newInstance(id)
            }
            2 -> {
                FragmentStandings.newInstance(
                    id
                )
            }
            else -> FragmentLeagues.newInstance(id)
        }
    }

    // this counts total number of tabs  
    override fun getCount(): Int {
        return totalTabs
    }
}