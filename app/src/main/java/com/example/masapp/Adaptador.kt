package com.example.masapp

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter

internal class Adaptador (
        var context: Context,
        fm: FragmentManager,
        private var totalTabs: Int,
        private val btHandler: BTHandler
) : FragmentStatePagerAdapter(fm,BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT )
{
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