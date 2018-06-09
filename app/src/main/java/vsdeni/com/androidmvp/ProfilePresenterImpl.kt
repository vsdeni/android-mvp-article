package vsdeni.com.androidmvp

data class ProfileViewModel(val name: String, val country: Country?, val availableCountries: Collection<Country>)

class ProfilePresenterImpl(private val getProfileInteractor: GetProfileInteractor,
                           private val saveProfileInteractor: SaveProfileInteractor,
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
            view?.showCountry(profile.country, profile.availableCountries)
        })
    }

    private fun loadProfile(onLoaded: (ProfileViewModel) -> Unit) {
        getProfileInteractor.execute()
                .subscribeOn(schedulers.background())
                .observeOn(schedulers.ui())
                .subscribe({
                    onLoaded.invoke(it)
                }, { error -> error.printStackTrace() })
    }

    override fun onSaveClick() {
        saveProfile(onSaved = {})
    }

    private fun saveProfile(onSaved: () -> Unit) {
        saveProfileInteractor.execute(view!!.getName(), view!!.getCountry())
                .observeOn(schedulers.background())
                .observeOn(schedulers.ui())
                .subscribe({
                    onSaved.invoke()
                }, { error -> error.printStackTrace() })
    }
}

