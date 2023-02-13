package space.rozlach.myapplication.module.service

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import retrofit2.HttpException
import space.rozlach.myapplication.features.data.remote.ApiService
import space.rozlach.myapplication.features.data.repository.MeteoritesInfoRepositoryImpl
import javax.inject.Inject

class MyWorker @Inject constructor(
    private val context: Context,
    private val meteoriteRepository: MeteoritesInfoRepositoryImpl,
    workerParameters: WorkerParameters
) :
    CoroutineWorker(context, workerParameters) {

    override suspend fun doWork(): Result {

      try {
          meteoriteRepository.getMeteorites()
      }catch (e: HttpException)
      {
          return Result.retry()
      }
        return Result.success()
    }


}