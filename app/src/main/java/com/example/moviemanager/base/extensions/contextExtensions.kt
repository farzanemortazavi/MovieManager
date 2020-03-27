package com.example.moviemanager.base.extensions

import android.content.Context
import android.net.ConnectivityManager


fun Context.showToast(message:String,duration:Int= android.widget.Toast.LENGTH_SHORT){
    android.widget.Toast.makeText(this, message , duration).show()
}
//----------------------------------------------------------------
fun Context.logData(message:String){
    android.util.Log.d("myError",message)
}
//-----------------------------------------------------------------
fun Context.isInternetAvailable(): Boolean {

    val manager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val activeNetworkInfo = manager.activeNetworkInfo
    return activeNetworkInfo != null && activeNetworkInfo.isConnected
}
//-------------------------------------------------------------------