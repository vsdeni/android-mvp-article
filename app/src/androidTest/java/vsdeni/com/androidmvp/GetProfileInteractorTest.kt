package vsdeni.com.androidmvp

import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import com.google.gson.GsonBuilder
import junit.framework.Assert
import org.junit.BeforeClass
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class GetProfileInteractorTest {
    companion object {
        private lateinit var profileData: ProfileViewModel
        @BeforeClass
        @JvmStatic
        fun setUp() {
            val appContext = InstrumentationRegistry.getTargetContext()
            profileData = GetProfileInteractor(ProfileDataSource(), CountriesDataSource(appContext, GsonBuilder().create()))
                    .execute()
                    .blockingGet()
        }
    }

    @Test
    @Throws(Exception::class)
    fun profileName_correct() {
        Assert.assertEquals("Denys Vasylenko", profileData.name)
    }

    @Test
    @Throws(Exception::class)
    fun profileCountry_correct() {
        Assert.assertEquals("172", profileData.userCountry.id)
    }

    @Test
    @Throws(Exception::class)
    fun availableCountries_correct() {
        Assert.assertEquals(250, profileData.availableCountries?.size)
    }
}