package com.example.copy_insta_fragment

import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter


class MainAdapter (fm:FragmentManager):FragmentStatePagerAdapter(fm){

    override fun getItem(position: Int): Fragment? {

        return when(position){

            0 -> AFragment()
            1 -> BFragment()

            else -> null
        }
    }

    override fun getCount() = 2

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        super.destroyItem(container, position, `object`)
    }
}