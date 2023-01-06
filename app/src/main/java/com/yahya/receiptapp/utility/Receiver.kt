package com.yahya.receiptapp.utility

import android.app.Service
import android.content.Intent
import android.os.IBinder

class Receiver: Service() {
    override fun onBind(p0: Intent?): IBinder? {
        return null
    }

    override fun onCreate() {
        super.onCreate()
    }
}