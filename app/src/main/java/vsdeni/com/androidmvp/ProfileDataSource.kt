package vsdeni.com.androidmvp

import io.reactivex.Completable
import io.reactivex.Maybe


class ProfileDataSource : ProfileRepository {
    override fun saveUserCountry(country: Country): Completable {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun saveUserName(name: String): Completable {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getUserCountry(): Maybe<Country> =
            Maybe.just(Country(172, "Ukraine", "A-32.png", "A-128.png"))

    override fun getUserName(): Maybe<String> =
            Maybe.just("Denys Vasylenko")
}