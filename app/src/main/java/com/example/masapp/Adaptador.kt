package com.example.masapp

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

internal class Adaptador (
        var context: Context,
        fm: FragmentManager,
        var totalTabs: Int,
        val btHandler: BTHandler
) :
        FragmentPagerAdapter(fm) {
    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> {
                Despacho(btHandler)
            }
            1 -> {
                Predespacho(btHandler)
            }
            else -> getItem(position)
        }
    }
    override fun getCount(): Int {
        return totalTabs
    }
}