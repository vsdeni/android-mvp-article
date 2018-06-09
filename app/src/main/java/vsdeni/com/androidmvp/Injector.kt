package vsdeni.com.androidmvp

import android.content.Context
import com.google.gson.GsonBuilder

class Injector(val context: Context) {

    fun provideProfilePresenter(): ProfilePresenter {
        return ProfilePresenterImpl(
                provideGetProfileInteractor(),
                provideSaveProfileInteractor())
    }

    fun provideGetProfileInteractor(): GetProfileInteractor {
        return GetProfileInteractor(provideProfileRepository(), provideCountriesRepository())
    }

    fun provideSaveProfileInteractor(): SaveProfileInteractor {
        return SaveProfileInteractor(provideProfileRepository())
    }

    fun provideCountriesRepository(): CountriesRepository {
        return CountriesDataSource(provideContext(), GsonBuilder().create())
    }

    fun provideProfileRepository(): ProfileRepository {
        return ProfileDataSource()
    }

    fun provideContext(): Context = context
}