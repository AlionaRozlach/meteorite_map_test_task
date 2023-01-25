package space.rozlach.myapplication.module.service

import android.app.job.JobParameters
import android.app.job.JobService
import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters
import space.rozlach.myapplication.module.api.ApiFactory
import space.rozlach.myapplication.module.database.AppDatabase
import space.rozlach.myapplication.module.map.model.Meteorite

class MyWorker(private val context: Context, workerParameters: WorkerParameters) :
    Worker(context, workerParameters) {

    override fun doWork(): Result {
        val call = ApiFactory.apiService.getListOfMeteorites()
        val response = call.execute()
        val db = AppDatabase.getInstance(context)

        if (response.code() == 200) {
            if (!response.body().isNullOrEmpty()) {
                db.meteoritesInfoDao().insertListOfMeteorites(response.body()!!)
                Log.d("WORKER_200",response.body().toString())
            }
        } else
            return Result.retry()


        return Result.retry()
    }


}