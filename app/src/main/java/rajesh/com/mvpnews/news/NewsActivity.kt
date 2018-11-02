package rajesh.com.mvpnews.news

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import rajesh.com.mvpnews.R

class NewsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            initFragment(NewsFragment.newInstance())
        }
    }

    private fun initFragment(notesFragment: Fragment) {
        // Add the NotesFragment to the layout
        val fragmentManager = supportFragmentManager
        val transaction = fragmentManager.beginTransaction()
        transaction.add(R.id.fragment, notesFragment)
        transaction.commit()
    }
}
