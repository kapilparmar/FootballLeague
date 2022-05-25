package com.coveiot.android.footballleague.view

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.coveiot.android.footballleague.Constants
import com.coveiot.android.footballleague.R
import com.coveiot.android.footballleague.Utils
import com.coveiot.android.footballleague.model.AllLeaguesModel
import com.coveiot.android.footballleague.model.Datum
import com.coveiot.android.footballleague.model.LeagueDetailsModel
import com.coveiot.android.footballleague.model.season.Data
import com.coveiot.android.footballleague.model.season.SeasonData
import com.coveiot.android.footballleague.model.standings.StandingsModel
import com.coveiot.android.footballleague.viewmodel.ActivityFootballViewModel
import com.coveiot.android.footballleagues.SportsApiManager
import com.coveiot.android.footballleagues.network.SportsApiErrorModel
import com.coveiot.android.footballleagues.network.SportsApiListener
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_seasons.*
import retrofit2.Response

class FragmentSeasons() : Fragment() {
    private var viewModel:ActivityFootballViewModel? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_seasons, container, false)
    }

    companion object {
        fun newInstance(id: String) = FragmentSeasons().apply {
            val bundle = Bundle()
            bundle.putString(Constants.EXTRAS_ID, id)
            arguments = bundle
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var bundle = arguments
        viewModel = ViewModelProvider(requireActivity()).get(ActivityFootballViewModel::class.java)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var bundle = arguments
        if (Utils.isNetConnected(requireContext())){
            txtMsg.visibility = View.GONE
            ll_item.visibility = View.VISIBLE
            bundle.let {
                var id = it!!.getString(Constants.EXTRAS_ID, null)

                SportsApiManager.getSeasonList(id,
                    object : SportsApiListener<Response<SeasonData?>?, SportsApiErrorModel?> {
                        override fun onSuccess(obj: Response<SeasonData?>?) {
                            Log.d("TAG", "onSuccess: ${Gson().toJson(obj)}")
                            onSuccessSeason(obj)
                        }

                        override fun onError(obj: SportsApiErrorModel?) {
                            Log.d("TAG", "onError: ${Gson().toJson(obj)}")
                            onFailureSeason(obj)
                        }
                    })
            }        }
        else{
            txtMsg.visibility = View.VISIBLE
            ll_item.visibility = View.GONE
            txtMsg.text = "No Internet Connection"
        }

    }
    override fun onAttach(context: Context) {
        super.onAttach(context)

    }


    fun onSuccessSeason(obj: Response<SeasonData?>?) {
        var data : Data = obj!!.body()!!.data!!
        txtName.setText("Name : "+data.name)
        txtabbr.setText("Desc : "+data.desc)
        txtslug.setText("Abbr : "+data.abbreviation)
        txtMsg.visibility = View.GONE
        ll_item.visibility = View.VISIBLE
    }

    fun onFailureSeason(obj: SportsApiErrorModel?) {
        txtMsg.visibility = View.VISIBLE
        ll_item.visibility = View.GONE
        txtMsg.text = obj!!.response     }
}