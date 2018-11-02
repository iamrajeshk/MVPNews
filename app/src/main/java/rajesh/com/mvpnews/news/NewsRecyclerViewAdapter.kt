package rajesh.com.mvpnews.news

import android.app.Activity
import android.content.Context
import android.support.v4.widget.CircularProgressDrawable
import android.support.v7.widget.RecyclerView
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.bumptech.glide.request.RequestOptions
import rajesh.com.mvpnews.R
import rajesh.com.mvpnews.data.TopHeadlinesResponse


/**
 *Created by rajeshkantipudi on 01/11/18
 */

class NewsRecyclerViewAdapter(
    private var articles: ArrayList<TopHeadlinesResponse.Article>,
    val newsItemClickListener: NewsItemClickListener
) : RecyclerView.Adapter<NewsRecyclerViewAdapter.ViewHolder>() {

    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        context = parent.context
        return ViewHolder(inflater.inflate(R.layout.item_news, parent, false))
    }

    override fun getItemCount(): Int = articles.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val article = articles[position]
        val displayMetrics = DisplayMetrics()
        (context as Activity).windowManager.defaultDisplay.getMetrics(displayMetrics)

        //To fix image height and width according to device metrice
        val deviceWidth = displayMetrics.widthPixels * 0.8
        val deviceHeight = displayMetrics.heightPixels * 0.7

        holder.imageView.layoutParams.width = deviceWidth.toInt()
        holder.imageView.layoutParams.height = deviceHeight.toInt()

        val circularProgressDrawable = CircularProgressDrawable(context)
        circularProgressDrawable.strokeWidth = 5f
        circularProgressDrawable.centerRadius = 30f
        circularProgressDrawable.start()

        val options = RequestOptions()
            .centerCrop()
            .placeholder(circularProgressDrawable)
            .error(R.drawable.ic_launcher_background)
            .priority(Priority.HIGH)

        Glide.with(context as Activity)
            .setDefaultRequestOptions(options)
            .load(article.urlToImage)
            .thumbnail(0.1f)
            .into(holder.imageView)
        holder.headerTextView.text = article.title
    }

    fun replaceData(articles: ArrayList<TopHeadlinesResponse.Article>) {
        this.articles = articles
        notifyDataSetChanged()
    }

    class ViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        val headerTextView = view.findViewById(R.id.headerTextView) as TextView
        val imageView = view.findViewById(R.id.imageView) as ImageView

    }

    interface NewsItemClickListener {
        fun onNewsItemClicked(article: TopHeadlinesResponse.Article)
    }
}