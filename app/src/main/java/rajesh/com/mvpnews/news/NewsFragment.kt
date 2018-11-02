package rajesh.com.mvpnews.news

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.azoft.carousellayoutmanager.CarouselLayoutManager
import com.azoft.carousellayoutmanager.CarouselZoomPostLayoutListener
import com.azoft.carousellayoutmanager.CenterScrollListener
import kotlinx.android.synthetic.main.fragment_news.*
import rajesh.com.mvpnews.R
import rajesh.com.mvpnews.data.NewsRepositories
import rajesh.com.mvpnews.data.TopHeadlinesResponse
import rajesh.com.mvpnews.util.BaseFragment


/**
 *Created by rajeshkantipudi on 01/11/18
 */

/**
 * News fragment to display news that extends Contract.View
 * Uses Carousel LayoutManager to display data in a Carousel view
 */
class NewsFragment : BaseFragment(), NewsContract.View, NewsRecyclerViewAdapter.NewsItemClickListener {
    companion object {
        fun newInstance(): NewsFragment {
            return NewsFragment()
        }
    }

    private lateinit var mUserActionListener: NewsContract.UserActionListener
    private lateinit var mAdapter: NewsRecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mAdapter = NewsRecyclerViewAdapter(ArrayList(), this)
        mUserActionListener = NewsPresenter(NewsRepositories.getNewsRepo(), this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView.apply {
            //layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
            val carouselLayoutManager = CarouselLayoutManager(CarouselLayoutManager.HORIZONTAL)
            carouselLayoutManager.setPostLayoutListener(CarouselZoomPostLayoutListener())
            layoutManager = carouselLayoutManager
            adapter = mAdapter
            carouselLayoutManager.isItemPrefetchEnabled = true
            carouselLayoutManager.offsetChildrenHorizontal(50)
            addOnScrollListener(CenterScrollListener())
            setHasFixedSize(true)
        }
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        retainInstance = true
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.fragment_news, container, false)
    }

    override fun onResume() {
        super.onResume()
        mUserActionListener.loadNews()
    }

    override fun showErrorMessage() {
        Toast.makeText(activity, (activity as Context).resources.getString(R.string.server_error), Toast.LENGTH_SHORT)
            .show()
    }


    override fun onNewsItemClicked(article: TopHeadlinesResponse.Article) {
        TODO("not implemented")
    }

    override fun setProgressBarIndicator(active: Boolean) {
        if (active) progressBar.visibility = View.VISIBLE else progressBar.visibility = View.GONE
    }

    override fun showNews(articles: ArrayList<TopHeadlinesResponse.Article>) {
        mAdapter.replaceData(articles)
    }

    override fun showNewsDetailUI(noteTitle: String) {
        TODO("not implemented")
    }

}