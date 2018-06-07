package vsdeni.com.androidmvp


class ProfilePresenterImpl(private val view: ProfileView,
                           private val getProfileInteractor: GetProfileInteractor,
                           private val saveProfileInteractor: SaveProfileInteractor,
                           private val schedulers: Schedulers = object : Schedulers {}) : ProfilePresenter {

    override fun loadProfile() {
        loadProfileData(onLoaded = { user ->
            view.showName(user.name)
            view.showCountry(user.country, user.countries)
        })
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
        saveProfile(onSaved = {})
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

