package com.arpak.workmanager

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.work.*
import com.arpak.workmanager.databinding.ActivityMainBinding
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)



        binding.buttonTakeAction.setOnClickListener {

            /*
               // it won't work if not connected to internet(wi-fi and mobile)
                       val workingCondition = Constraints.Builder()
                           .setRequiredNetworkType(NetworkType.CONNECTED)
                           .build()

                       *//*  method that runs every time the button is pressed
            val request = OneTimeWorkRequestBuilder<MyWorker>().build()
            *//*


            //method that works with delay every time the button is pressed
                val requestOneTimeRequest = OneTimeWorkRequestBuilder<MyWorker>()
                .setInitialDelay(10, TimeUnit.SECONDS)
                .setConstraints(workingCondition)
                .build()
*/

            val requestPeriodicWork =
                PeriodicWorkRequestBuilder<MyWokPeriodicRequestNotification>(15, TimeUnit.MINUTES)
                    .setInitialDelay(10, TimeUnit.SECONDS)
                    .build()

            WorkManager.getInstance(this@MainActivity).enqueue(requestPeriodicWork)


/*             // Observing our work in the background
            WorkManager.getInstance(this@MainActivity).getWorkInfoByIdLiveData(requestPeriodicWork.id)
                .observe(this){
                    val state = it.state.name
                    Log.e("Backqround process State", state)
                }

        }*/

        }
    }
}