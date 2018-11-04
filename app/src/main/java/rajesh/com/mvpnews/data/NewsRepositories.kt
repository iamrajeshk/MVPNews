package rajesh.com.mvpnews.data

import rajesh.com.mvpnews.util.NetManager

/**
 *Created by rajeshkantipudi on 01/11/18
 */

class NewsRepositories(private val netManager: NetManager) {


    private val networkRepository = NetworkRepository()
    private val localRepository = LocalRepository()
    /**
     * Check network connectivity and use the repo accordingly
     */
    fun getNewsRepo(): NewsRepository {
        return if (netManager.isConnectedToInternet) networkRepository else localRepository
    }
}