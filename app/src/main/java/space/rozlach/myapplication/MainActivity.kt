package space.rozlach.myapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import space.rozlach.myapplication.module.MeteoriteViewModel
import space.rozlach.myapplication.module.api.ApiFactory


class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MeteoriteViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProviders.of(this).get(MeteoriteViewModel::class.java)
        viewModel.meteoritesList.observe(this, Observer{
            println("SUCCCESS IN ACTIVITY")
            println(it)
        })

        viewModel.getDetailInfo("Boumdeid (2011)").observe(this, Observer {
            println(it)
            println("Success Detail About Meteorite")
        })

    }

}