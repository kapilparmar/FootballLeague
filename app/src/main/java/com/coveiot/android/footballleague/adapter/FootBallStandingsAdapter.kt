package com.coveiot.android.footballleague.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.coveiot.android.footballleague.R
import com.coveiot.android.footballleague.model.Datum
import com.coveiot.android.footballleague.model.standings.SessionDataMoedl
import com.coveiot.android.footballleague.model.standings.Standing
import com.coveiot.android.footballleague.model.standings.StandingsModel

class FootBallStandingsAdapter(
    private var dataSet:List<Standing>) :
        RecyclerView.Adapter<FootBallStandingsAdapter.ViewHolder>() {

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val txtName: TextView
        val txtAbbr: TextView
        val imgLogo: ImageView
        val llItem : LinearLayout

        init {
            // Define click listener for the ViewHolder's View.
            txtName = view.findViewById(R.id.txtName)
            txtAbbr = view.findViewById(R.id.txtabbr)
            imgLogo = view.findViewById(R.id.imgView)
            llItem = view.findViewById(R.id.ll_item)

        }
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
                .inflate(R.layout.football_item, viewGroup, false)


        return ViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        viewHolder.txtName.text = dataSet[position].team!!.name
        viewHolder.txtAbbr.text = dataSet[position].team!!.abbreviation
        Glide.with(viewHolder.imgLogo.context)
            .load(dataSet[position].team!!.logos!!.get(0))
            .into(viewHolder.imgLogo)

    }


    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount(): Int {
        return dataSet!!.size
    }

    fun notifyAdapter(dataList: List<Standing>) {
        this.dataSet = dataList
        notifyDataSetChanged()

    }

    interface OnItemClickListener {
        fun onItemClick(postition:Int)

    }
}