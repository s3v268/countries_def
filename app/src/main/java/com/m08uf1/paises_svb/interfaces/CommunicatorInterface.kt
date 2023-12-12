package com.m08uf1.paises_svb.interfaces

import com.m08uf1.paises_svb.models.Country

interface CommunicatorInterface {
    //
    fun goToFragment(fragmentNum : Int)
    fun sendNotificationMockup(tab_position: Int)
    //fun changeTheme(themeName : name)
    fun onDataReceived(dat:MutableList<Country>)
}