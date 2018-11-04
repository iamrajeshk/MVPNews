package rajesh.com.mvpnews.data

import rajesh.com.mvpnews.util.NetManager

/**
 *Created by rajeshkantipudi on 01/11/18
 */

class NewsRepositories {
    companion object {
        private var repository: NewsRepository? = null
        /**
         * Returns the repository for data,
         * We can decide whether to fetch data from api or database
         * Multiple repos for multiple data source, maybe we can add network check to decide the repo selection
         */
        fun getNewsRepo(netManager: NetManager): NewsRepository = repository ?: synchronized(this) {
            repository ?: buildRepository(netManager).also { repository = it }
        }

        /**
         * Check network connectivity and use the repo accordingly
         */
        private fun buildRepository(netManager: NetManager): NewsRepository {

            return if (netManager.isConnectedToInternet) NetworkDataRepository() else DatabaseRepository()
        }
    }

}