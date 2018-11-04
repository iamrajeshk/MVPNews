package rajesh.com.mvpnews.util

import android.content.Context
import rajesh.com.mvpnews.data.NewsRepositories
import rajesh.com.mvpnews.data.NewsRepository

/**
 *Created by rajeshkantipudi on 04/11/18
 */

object Injection {

    private var NET_MANAGER: NetManager? = null

    private fun provideNetManager(context: Context): NetManager {
        if (NET_MANAGER == null) {
            NET_MANAGER = NetManager(context)
        }
        return NET_MANAGER!!
    }

    fun provideRepository(context: Context): NewsRepository {
        return NewsRepositories(provideNetManager(context)).getNewsRepo()
    }
}