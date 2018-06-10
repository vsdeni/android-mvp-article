package vsdeni.com.androidmvp

data class ProfileViewModel(val name: String, val country: Country?)

class ProfilePresenterImpl(private val getProfileInteractor: GetProfileInteractor,
                           private val saveProfileInteractor: SaveProfileInteractor,
                           private val getCountriesInteractor: GetCountriesInteractor,
                           private val schedulers: Schedulers = object : Schedulers {}) : ProfilePresenter {

    private var view: ProfileView? = null

    override fun attachView(profileView: ProfileView) {
        this.view = profileView
    }

    override fun detachView(profileView: ProfileView) {
        this.view = null
    }

    override fun loadProfile() {
        loadProfile(onLoaded = { profile ->
            view?.showName(profile.name)
            view?.showCountry(profile.country)
        })
    }

    override fun loadCountries() {
        getCountriesInteractor.execute()
                .subscribeOn(schedulers.background())
                .observeOn(schedulers.ui())
                .subscribe({
                    view?.showCountries(it)
                }, { error -> error.printStackTrace() })
    }

    private fun loadProfile(onLoaded: (ProfileViewModel) -> Unit) {
        getProfileInteractor.execute()
                .subscribeOn(schedulers.background())
                .observeOn(schedulers.ui())
                .subscribe({
                    onLoaded.invoke(it)
                }, { error -> error.printStackTrace() })
    }

    override fun saveProfile() {
        saveProfile(onSaved = {})
    }

    private fun saveProfile(onSaved: () -> Unit) {
        saveProfileInteractor.execute(view!!.name(), view!!.country())
                .subscribeOn(schedulers.background())
                .observeOn(schedulers.ui())
                .subscribe({
                    onSaved.invoke()
                }, { error -> error.printStackTrace() })
    }
}

