package space.rozlach.myapplication.module

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import space.rozlach.myapplication.features.data.local.AppDatabase
import space.rozlach.myapplication.features.data.remote.dto.MeteoriteDto

class MeteoriteViewModel(application: Application) : AndroidViewModel(application) {
    private val db = AppDatabase.getInstance(application)

    val mutableSelectedItem = MutableLiveData<MeteoriteDto>()
    val meteoritesList = db.meteoritesInfoDao().getMeteoritesInfoList()

    fun getDetailInfo(name: String): LiveData<MeteoriteDto> {
        return db.meteoritesInfoDao().getInfoAboutMeteorite(name)
    }

    fun getMeteoritesListVM(): LiveData<List<MeteoriteDto>> {
        return db.meteoritesInfoDao().getMeteoritesInfoList()
    }

    fun selectItem(meteoriteDto: MeteoriteDto) {
        mutableSelectedItem.value = meteoriteDto
    }

}