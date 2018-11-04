package rajesh.com.mvpnews.util

import android.content.Context
import android.net.ConnectivityManager

/**
 *Created by rajeshkantipudi on 04/11/18
 */

class NetManager(private var applicationContext: Context) {
    val isConnectedToInternet: Boolean
        get() {
            val connectionManager =
                applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val networkInfo = connectionManager.activeNetworkInfo
            return networkInfo != null && networkInfo.isConnected
        }
}