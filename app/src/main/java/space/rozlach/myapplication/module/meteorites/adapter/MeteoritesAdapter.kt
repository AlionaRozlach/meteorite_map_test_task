package space.rozlach.myapplication.module.meteorites.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import space.rozlach.myapplication.R
import space.rozlach.myapplication.module.map.model.Meteorite

class MeteoritesAdapter(private val meteorites: List<Meteorite>) : RecyclerView.Adapter<MeteoritesAdapter.ViewHolder>() {
//    var meteoriteList = ArrayList<Meteorite?>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflates the card_view_design view
        // that is used to hold list item
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.meteorite_item, parent, false)

        return ViewHolder(view)
    }

    // binds the list items to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

//        meteoriteMutableList = meteorites
        val meteorite = meteorites[position]

        // sets the image to the imageview from our itemHolder class

        // sets the text to the textview from our itemHolder class
        holder.textView.text = meteorite.name


    }

    interface UpdateData {
        fun updateMeteoritesList(list: List<Meteorite>)

    }
//    @SuppressLint("NotifyDataSetChanged")
//    fun updateMeteoritesList(meteorites: ArrayList<Meteorite?>) {
//        meteoriteList.clear()
//        meteoriteList = meteorites
//        notifyDataSetChanged()
//    }

    // return the number of the items in the list
    override fun getItemCount(): Int {
        return meteorites.size
    }

    // Holds the views for adding it to image and text
    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val textView: TextView = itemView.findViewById(R.id.meteoriteName)
    }
}


