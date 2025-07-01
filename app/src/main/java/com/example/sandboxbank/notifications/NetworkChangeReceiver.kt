package com.example.sandboxbank.notifications

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import com.example.sandboxbank.R

class NetworkChangeReceiver : BroadcastReceiver() {


    override fun onReceive(context: Context, intent: Intent?) {
        if (!isInternetAvailable(context)) {
            AppNotifications.showNotification(
                id = NOTIFICATION_FOR_ERROR,
                context = context,
                title = "Интернет не доступен",
                text = "Операции приостановлены",
                icon = R.drawable.ic_error,
            )
        } else {
            AppNotifications.hideNotification(
                context = context,
                id = NOTIFICATION_FOR_ERROR
            )
        }
    }


    fun isInternetAvailable(context: Context): Boolean {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val connectivityManager =
                context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val network = connectivityManager.activeNetwork

            val capabilities = connectivityManager.getNetworkCapabilities(network)
            return capabilities != null &&
                    (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) ||
                            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) ||
                            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET))
        } else {
            return true
        }

    }

}