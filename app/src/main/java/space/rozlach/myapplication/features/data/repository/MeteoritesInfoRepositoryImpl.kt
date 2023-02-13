package space.rozlach.myapplication.features.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import space.rozlach.myapplication.features.data.local.AppDatabase
import space.rozlach.myapplication.recourse.Resource
import space.rozlach.myapplication.features.data.local.MeteoritesInfoDao
import space.rozlach.myapplication.features.data.remote.ApiService
import space.rozlach.myapplication.features.domain.model.Meteorite
import space.rozlach.myapplication.features.domain.repository.MeteoriteRepository
import java.io.IOException
import java.sql.DatabaseMetaData
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MeteoritesInfoRepositoryImpl @Inject constructor(
    private val api: ApiService,
    private val db: AppDatabase
) : MeteoriteRepository {

    private val dao = db.dao

    override suspend fun getMeteoriteInfo(name: String): Flow<Resource<Meteorite>> = flow {
        emit(Resource.Loading())
        val meteoriteInfo = dao.getInfoAboutMeteorite(name).toMeteorite()
        emit(Resource.Loading(data = meteoriteInfo))

        try {
            val remoteMeteoriteInfo = api.getListOfMeteorites()
            dao.deleteAllFromTable()
            dao.insertListOfMeteorites(remoteMeteoriteInfo.map { it.toMeteoriteEntity() })

        } catch (e: HttpException) {
            emit(Resource.Error("Oops, something went wrong!", meteoriteInfo))
        } catch (e: IOException) {
            emit(
                Resource.Error(
                    "Couldn't reach server, check your internet connection.",
                    meteoriteInfo
                )
            )
        }

        val newMeteoriteInfos = dao.getInfoAboutMeteorite(name).toMeteorite()
        emit(Resource.Success(newMeteoriteInfos))
    }

    override suspend fun getMeteorites(): Flow<Resource<List<Meteorite>>> = flow {
        emit(Resource.Loading())
        val meteoriteInfo = dao.getMeteoritesInfoList().map { it.toMeteorite() }
        emit(Resource.Loading(data = meteoriteInfo))

        try {
            val remoteMeteoriteInfo = api.getListOfMeteorites()
            dao.deleteAllFromTable()
            dao.insertListOfMeteorites(remoteMeteoriteInfo.map { it.toMeteoriteEntity() })

        } catch (e: HttpException) {
            emit(Resource.Error("Oops, something went wrong!", meteoriteInfo))
        } catch (e: IOException) {
            emit(
                Resource.Error(
                    "Couldn't reach server, check your internet connection.",
                    meteoriteInfo
                )
            )
        }

        val newMeteoriteInfos = dao.getMeteoritesInfoList().map { it.toMeteorite() }
        emit(Resource.Success(newMeteoriteInfos))
    }


}