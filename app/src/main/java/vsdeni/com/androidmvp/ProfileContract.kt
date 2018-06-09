package vsdeni.com.androidmvp

interface ProfileView {
    fun showName(name: String)

    fun showCountry(country: Country?, availableCountries: Collection<Country>)

    fun getName(): String

    fun getCountry(): Country?
}

interface ProfilePresenter {
    fun attachView(profileView: ProfileView)

    fun detachView(profileView: ProfileView)

    fun loadProfile()

    fun onSaveClick()
}