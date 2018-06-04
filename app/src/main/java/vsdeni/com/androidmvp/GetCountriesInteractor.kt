package vsdeni.com.androidmvp

import io.reactivex.Single

class GetCountriesInteractor(private val countriesRepository: CountriesRepository) {

    fun execute(): Single<List<Country>> =
            countriesRepository
                    .getCountries()
                    .toList()
}