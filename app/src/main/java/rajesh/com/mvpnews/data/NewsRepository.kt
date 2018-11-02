package rajesh.com.mvpnews.data

/**
 *Created by rajeshkantipudi on 01/11/18
 */

/**
 * Interface defining the properties of a repository
 * Any repository that wants to provide data can implement this class
 */
interface NewsRepository {

    interface LoadNewsCallback {
        fun onNewsLoaded(articles: ArrayList<TopHeadlinesResponse.Article>)
        fun onServerFailure()
    }

    fun getNews(callback: LoadNewsCallback)
}