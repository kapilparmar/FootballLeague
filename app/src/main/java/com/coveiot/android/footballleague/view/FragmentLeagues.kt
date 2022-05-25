package com.coveiot.android.footballleague.view

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
import com.coveiot.android.footballleague.model.Datum
import com.coveiot.android.footballleague.model.LeagueDetailsModel
import com.coveiot.android.footballleague.viewmodel.ActivityFootballViewModel
import com.coveiot.android.footballleagues.SportsApiManager
import com.coveiot.android.footballleagues.network.SportsApiErrorModel
import com.coveiot.android.footballleagues.network.SportsApiListener
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_leagues.*
import retrofit2.Response


class FragmentLeagues() : Fragment(){

    private var viewModel:ActivityFootballViewModel? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_leagues, container, false)
    }

    companion object {
        fun newInstance(id: String) = FragmentLeagues().apply {
            val bundle = Bundle()
            bundle.putString(Constants.EXTRAS_ID, id)
            arguments = bundle
        }
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(requireActivity()).get(ActivityFootballViewModel::class.java)
        var bundle = arguments
        bundle.let {
          var  id = it!!.getString(Constants.EXTRAS_ID,null)
            if (Utils.isNetConnected(requireContext())){
                txtMsg.visibility = View.GONE
                ll_item.visibility = View.VISIBLE
                SportsApiManager.getFootballLeaguesDetails(id,
                    object : SportsApiListener<Response<LeagueDetailsModel?>?, SportsApiErrorModel?> {
                        override fun onSuccess(obj: Response<LeagueDetailsModel?>?) {
                            Log.d("TAG", "onSuccess: ${Gson().toJson(obj)}")
                            onSuccessLeagueDetails(obj)
                        }

                        override fun onError(obj: SportsApiErrorModel?) {
                            Log.d("TAG", "onError: ${Gson().toJson(obj)}")
                            onFailureLeagueDetails(obj)
                        }
                    })            }
            else{
                txtMsg.visibility = View.VISIBLE
                ll_item.visibility = View.GONE
                txtMsg.text = "No Internet Connection"
            }

        }
    }

    fun onSuccessLeagueDetails(obj: Response<LeagueDetailsModel?>?) {
        txtMsg.visibility = View.GONE
        ll_item.visibility = View.VISIBLE
        var dataum : Datum = obj!!.body()!!.data!!
        txtName.text = ("Name : "+dataum.name)
        txtabbr.text =("Abbrevation : "+dataum.abbr)
        txtslug.text = ("Slug : "+dataum.slug)
        Glide.with(imgView.context)
            .load(dataum.logos!!.dark)
            .into(imgView)
    }


    fun onFailureLeagueDetails(obj: SportsApiErrorModel?) {
        txtMsg.visibility = View.VISIBLE
        ll_item.visibility = View.GONE
        txtMsg.text = obj!!.response
    }


}