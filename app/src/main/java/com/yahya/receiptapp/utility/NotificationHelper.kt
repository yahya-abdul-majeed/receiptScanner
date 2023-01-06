package com.yahya.receiptapp.utility

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.yahya.receiptapp.R

class NotificationHelper (context:Context){
    private var _context = context

    var builder = NotificationCompat.Builder(_context,"channel_id")
        .setSmallIcon(R.drawable.ic_launcher_foreground)
        .setContentTitle("notification_title")
        .setContentText("notification_text")
        .setPriority(NotificationCompat.PRIORITY_DEFAULT)

    fun sendNotification(){
        with(NotificationManagerCompat.from(_context)){
            notify(76,builder.build())
        }
    }


    fun createNotificationChannel(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val name = "my_notification_channel"
            val descriptionText = "this is my notification channel"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel("channel_id",name,importance).apply {
                description = descriptionText
            }
            val notificationManager = _context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)

        }
    }
}