package com.sathish.workmanagerdemo

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters

class UploadWorker(context: Context,workerParameters: WorkerParameters) : Worker(context,workerParameters){
    override fun doWork(): Result {
       return try{
               val count  = inputData.getInt(MainActivity.KEY_COUNT_VALUE,0)
           (1..count).forEach {
               Log.i("MYTAG", "Uploading $it")
           }
           Result.success()
       }catch (e: Exception){
           Result.failure()
       }
    }

}