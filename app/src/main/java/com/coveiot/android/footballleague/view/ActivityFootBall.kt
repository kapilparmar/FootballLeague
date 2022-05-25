package com.coveiot.android.footballleague.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.coveiot.android.footballleague.Constants
import com.coveiot.android.footballleague.R
import com.coveiot.android.footballleague.adapter.PagerAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import com.google.android.material.tabs.TabLayout.TabLayoutOnPageChangeListener
import kotlinx.android.synthetic.main.activity_football.*


class ActivityFootBall : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_football)

        var intent = intent
        var id = intent.getStringExtra(Constants.EXTRAS_ID)


        tabs.addTab(tabs.newTab().setText("Leagues"))
        tabs.addTab(tabs.newTab().setText("Seasons"))
        tabs.addTab(tabs.newTab().setText("Standings"))
        val adapter = PagerAdapter(
            this,
            supportFragmentManager,
            tabs.getTabCount(),
            id!!
        )
        viewpager.setAdapter(adapter)

        viewpager.addOnPageChangeListener(TabLayoutOnPageChangeListener(tabs))

        tabs.addOnTabSelectedListener(object : OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                viewpager.setCurrentItem(tab.position)
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {}
            override fun onTabReselected(tab: TabLayout.Tab) {}
        })

    }
}