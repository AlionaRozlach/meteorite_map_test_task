package space.rozlach.myapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.work.*
import space.rozlach.myapplication.module.MeteoriteViewModel
import space.rozlach.myapplication.module.map.fragment.MapFragment
import space.rozlach.myapplication.module.service.MyWorker
import java.util.concurrent.TimeUnit


class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MeteoriteViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        var list: List<Meteorite>? = null
        val myFragment = MapFragment()
//        viewModel = ViewModelProviders.of(this)[MeteoriteViewModel::class.java]
//        viewModel.meteoritesList.observe(this, Observer{
//            println("SUCCCESS IN ACTIVITY")
//            list = it
//            println(it)
//            println(it.size)
//            if(myFragment.isAdded)
//                supportFragmentManager.beginTransaction().replace(R.id.frame_layout,myFragment )
//                .commit()
//            else
//                supportFragmentManager.beginTransaction().add(R.id.frame_layout,myFragment )
//                    .commit()
//        })

        val workManager = WorkManager.getInstance(application)

//        workManager.cancelAllWorkByTag("my_unique_worker")
//        workManager.pruneWork()

        val constraint: Constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()

        val workRequest = PeriodicWorkRequest.Builder(MyWorker::class.java, 1, TimeUnit.DAYS)
            .setConstraints(constraint)
            .build()

        workManager.enqueueUniquePeriodicWork(
            "my_unique_worker",
            ExistingPeriodicWorkPolicy.KEEP,
            workRequest
        )

        if(myFragment.isAdded)
                supportFragmentManager.beginTransaction().replace(R.id.frame_layout,myFragment )
                .commit()
            else
                supportFragmentManager.beginTransaction().add(R.id.frame_layout,myFragment )
                    .commit()


//        viewModel.getDetailInfo("Boumdeid (2011)").observe(this, Observer {
//            println(it)
//            println("Success Detail About Meteorite")
//        })



    }

}