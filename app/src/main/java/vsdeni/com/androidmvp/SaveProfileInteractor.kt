package vsdeni.com.androidmvp

import io.reactivex.Completable


class SaveProfileInteractor(private val profileRepository: ProfileRepository) {

    fun execute(name: String, country: Country?): Completable =
            country?.let {
                profileRepository
                        .saveUserCountry(country)
            } ?: Completable.complete()
                    .andThen(profileRepository.saveUserName(name))
}