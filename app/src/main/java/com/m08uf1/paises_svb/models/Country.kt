package com.m08uf1.paises_svb.models

import com.google.gson.annotations.SerializedName;

data class Countries (
    val countries: MutableList<Country>
)

data class Country (
    @SerializedName("name_en")
    val nameEn: String,
    @SerializedName("name_es")
    val nameEs: String,
    @SerializedName("continent_en")
    val continentEn: ContinentEn,
    @SerializedName("continent_es")
    val continentEs: ContinentEs,
    val capitalEn: String,
    val capitalEs: String,
    val dialCode: String,
    val code2: String,
    val code3: String,
    val tld: String,
    val km2: Double,
    val emoji: String
)

enum class ContinentEn {
    Africa,
    Antarctica,
    Asia,
    Europe,
    NorthAmerica,
    Oceania,
    SouthAmerica
}

enum class ContinentEs {
    AméricaDelNorte,
    AméricaDelSur,
    Antártida,
    Asia,
    Europa,
    Oceanía,
    África
}