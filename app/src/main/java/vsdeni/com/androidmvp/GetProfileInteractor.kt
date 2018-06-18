package vsdeni.com.androidmvp

import io.reactivex.Single
import io.reactivex.functions.BiFunction


class GetProfileInteractor(private val profileRepository: ProfileRepository) {

    fun execute(): Single<ProfileViewModel> =
            Single.zip(
                    profileRepository
                            .getUserCountry()
                            .switchIfEmpty(Single.just(EMPTY_COUNTRY)),

                    profileRepository
                            .getUserName()
                            .switchIfEmpty(Single.just("")),

                    BiFunction({ country, name ->
                        ProfileViewModel(name, country)
                    })
            )
}