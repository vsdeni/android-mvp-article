package vsdeni.com.androidmvp

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.ArrayAdapter
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.activity_profile.*


class ProfileActivity : AppCompatActivity(), ProfileView, OnCountrySelectCallback {
    private lateinit var presenter: ProfilePresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = getString(R.string.toolbar_title_profile)

        presenter = ProfilePresenterImpl(this,
                GetProfileInteractor(ProfileDataSource(), CountriesDataSource(this, GsonBuilder().create())),
                SaveProfileInteractor(ProfileDataSource()))
    }

    override fun onResume() {
        super.onResume()
        presenter.loadProfile()
    }

    override fun onCountrySelected(country: Country) {

    }

    override fun showName(name: String) {
        user_name.setText(name)
    }

    override fun showCountry(country: Country?, countries: Collection<Country>) {
        val adapter = ArrayAdapter<Country>(this,
                android.R.layout.simple_spinner_item,
                countries.toTypedArray())

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        user_country.adapter = adapter
        user_country.setSelection(countries.indexOf(countries.firstOrNull { it.id == country?.id }))
    }

    override fun getName(): String =
            user_name.text.toString()


    override fun getCountry(): Country =
            user_country.tag as Country

}

interface OnCountrySelectCallback {
    fun onCountrySelected(country: Country)
}