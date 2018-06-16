package vsdeni.com.androidmvp

interface ProfileView {
    fun showName(name: String)

    fun showCountry(country: Country?)

    fun showCountriesPicker(countries: Collection<Country>)

    fun name(): String

    fun country(): Country?
}

interface ProfilePresenter {
    fun attachView(profileView: ProfileView)

    fun detachView()

    fun loadCountries()

    fun loadProfile()

    fun saveProfile()
}