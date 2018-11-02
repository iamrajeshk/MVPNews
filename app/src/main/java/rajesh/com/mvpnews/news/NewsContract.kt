package rajesh.com.mvpnews.news

import rajesh.com.mvpnews.data.TopHeadlinesResponse

/**
 *Created by rajeshkantipudi on 01/11/18
 */

/**
 * This specifies the contract between view and presenter
 */
interface NewsContract {
    interface View {
        fun setProgressBarIndicator(active: Boolean)
        fun showNews(articles: ArrayList<TopHeadlinesResponse.Article>)
        fun showNewsDetailUI(noteTitle: String)
        fun showErrorMessage()
    }

    interface UserActionListener {
        fun loadNews()
        fun openNewsDetail()
    }
}