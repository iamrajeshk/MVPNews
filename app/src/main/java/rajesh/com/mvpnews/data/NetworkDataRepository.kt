package rajesh.com.mvpnews.data

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


/**
 *Created by rajeshkantipudi on 01/11/18
 */

/**
 * Network data source, provides data from api
 */
class NetworkDataRepository : NewsRepository {

    private val apiClient = ApiInterface.create()

    override fun getNews(callback: NewsRepository.LoadNewsCallback) {
        val apiCall = apiClient.getTopNews("US")
        apiCall.enqueue(object : Callback<TopHeadlinesResponse> {
            override fun onFailure(call: Call<TopHeadlinesResponse>, t: Throwable) {
                callback.onServerFailure()
            }

            override fun onResponse(call: Call<TopHeadlinesResponse>, response: Response<TopHeadlinesResponse>) {
                if (response.isSuccessful) {
                    val topHeadlinesResponse = response.body()
                    topHeadlinesResponse?.apply {
                        val iterator = this.articles.iterator()
                        while (iterator.hasNext()) {
                            if (iterator.next().urlToImage?.isEmpty() != false)
                                iterator.remove()
                        }
                        callback.onNewsLoaded(topHeadlinesResponse.articles)
                    }
                }
            }

        })
    }

}