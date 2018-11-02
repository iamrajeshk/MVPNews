package rajesh.com.mvpnews.data

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
        fun getNewsRepo(): NewsRepository = repository ?: synchronized(this) {
            repository ?: buildRepository().also { repository = it }
        }

        private fun buildRepository() = NetworkDataRepository()
    }

}