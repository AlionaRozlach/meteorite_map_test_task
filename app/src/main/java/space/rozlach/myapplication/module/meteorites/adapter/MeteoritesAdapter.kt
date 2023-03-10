package space.rozlach.myapplication.module.meteorites.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import space.rozlach.myapplication.R
import space.rozlach.myapplication.features.data.remote.dto.MeteoriteDto

class MeteoritesAdapter(fragment: Fragment, private val meteoriteDtos: List<MeteoriteDto>) : RecyclerView.Adapter<MeteoritesAdapter.ViewHolder>() {

    private val callback: OnClick = fragment as OnClick


    interface OnClick {
        fun onClick(meteoriteDto: MeteoriteDto)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflates the card_view_design view
        // that is used to hold list item
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.meteorite_item, parent, false)

        return ViewHolder(view)
    }

    // binds the list items to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val meteorite = meteoriteDtos[position]

        holder.textView.text = meteorite.name
        holder.meteoriteTypeName.text = meteorite.nameType
        holder.meteoriteMass.text = meteorite.mass
        holder.meteoriteFall.text = meteorite.fall
        holder.meteoriteYearFall.text = meteorite.year
//        holder.textView.text = meteorite.name

        holder.itemView.setOnClickListener {
            callback.onClick(meteorite)
        }
    }

    // return the number of the items in the list
    override fun getItemCount(): Int {
        return meteoriteDtos.size
    }

    // Holds the views for adding it to image and text
    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val textView: TextView = itemView.findViewById(R.id.meteoriteName)
        val meteoriteTypeName: TextView = itemView.findViewById(R.id.meteoriteTypeName)
        val meteoriteMass: TextView = itemView.findViewById(R.id.meteoriteMass)
        val meteoriteFall: TextView = itemView.findViewById(R.id.meteoriteFall)
        val meteoriteYearFall: TextView = itemView.findViewById(R.id.meteoriteYearFall)
    }
}


