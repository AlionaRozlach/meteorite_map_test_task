package space.rozlach.myapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import space.rozlach.myapplication.module.MeteoriteViewModel
import space.rozlach.myapplication.module.map.fragment.MapFragment
import space.rozlach.myapplication.module.map.model.Meteorite


class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MeteoriteViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var list: List<Meteorite>? = null
        val myFragment = MapFragment()
        viewModel = ViewModelProviders.of(this)[MeteoriteViewModel::class.java]
        viewModel.meteoritesList.observe(this, Observer{
            println("SUCCCESS IN ACTIVITY")
            list = it
            println(it)
            println(it.size)
            if(myFragment.isAdded)
                supportFragmentManager.beginTransaction().replace(R.id.frame_layout,myFragment )
                .commit()
            else
                supportFragmentManager.beginTransaction().add(R.id.frame_layout,myFragment )
                    .commit()
        })



//        viewModel.getDetailInfo("Boumdeid (2011)").observe(this, Observer {
//            println(it)
//            println("Success Detail About Meteorite")
//        })



    }

}