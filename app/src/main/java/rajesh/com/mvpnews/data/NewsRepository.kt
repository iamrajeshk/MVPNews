package rajesh.com.mvpnews.data

/**
 *Created by rajeshkantipudi on 01/11/18
 */

interface NewsRepository {

    interface LoadNewsCallback {
        fun onNewsLoaded(articles: ArrayList<TopHeadlinesResponse.Article>)
        fun onServerFailure()
    }

    fun getNews(callback: LoadNewsCallback)
}