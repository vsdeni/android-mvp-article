package vsdeni.com.androidmvp

import io.reactivex.Observable


interface CountriesRepository {
    fun getCountries(): Observable<Country>
}