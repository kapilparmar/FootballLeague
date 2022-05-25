package com.coveiot.android.footballleague

import android.content.Context
import android.net.ConnectivityManager

object Utils {

    fun isNetConnected(context: Context): Boolean {
        val connectivityManager = context.getSystemService("connectivity") as ConnectivityManager
        val info = connectivityManager.activeNetworkInfo
        return info != null && info.isAvailable && info.isConnected
    }
}