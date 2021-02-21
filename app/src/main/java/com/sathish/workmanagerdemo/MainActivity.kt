package com.sathish.workmanagerdemo

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.work.*

class MainActivity : AppCompatActivity() {

    companion object {
        const val KEY_COUNT_VALUE = "key_count_value"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val btnUploadWorker = findViewById<Button>(R.id.btn_upload)

        btnUploadWorker.setOnClickListener {
            val workManager = WorkManager.getInstance(this@MainActivity)
            val data: Data = Data.Builder().putInt(KEY_COUNT_VALUE, 300).build()
            val constraint = Constraints.Builder().setRequiredNetworkType(NetworkType.CONNECTED).build()
            val uploadWorkRequest: WorkRequest = OneTimeWorkRequestBuilder<UploadWorker>()
                    .setConstraints(constraint)
                    .setInputData(data)
                    .build()
            workManager.enqueue(uploadWorkRequest)
            workManager.getWorkInfoByIdLiveData(uploadWorkRequest.id).observe(this, Observer {
                Toast.makeText(this, it.state.toString(), Toast.LENGTH_LONG).show()
            })
        }
    }
}