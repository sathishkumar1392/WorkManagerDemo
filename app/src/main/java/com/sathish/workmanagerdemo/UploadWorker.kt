package com.sathish.workmanagerdemo

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters

class UploadWorker(context: Context,workerParameters: WorkerParameters) : Worker(context,workerParameters){
    override fun doWork(): Result {
       return try{
           (1..1000).forEach {
               Log.i("MYTAG", "Uploading $it")
           }
           Result.success()
       }catch (e: Exception){
           Result.failure()
       }
    }

}