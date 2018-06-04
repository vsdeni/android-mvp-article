package vsdeni.com.androidmvp

    interface ProfileView {
        fun showName(name: String)

        fun showCountry(country: Country?)

        fun showCountriesPopup(countries: Collection<Country>)

        fun getName(): String

        fun getCountry(): Country?
    }

    interface ProfilePresenter {
        fun onStart()

        fun onStop()

        fun onCountryClick()

        fun onSaveClick()
    }





