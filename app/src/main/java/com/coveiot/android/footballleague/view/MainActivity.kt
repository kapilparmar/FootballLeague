package com.coveiot.android.footballleague.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.coveiot.android.footballleague.Constants
import com.coveiot.android.footballleague.R
import com.coveiot.android.footballleague.Utils
import com.coveiot.android.footballleague.adapter.FootBalLeagueAdapter
import com.coveiot.android.footballleague.model.AllLeaguesModel
import com.coveiot.android.footballleague.model.Datum
import com.coveiot.android.footballleague.viewmodel.FootBallLeagueViewModel
import com.coveiot.android.footballleagues.network.SportsApiErrorModel
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Response

class MainActivity : AppCompatActivity(),
    FootBallLeagueViewModel.FootballLeagueListener ,
    FootBalLeagueAdapter.OnItemClickListener{
    private lateinit var viewModelWorkoutHistory: FootBallLeagueViewModel
    var mAdapter: FootBalLeagueAdapter? = null
    var dataList : MutableList<Datum>?= mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModelWorkoutHistory = ViewModelProvider(this).get(FootBallLeagueViewModel::class.java)
        viewModelWorkoutHistory.footballLeagueListener = this@MainActivity
        val manager = LinearLayoutManager(this)
        recyclerView.layoutManager = manager
        mAdapter = FootBalLeagueAdapter(dataList!!, this)
        recyclerView.adapter = mAdapter
        if (Utils.isNetConnected(this)){
            txtMsg.visibility = View.GONE
            recyclerView.visibility = View.VISIBLE
            viewModelWorkoutHistory.getFootballLeagues()
        }
        else{
            txtMsg.visibility = View.VISIBLE
            recyclerView.visibility = View.GONE
            txtMsg.text = "No Internet Connection"
        }
    }

    override fun onSuccess(obj: Response<AllLeaguesModel?>?) {
        txtMsg.visibility = View.GONE
        recyclerView.visibility = View.VISIBLE
        dataList = obj!!.body()!!.data as MutableList<Datum>?
        mAdapter!!.notifyAdapter(dataList!!)
    }

    override fun onFailure(obj: SportsApiErrorModel?) {
        txtMsg.visibility = View.VISIBLE
        recyclerView.visibility = View.GONE
        txtMsg.text = obj!!.response
    }

    override fun onItemClick(postition: Int) {
        var intent = Intent(this, ActivityFootBall::class.java)
        intent.putExtra(Constants.EXTRAS_ID,dataList!!.get(postition).id)
        startActivity(intent)
    }
}