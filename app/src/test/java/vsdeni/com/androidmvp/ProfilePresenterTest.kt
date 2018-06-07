package vsdeni.com.androidmvp

import io.reactivex.Observable
import io.reactivex.android.plugins.RxAndroidPlugins
import org.junit.Before
import org.junit.BeforeClass
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner


/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see [Testing documentation](http://d.android.com/tools/testing)
 */
@RunWith(MockitoJUnitRunner::class)
class ProfilePresenterTest {

    @Mock
    private lateinit var profileView: ProfileView

    @Mock
    private lateinit var countriesRepository: CountriesRepository

    @Mock
    private lateinit var getProfileInteractor: GetProfileInteractor

    @Mock
    private lateinit var saveProfileInteractor: SaveProfileInteractor

    private lateinit var profilePresenter: ProfilePresenter

    companion object {
        @BeforeClass
        @JvmStatic
        fun setup() {
            RxAndroidPlugins.setInitMainThreadSchedulerHandler {
                io.reactivex.schedulers.Schedulers.trampoline()
            }
        }
    }

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)

        `when`(countriesRepository.getCountries()).thenReturn(Observable.just(ABSENT_COUNTRY))

        profilePresenter = ProfilePresenterImpl(view = profileView,
                getProfileInteractor = getProfileInteractor,
                saveProfileInteractor = saveProfileInteractor,
                getCountriesInteractor = GetCountriesInteractor(countriesRepository))
    }

    @Test
    fun clickOnCountry_ShowCountriesPopup() {
        profilePresenter.onCountryClick()
        verify(profileView).showCountriesPopup(ArgumentMatchers.anyCollection())
    }
}