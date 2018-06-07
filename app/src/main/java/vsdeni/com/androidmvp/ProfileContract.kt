package vsdeni.com.androidmvp

interface ProfileView {
    fun showName(name: String)

    fun showCountry(country: Country?, availableCountries: Collection<Country>)

    fun getName(): String

    fun getCountry(): Country?
}

interface ProfilePresenter {
    fun loadProfile()

    fun onSaveClick()
}