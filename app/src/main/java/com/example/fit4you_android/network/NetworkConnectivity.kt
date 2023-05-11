package com.example.fit4you_android.network

import android.net.NetworkInfo

interface NetworkConnectivity {
    fun getNetworkInfo(): NetworkInfo?
    fun isConnected(): Boolean
}
