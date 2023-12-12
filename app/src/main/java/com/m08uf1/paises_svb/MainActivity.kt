package com.m08uf1.paises_svb

import android.content.Context
import android.content.SharedPreferences
import android.content.SharedPreferences.Editor
import android.content.res.ColorStateList
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import com.m08uf1.paises_svb.adapters.ViewPagerAdapter
import com.m08uf1.paises_svb.databinding.ActivityMainBinding
import com.m08uf1.paises_svb.fragments.CountryList
import com.m08uf1.paises_svb.fragments.CountryQuiz
import com.m08uf1.paises_svb.interfaces.CommunicatorInterface
import com.m08uf1.paises_svb.models.Country

class MainActivity : AppCompatActivity(), CommunicatorInterface {
    lateinit var binding : ActivityMainBinding
    lateinit var pager : ViewPager2
    lateinit var adapter : FragmentStateAdapter
    lateinit var sharedPreferences: SharedPreferences
    lateinit var editor : Editor
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setTheme(themeSetup())
        setContentView(binding.root)

        viewPager2Setup() //ViewPager gestiona y nos permite editar el cambio de fragment
        val colorStateList : ColorStateList = colorStateListSetup() //ColorStateList nos permite canviar el color de una view automáticamente según su estado (enabled, selcted, etc.)
        tabLayoutMediatorSetup(colorStateList) //tabLayoutMediator conecta el tabLayout amb ViewPager2

    }
    override fun onDataReceived(dat: MutableList<Country>) {
        val quizFragment = supportFragmentManager.findFragmentByTag("f" + 1) as? CountryQuiz
        quizFragment?.updateData(dat)
    }

    private fun themeSetup(): Int {
        //get current theme from preferences
        sharedPreferences = getSharedPreferences("ac01Prefs", Context.MODE_PRIVATE)
        editor = sharedPreferences.edit()
        //returns selected theme id from sp
        return sharedPreferences.getInt("selectedTheme", R.style.Base_Theme_Paises_svb)
    }

    private fun tabLayoutMediatorSetup(colorStateList : ColorStateList) {
        TabLayoutMediator(binding.tabLayoutCustom, pager, true, true){
                tab, position ->
            tab.setCustomView(R.layout.layout_custom_tab)
            tab.customView?.findViewById<ImageView>(R.id.tab_icon)?.imageTintList = colorStateList
            tab.customView?.findViewById<TextView>(R.id.tab_tv)?.setTextColor(colorStateList)
            when(position){
                0->{
                    tab.customView?.findViewById<TextView>(R.id.tab_tv)?.visibility = View.VISIBLE
                    tab.customView?.findViewById<ImageView>(R.id.tab_icon)?.setImageResource(R.drawable.ic_country_list)
                    tab.customView?.findViewById<TextView>(R.id.tab_tv)?.setText(R.string.tv_vptab_list)
                }
                1->{
                    tab.customView?.findViewById<ImageView>(R.id.tab_icon)?.setImageResource(R.drawable.ic_country_game)
                    tab.customView?.findViewById<TextView>(R.id.tab_tv)?.setText(R.string.tv_vptab_game)
                }
                2->{
                    tab.customView?.findViewById<ImageView>(R.id.tab_icon)?.setImageResource(R.drawable.ic_settings)
                    tab.customView?.findViewById<TextView>(R.id.tab_tv)?.setText(R.string.tv_vptab_settings)
                }
            }

            /* si a cada tab vols fer una cosa diferent (en aquest cas no) Elimina les dues linees anteriors i  ->
            when(position){
                0->{tab.customView?.findViewById<ImageView>(R.id.tab_icon)?.setImageResource(R.drawable.icon_custom_notification)
                    tab.customView?.findViewById<TextView>(R.id.tab_tv)?.setTextColor(colorStateList)
                }
                1->{tab.customView?.findViewById<ImageView>(R.id.tab_icon)?.setImageResource(R.drawable.icon_custom_notification)
                    tab.customView?.findViewById<TextView>(R.id.tab_tv)?.setTextColor(colorStateList)
                }
            }
             */

        }.attach()
    }

    fun viewPager2Setup(){
        pager = binding.viewPagerCustom
        adapter = ViewPagerAdapter(supportFragmentManager,lifecycle)
        pager.adapter = adapter

        pager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                val tab = binding.tabLayoutCustom.getTabAt(position)
                val tabArray = arrayListOf(binding.tabLayoutCustom.getTabAt(0), binding.tabLayoutCustom.getTabAt(1), binding.tabLayoutCustom.getTabAt(2))
                tabArray.removeAt(position)
                tab?.customView?.findViewById<TextView>(R.id.tab_tv)?.visibility = View.VISIBLE
                //l'animacio quan canvia la visibilitat es gestiona en el layout root the la custom tab
                //és la linia android:animateLayoutChanges="true"
                tabArray.forEach(){it?.customView?.findViewById<TextView>(R.id.tab_tv)?.visibility = View.GONE }
                super.onPageSelected(position)
                //enviem les dades al joc al canviar the fragment amb swipe
                if (position == 1) { // Check if frag és CountryQuiz (pos = 1)
                    val countryListFragment = supportFragmentManager.findFragmentByTag("f0") as? CountryList
                    countryListFragment?.sendGameData()
                }
            }
        })
    }

    private fun colorStateListSetup(): ColorStateList {
        val states = arrayOf(
            intArrayOf(android.R.attr.state_selected), // on selected
            intArrayOf(android.R.attr.state_enabled) // on enabled
        )
        val colors = intArrayOf(
            getColor(R.color.f1_red),
            getColor(R.color.black)
        )
        return ColorStateList(states, colors)
    }

    override fun goToFragment(fragmentNum: Int) {
    }

    override fun sendNotificationMockup(tab_position: Int) {
    }

}