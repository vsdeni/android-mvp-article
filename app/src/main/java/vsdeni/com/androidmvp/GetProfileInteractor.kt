package vsdeni.com.androidmvp

import io.reactivex.Single
import io.reactivex.functions.BiFunction
import io.reactivex.functions.Function3


class GetProfileInteractor(private val profileRepository: ProfileRepository) {

    fun execute(): Single<ProfileViewModel> =
            Single.zip(
                    profileRepository
                            .getUserCountry()
                            .switchIfEmpty(Single.just(ABSENT_COUNTRY)),

                    profileRepository
                            .getUserName()
                            .switchIfEmpty(Single.just("")),

                    BiFunction({ country, name ->
                        ProfileViewModel(name, country)
                    })
            )
}