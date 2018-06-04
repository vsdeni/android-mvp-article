package vsdeni.com.androidmvp

import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import com.google.gson.GsonBuilder
import io.reactivex.Single
import junit.framework.Assert
import org.junit.BeforeClass
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see [Testing documentation](http://d.android.com/tools/testing)
 */
@RunWith(AndroidJUnit4::class)
class CountriesDataSourceTest {

    companion object {
        private lateinit var list: List<Country>
        @BeforeClass
        @JvmStatic
        fun setUp() {
            val appContext = InstrumentationRegistry.getTargetContext()
            list = CountriesDataSource(appContext, GsonBuilder().setLenient().create())
                    .getCountries()
                    .toList()
                    .blockingGet()
        }
    }

    @Test
    @Throws(Exception::class)
    fun countriesList_correctCount() {
        Assert.assertEquals(250, list.size)
    }

    @Test
    @Throws(Exception::class)
    fun countriesGetFirstItem_correctFields() {
        val country = list[0]
        Assert.assertEquals("1", country.id)
        Assert.assertEquals("Afghanistan", country.name)
        Assert.assertEquals("AF-32.png", country.flag_32)
        Assert.assertEquals("AF-128.png", country.flag_128)
    }
}
