package space.rozlach.myapplication.features.domain.repository

import kotlinx.coroutines.flow.Flow
import space.rozlach.myapplication.recourse.Resource
import space.rozlach.myapplication.features.domain.model.Meteorite

interface MeteoriteRepository {

    suspend fun getMeteoriteInfo(name: String) : Flow<Resource<Meteorite>>
    suspend fun getMeteorites() : Flow<Resource<List<Meteorite>>>


}