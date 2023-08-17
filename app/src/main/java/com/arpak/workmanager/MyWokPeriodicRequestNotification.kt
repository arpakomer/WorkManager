package com.arpak.workmanager

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.work.Worker
import androidx.work.WorkerParameters


class MyWokPeriodicRequestNotification(appContext: Context, workerParams: WorkerParameters) :
    Worker(appContext, workerParams) {


    override fun doWork(): Result {

        createNotification()

        return Result.success()
    }


    fun createNotification() {

        val builder: NotificationCompat.Builder
        val notificationManager =
            applicationContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        val intent = Intent(applicationContext, MainActivity::class.java)

        val goToIntent = PendingIntent.getActivity(applicationContext, 1, intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE)


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            val canalId = "canalId"
            val canalName = "canalName"
            val canalIntroduce = "canalIntroduce"
            val canalPrecedence = NotificationManager.IMPORTANCE_HIGH

            var canal: NotificationChannel? = notificationManager.getNotificationChannel(canalId)

            if (canal == null) {
                canal = NotificationChannel(canalId, canalName, canalPrecedence)
                canal.description = canalIntroduce
                notificationManager.createNotificationChannel(canal)
            }
            builder = NotificationCompat.Builder(applicationContext,canalId)
            builder.setContentTitle("Title")
                .setContentText("Content")
                .setSmallIcon(R.drawable.circle_notifications)
                .setContentIntent(goToIntent)
                .setAutoCancel(true)


        } else {

            builder = NotificationCompat.Builder(applicationContext)

            builder.setContentTitle("Title")
                .setContentText("Content")
                .setSmallIcon(R.drawable.circle_notifications)
                .setContentIntent(goToIntent)
                .setAutoCancel(true)
                .priority = Notification.PRIORITY_HIGH

        }

        notificationManager.notify(1, builder.build())
    }


}