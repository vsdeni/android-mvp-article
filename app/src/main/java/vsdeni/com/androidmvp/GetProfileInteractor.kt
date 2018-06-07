package vsdeni.com.androidmvp

import io.reactivex.Single
import io.reactivex.functions.Function3


class GetProfileInteractor(private val profileRepository: ProfileRepository,
                           private val countriesRepository: CountriesRepository) {

    fun execute(): Single<ProfileViewModel> =
            Single.zip(
                    profileRepository
                            .getUserCountry()
                            .switchIfEmpty(Single.just(ABSENT_COUNTRY)),

                    countriesRepository
                            .getCountries()
                            .toList(),

                    profileRepository
                            .getUserName()
                            .switchIfEmpty(Single.just("")),

                    Function3({ country, countries, name ->
                        ProfileViewModel(name, country, countries)
                    })
            )
}