package space.rozlach.myapplication.module.api

import io.reactivex.Single
import retrofit2.Call
import retrofit2.http.GET
import space.rozlach.myapplication.module.map.model.Meteorite

interface ApiService {
    @GET("/resource/gh4g-9sfh.json")
//    fun getListOfMeteorites(): Single<ArrayList<Meteorite>>
    fun getListOfMeteorites(): Call<ArrayList<Meteorite>>
}