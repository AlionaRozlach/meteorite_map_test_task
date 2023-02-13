package space.rozlach.myapplication.features.data.remote

import retrofit2.Call
import retrofit2.http.GET
import space.rozlach.myapplication.features.data.remote.dto.MeteoriteDto

interface ApiService {
    @GET("/resource/gh4g-9sfh.json")
    suspend fun getListOfMeteorites(): ArrayList<MeteoriteDto>
}