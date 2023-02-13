package space.rozlach.myapplication.module.meteorites.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import space.rozlach.myapplication.MainActivity
import space.rozlach.myapplication.R
import space.rozlach.myapplication.module.MeteoriteViewModel
import space.rozlach.myapplication.features.data.remote.dto.MeteoriteDto
import space.rozlach.myapplication.module.meteorites.adapter.MeteoritesAdapter

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [MeteoritesListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MeteoritesListFragment : Fragment(){
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var viewModel: MeteoriteViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var meteoritesAdapter: MeteoritesAdapter
    private var list=  ArrayList<MeteoriteDto>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.meteorites_fragment, container, false)
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = view.findViewById(R.id.meteoritesRecyclerView)
//
//        meteoritesAdapter = MeteoritesAdapter()
//        recyclerView.adapter = meteoritesAdapter
        recyclerView.layoutManager = LinearLayoutManager(context)
//        recyclerView.adapter = MeteoritesAdapter(this,list)

        viewModel = ViewModelProviders.of(this)[MeteoriteViewModel::class.java]
        viewModel.getMeteoritesListVM().observe(activity as MainActivity, Observer {meteorites->
            list.removeAll(list)
            // update with the new events that we have observed.
            list.addAll(meteorites)
            // tell the recycler view to update.
            recyclerView.adapter!!.notifyDataSetChanged()
        })




    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment MeteoritesListFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance() =
            MeteoritesListFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }


}