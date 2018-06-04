package vsdeni.com.androidmvp

import io.reactivex.Completable
import io.reactivex.Maybe


interface ProfileRepository {
    fun getUserCountry(): Maybe<Country>

    fun getUserName(): Maybe<String>

    fun saveUserCountry(country: Country): Completable

    fun saveUserName(name: String): Completable
}