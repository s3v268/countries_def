package com.m08uf1.paises_svb.interfaces

import com.m08uf1.paises_svb.models.Country

interface CommunicatorInterface {
    //utilitzem aquesta interface per comunicar l'activity amb el fragment que
    //envia les dades, en aquest cas CountryList envia a CountryQuiz
    fun onDataReceived(dat: MutableList<Country>)

    //no implementat no es necessari
    fun goToFragment(fragmentNum: Int)
    //no implementat no es necessari
    fun sendNotificationMockup(tab_position: Int)
}