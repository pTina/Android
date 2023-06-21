package com.example.copy_instagram

import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter


class VpSecondActivityAdapter (fm:FragmentManager):FragmentStatePagerAdapter(fm){

    override fun getItem(position: Int): Fragment? {

        return when(position){

            0 -> VpSecondActivityFirstFragment()
            1 -> VpSecondActivitySecondFragment()

            else -> null
        }
    }

    override fun getCount() = 2

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        super.destroyItem(container, position, `object`)
    }
}