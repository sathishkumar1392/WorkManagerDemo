package com.sathish.workmanagerdemo

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.WorkRequest

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val btnUploadWorker = findViewById<Button>(R.id.btn_upload)

        btnUploadWorker.setOnClickListener {
            val workManager = WorkManager.getInstance(this@MainActivity)
            val uploadWorkRequest: WorkRequest = OneTimeWorkRequestBuilder<UploadWorker>().build()
                workManager.enqueue(uploadWorkRequest)
                workManager.getWorkInfoByIdLiveData(uploadWorkRequest.id).observe(this, Observer {
                    Toast.makeText(this,it.state.toString(),Toast.LENGTH_LONG).show()
                })
        }
    }
}