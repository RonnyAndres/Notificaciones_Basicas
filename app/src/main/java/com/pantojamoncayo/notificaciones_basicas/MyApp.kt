package com.pantojamoncayo.notificaciones_basicas

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import com.google.firebase.ktx.Firebase
import com.google.firebase.messaging.ktx.messaging

class MyApp: Application(){
    companion object{
        const val NOTIFICATION_CHANNEL_ID = "notificacion_fcm"
    }
    override fun onCreate() {
        super.onCreate()
        Firebase.messaging.token.addOnCompleteListener {
            if (!it.isSuccessful){
                println("Error al obtener el token")
                return@addOnCompleteListener
            }
            val token = it.result
            println("Token: $token")
        }
        createNotificationChannel()
    }

    private fun createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                NOTIFICATION_CHANNEL_ID,
                "Notificaciones FCM",
                NotificationManager.IMPORTANCE_HIGH,
            )
                channel.description = "Notificaciones desde FCM"
                val notificationManager = getSystemService(android.app.NotificationManager::class.java)
                notificationManager.createNotificationChannel(channel)

        }
    }
}