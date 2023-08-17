package com.arpak.workmanager

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters

class MyWorker(appContext: Context, workerParams: WorkerParameters): Worker(appContext,workerParams) {

    override fun doWork(): Result {

        val total = 65 + 65
        Log.e("Background processing total",total.toString())

        return Result.success()
    }

}