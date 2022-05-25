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

class FootBalLeagueAdapter(
    private var dataSet:List<Datum>,
    private var onItemClickListener:OnItemClickListener) :
        RecyclerView.Adapter<FootBalLeagueAdapter.ViewHolder>() {

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
        viewHolder.txtName.text = dataSet[position].name
        viewHolder.txtAbbr.text = dataSet[position].abbr
        Glide.with(viewHolder.imgLogo.context)
            .load(dataSet[position].logos!!.light)
            .into(viewHolder.imgLogo)
        viewHolder.llItem.setOnClickListener(View.OnClickListener {
            onItemClickListener.onItemClick(position)
        })

    }


    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount(): Int {
        return dataSet!!.size
    }

    fun notifyAdapter(dataList: List<Datum>) {
        this.dataSet = dataList
        notifyDataSetChanged()

    }

    interface OnItemClickListener {
        fun onItemClick(postition:Int)

    }
}