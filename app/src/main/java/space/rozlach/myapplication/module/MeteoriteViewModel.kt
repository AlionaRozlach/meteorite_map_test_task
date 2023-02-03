package space.rozlach.myapplication.module

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import space.rozlach.myapplication.module.api.ApiFactory
import space.rozlach.myapplication.module.api.ApiService
import space.rozlach.myapplication.module.database.AppDatabase
import space.rozlach.myapplication.module.database.MeteoritesInfoDao
import space.rozlach.myapplication.module.map.model.Meteorite
import java.util.concurrent.TimeUnit

class MeteoriteViewModel(application: Application) : AndroidViewModel(application) {
    private val db = AppDatabase.getInstance(application)
    private val compositeDisposable = CompositeDisposable()
//    private val compositeDisposable = CompositeDisposable()

     val mutableSelectedItem = MutableLiveData<Meteorite>()

    val meteoritesList = db.meteoritesInfoDao().getMeteoritesInfoList()
     val selectedItem= mutableSelectedItem



    //    init {
//        loadData()
//    }
//
    fun getDetailInfo(name: String): LiveData<Meteorite> {
        return db.meteoritesInfoDao().getInfoAboutMeteorite(name)
    }

    fun getMeteoritesListVM(): LiveData<List<Meteorite>>{
        return db.meteoritesInfoDao().getMeteoritesInfoList()
    }

    fun getMeteorite(): LiveData<Meteorite>{
        return selectedItem
    }


    fun selectItem(meteorite: Meteorite) {
        mutableSelectedItem.value = meteorite
    }

//    fun isLocationPermissionAccepted():MutableLiveData<Boolean?>

//    private fun loadData() {
//        val disposable = ApiFactory.apiService.getListOfMeteorites()
//            .subscribeOn(Schedulers.io())
//            .subscribe(
//                {
//                    db.meteoritesInfoDao().insertListOfMeteorites(it)
//                    println("SUCCESS")
//                }, {
//                    println("Load data ERRRROR")
//                    println(it.message)
//                }
//            )
//        compositeDisposable.add(disposable)
//    }
//
//    override fun onCleared() {
//        super.onCleared()
//        compositeDisposable.dispose()
//    }
}