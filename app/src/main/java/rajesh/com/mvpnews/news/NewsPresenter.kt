package rajesh.com.mvpnews.news

import rajesh.com.mvpnews.data.NewsRepository
import rajesh.com.mvpnews.data.TopHeadlinesResponse

/**
 *Created by rajeshkantipudi on 01/11/18
 */

/**
 * Presenter layer with an instance of repo and View(Activity or fragment) passed from the View and implement Contract.UserActionListener
 */
class NewsPresenter(
    private val mNewsRepository: NewsRepository,
    val mNewsView: NewsContract.View
) : NewsContract.UserActionListener {

    override fun loadNews() {
        mNewsView.setProgressBarIndicator(true)
        mNewsRepository.getNews(object : NewsRepository.LoadNewsCallback {
            override fun onServerFailure(errorBody: String?) {
                mNewsView.showErrorMessage(errorBody)
            }

            override fun onNewsLoaded(articles: ArrayList<TopHeadlinesResponse.Article>) {
                mNewsView.setProgressBarIndicator(false)
                mNewsView.showNews(articles)
            }
        })
    }

    override fun openNewsDetail() {
        TODO("not implemented")
    }

}