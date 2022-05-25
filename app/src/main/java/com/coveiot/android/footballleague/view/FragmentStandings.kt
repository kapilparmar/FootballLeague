package com.coveiot.android.footballleague.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.coveiot.android.footballleague.Constants
import com.coveiot.android.footballleague.R
import com.coveiot.android.footballleague.Utils
import com.coveiot.android.footballleague.adapter.FootBallStandingsAdapter
import com.coveiot.android.footballleague.model.standings.SessionDataMoedl
import com.coveiot.android.footballleague.model.standings.Standing
import com.coveiot.android.footballleague.model.standings.StandingsModel
import com.coveiot.android.footballleague.viewmodel.ActivityFootballViewModel
import com.coveiot.android.footballleagues.SportsApiManager
import com.coveiot.android.footballleagues.network.SportsApiErrorModel
import com.coveiot.android.footballleagues.network.SportsApiListener
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_standings.*
import retrofit2.Response

class FragmentStandings() : Fragment(){
    private var viewModel:ActivityFootballViewModel? = null
    var mAdapter: FootBallStandingsAdapter? = null
    var dataList : MutableList<Standing>?= mutableListOf()

    companion object {
        fun newInstance(id: String) = FragmentStandings().apply {
            val bundle = Bundle()
            bundle.putString(Constants.EXTRAS_ID, id)
            arguments = bundle
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_standings, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var bundle = arguments
        viewModel = ViewModelProvider(requireActivity()).get(ActivityFootballViewModel::class.java)
        val manager = LinearLayoutManager(context)
        recycler_view.layoutManager = manager
        mAdapter = FootBallStandingsAdapter(dataList!!)
        recycler_view.adapter = mAdapter
        if (Utils.isNetConnected(requireContext())){
            txtMsg.visibility = View.GONE
            recycler_view.visibility = View.VISIBLE
            bundle.let {
                var id = it!!.getString(Constants.EXTRAS_ID, null)
                SportsApiManager.getStandings(id,
                    object : SportsApiListener<Response<StandingsModel?>?, SportsApiErrorModel?> {
                        override fun onSuccess(obj: Response<StandingsModel?>?) {
                            Log.d("TAG", "onSuccess: ${Gson().toJson(obj)}")
                            onSuccessStandings(obj)
                        }

                        override fun onError(obj: SportsApiErrorModel?) {
                            Log.d("TAG", "onError: ${Gson().toJson(obj)}")
                            onFailureLStandings(obj)
                        }
                    })

            }
        }
        else{
            txtMsg.visibility = View.VISIBLE
            recycler_view.visibility = View.GONE
            txtMsg.text = "No Internet Connection"
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }


    fun onSuccessStandings(obj: Response<StandingsModel?>?) {
        dataList = obj!!.body()!!.data!!.standings as MutableList<Standing>?
        mAdapter!!.notifyAdapter(dataList!!)
        txtMsg.visibility = View.GONE
        recycler_view.visibility = View.VISIBLE
    }

    fun onFailureLStandings(obj: SportsApiErrorModel?) {
        txtMsg.visibility = View.VISIBLE
        recycler_view.visibility = View.GONE
        txtMsg.text = obj!!.response
    }
}