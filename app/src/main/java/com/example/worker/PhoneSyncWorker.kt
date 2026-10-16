package com.example.worker

import android.content.Context
import android.util.Log
import androidx.work.Constraints
import androidx.work.CoroutineWorker
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.NetworkType
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.WorkerParameters
import com.example.data.PhoneRepositoryImpl
import java.util.concurrent.TimeUnit

class PhoneSyncWorker(
    appContext: Context,
    workerParams: WorkerParameters
) : CoroutineWorker(appContext, workerParams) {

    override suspend fun doWork(): Result {
        return try {
            Log.d("PhoneSyncWorker", "Executing scheduled 24-hr phone specs sync")
            val repository = PhoneRepositoryImpl(applicationContext)
            repository.refresh(force = true)
            Result.success()
        } catch (e: Exception) {
            Log.w("PhoneSyncWorker", "Background sync encountered error, falling back to cache", e)
            Result.retry()
        }
    }

    companion object {
        const val WORK_NAME = "hatifi_24hr_phone_sync"

        fun schedulePeriodicSync(context: Context) {
            val constraints = Constraints.Builder()
                .setRequiredNetworkType(NetworkType.CONNECTED)
                .build()

            val syncRequest = PeriodicWorkRequestBuilder<PhoneSyncWorker>(
                24, TimeUnit.HOURS,
                15, TimeUnit.MINUTES // 15 min flex window
            )
                .setConstraints(constraints)
                .build()

            WorkManager.getInstance(context).enqueueUniquePeriodicWork(
                WORK_NAME,
                ExistingPeriodicWorkPolicy.KEEP,
                syncRequest
            )
        }
    }
}
