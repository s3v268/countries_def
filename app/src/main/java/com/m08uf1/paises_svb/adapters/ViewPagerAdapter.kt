package com.m08uf1.paises_svb.adapters

import com.m08uf1.paises_svb.fragments.AppSettings
import com.m08uf1.paises_svb.fragments.CountryList
import com.m08uf1.paises_svb.fragments.CountryQuiz
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter

class ViewPagerAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle):
    FragmentStateAdapter(fragmentManager, lifecycle) {
    override fun getItemCount(): Int {
        return 3
    }
    override fun createFragment(position: Int): Fragment {
        when(position){
            0-> return CountryList()
            1-> return CountryQuiz()
            2-> return AppSettings()
            else -> return CountryList()
            //assignem una posició a cada fragment així com el fragment per defecte ->
        }
    }

}