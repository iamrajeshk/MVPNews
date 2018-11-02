package rajesh.com.mvpnews.data

/**
 *Created by rajeshkantipudi on 01/11/18
 */

class NewsRepositories {
    companion object {
        private var repository: NewsRepository? = null

        fun getNewsRepo(): NewsRepository = repository ?: synchronized(this) {
            repository ?: buildRepository().also { repository = it }
        }

        private fun buildRepository() = NetworkDataRepository()
    }

}