package vsdeni.com.androidmvp


class ProfilePresenterImpl(private val view: ProfileView,
                           private val getProfileInteractor: GetProfileInteractor,
                           private val saveProfileInteractor: SaveProfileInteractor,
                           private val getCountriesInteractor: GetCountriesInteractor,
                           private val schedulers: Schedulers = object : Schedulers {}) : ProfilePresenter {

    override fun onStart() {
        loadProfileData({ user ->
            view.showName(user.name)
            view.showCountry(user.country)
        })
    }

    override fun onStop() {
        //NO-OP
    }

    override fun onCountryClick() {
        loadCountries({ countries ->
            view.showCountriesPopup(countries)
        })
    }

    private fun loadCountries(onLoaded: (Collection<Country>) -> Unit) {
        getCountriesInteractor.execute()
                .subscribeOn(schedulers.background())
                .observeOn(schedulers.ui())
                .subscribe({
                    onLoaded.invoke(it)
                }, { error -> error.printStackTrace() })
    }

    private fun loadProfileData(onLoaded: (ProfileViewModel) -> Unit) {
        getProfileInteractor.execute()
                .subscribeOn(schedulers.background())
                .observeOn(schedulers.ui())
                .subscribe({
                    onLoaded.invoke(it)
                }, { error -> error.printStackTrace() })
    }

    override fun onSaveClick() {
        saveProfile({})
    }

    private fun saveProfile(onSaved: () -> Unit) {
        saveProfileInteractor.execute(view.getName(), view.getCountry())
                .observeOn(schedulers.background())
                .observeOn(schedulers.ui())
                .subscribe({
                    onSaved.invoke()
                }, { error -> error.printStackTrace() })
    }
}

