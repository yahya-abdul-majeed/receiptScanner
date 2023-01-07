package com.yahya.receiptapp.utility

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.yahya.receiptapp.R

var notificationID = 1;
const val channelId = "1";

class NotificationHelper : BroadcastReceiver(){

    override fun onReceive(context: Context?, p1: Intent?) {
        val notification = NotificationCompat.Builder(context!!, channelId)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle("notification_title")
            .setContentText("notification_text")
            .build()

        val manager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        manager.notify(notificationID, notification)
    }


    fun createNotificationChannel(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val name = "my_notification_channel"
            val descriptionText = "this is my notification channel"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel("channel_id",name,importance).apply {
                description = descriptionText
            }
            //val notificationManager = _context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            //notificationManager.createNotificationChannel(channel)

        }
    }


}