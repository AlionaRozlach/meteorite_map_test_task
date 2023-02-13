package space.rozlach.myapplication.module.meteorites.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import space.rozlach.myapplication.R
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import space.rozlach.myapplication.MainActivity
import space.rozlach.myapplication.module.MeteoriteViewModel
import space.rozlach.myapplication.features.data.remote.dto.MeteoriteDto

private const val METEORITE_ARG = "meteorite"

class MeteoriteDetailFragment : Fragment() {

    private lateinit var  viewModel: MeteoriteViewModel
    private var meteoriteDto: MeteoriteDto? = null
    private lateinit var nameTextView: TextView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        viewModel = ViewModelProviders.of(requireActivity())[MeteoriteViewModel::class.java]

        return inflater.inflate(R.layout.meteorite_detail_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        nameTextView = view.findViewById(R.id.meteoriteName)

        viewModel.mutableSelectedItem.observe(activity as MainActivity, Observer {
            println("INSIDEA OBSERVER")
            println(it)
            meteoriteDto = it
        })

        println("METEEEEEORITEEEEEEEEEEEEEEEEE")
        println(meteoriteDto)
        if (meteoriteDto?.name != null) {
            viewModel.getDetailInfo(meteoriteDto?.name!!).observe(activity as MainActivity, Observer {
                nameTextView.text = it.toString()
            })
        }

    }

}