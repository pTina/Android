package com.example.copy_insta_fragment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.viewpager.widget.ViewPager
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private val adapter by lazy {MainAdapter(supportFragmentManager)}


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        ibtMainFavorite.setOnClickListener{
            ibtMainFavorite.setImageResource(R.drawable.favorite_fill)

        }

        ibtMainBookmark.setOnClickListener{
            ibtMainBookmark.setImageResource(R.drawable.bookmark_fill)
        }

        vpActivityMain.adapter = MainActivity@adapter


        vpActivityMain.addOnPageChangeListener(object:ViewPager.OnPageChangeListener{

            override fun onPageScrollStateChanged(state: Int) {

            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

            }

            override fun onPageSelected(position: Int) {
                ciMainActivity.selectDot(position)
            }
        })

        ciMainActivity.createDotPanel(2,R.drawable.indicator_dot_off,R.drawable.indicator_dot_on,0)
    }
}
