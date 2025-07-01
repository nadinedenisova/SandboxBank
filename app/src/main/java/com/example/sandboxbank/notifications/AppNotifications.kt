package com.example.sandboxbank.notifications

import android.Manifest
import android.app.Activity
import android.app.Activity.NOTIFICATION_SERVICE
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.sandboxbank.R
import kotlin.random.Random


private const val CHANNEL_ID = "first_channel_id"
const val NOTIFICATION_ID = 1
const val NOTIFICATION_FOR_ERROR = 99

object AppNotifications {


    fun checkRights(context: Context, activity: Activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ActivityCompat.checkSelfPermission(
                    context,
                    Manifest.permission.POST_NOTIFICATIONS
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                ActivityCompat.requestPermissions(
                    activity,
                    arrayOf(Manifest.permission.POST_NOTIFICATIONS),
                    NOTIFICATION_ID
                )
            }
        }
    }

    fun createNotificationChannel(context: Context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "Sandbox: уведомления о процентах и платежах"
            val descriptionText = "Информация от банка"
            val importance = NotificationManager.IMPORTANCE_HIGH
            val mChannel = NotificationChannel(CHANNEL_ID, name, importance)
            mChannel.description = descriptionText
            val notificationManager =
                context.getSystemService(NOTIFICATION_SERVICE) as NotificationManager
            val existingChannel = notificationManager.getNotificationChannel(CHANNEL_ID)
            if (existingChannel == null) notificationManager.createNotificationChannel(mChannel)
        }
    }


    fun showNotification(
        id: Int,
        context: Context,
        title: String,
        text: String,
        icon: Int
    ) {
        val builder = NotificationCompat.Builder(context, CHANNEL_ID)
            .setSmallIcon(icon)
            .setContentTitle(title)
            .setContentText(text)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setAutoCancel(true)

        with(NotificationManagerCompat.from(context)) {
            if (ActivityCompat.checkSelfPermission(
                    context,
                    Manifest.permission.POST_NOTIFICATIONS
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                return@with
            }
            notify(id, builder.build())
        }
    }

    fun hideNotification(
        id: Int,
        context: Context
    ) {
        val notificationManager = NotificationManagerCompat.from(context)
        notificationManager.cancel(id)
    }

    fun hideAll(
        context: Context
    ) {
        val notificationManager = NotificationManagerCompat.from(context)
        notificationManager.cancelAll()
    }

    fun showMockNotification(context: Context){
        val value = Random.nextInt(1,2)
        val sum = "%.2f".format(Random.nextDouble(100.00, 1000.00))

        if (value == 1){
            showNotification(
                id = NOTIFICATION_ID,
                context = context,
                title = "Скоро выплата процентов",
                text = "По вашему вкладу ожидаются проценты в размере ${sum} руб.",
                icon = R.drawable.leading_icon
            )
        } else {
            showNotification(
                id = NOTIFICATION_ID,
                context = context,
                title = "Необходимо внести платёж",
                text = "По вашему кредиту требуется внести платёж в размере ${sum} руб.",
                icon = R.drawable.icon_percent
            )
        }
    }

}