package vsdeni.com.androidmvp

import io.reactivex.Single
import io.reactivex.functions.BiFunction


class GetProfileInteractor(private val profileRepository: ProfileRepository) {

    fun execute(): Single<ProfileViewModel> =
            profileRepository
                    .getUserCountry()
                    .switchIfEmpty(Single.just(ABSENT_COUNTRY))
                    .zipWith(profileRepository
                            .getUserName()
                            .switchIfEmpty(Single.just("")),
                            BiFunction<Country, String, ProfileViewModel>(
                                    { country, name ->
                                        ProfileViewModel(name, country)
                                    }
                            ))
}