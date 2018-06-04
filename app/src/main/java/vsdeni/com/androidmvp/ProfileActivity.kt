package vsdeni.com.androidmvp

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
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
                GetProfileInteractor(ProfileDataSource()),
                SaveProfileInteractor(ProfileDataSource()),
                GetCountriesInteractor(CountriesDataSource(this, GsonBuilder().create())),
                object : Schedulers {})

        user_country.setOnClickListener({ presenter.onCountryClick() })
    }

    override fun onResume() {
        super.onResume()
        presenter.onStart()
    }

    override fun onCountrySelected(country: Country) {

    }

    override fun showName(name: String) {
        user_name.setText(name)
    }

    override fun showCountry(country: Country?) {
        user_country.text = country?.name
        user_country.tag = country
    }

    override fun showCountriesPopup(countries: Collection<Country>) {
        val dialog = CountryPickerDialog.newInstance("Countries", countries, getCountry())
        dialog.show(supportFragmentManager, "countries")
    }

    override fun getName(): String =
            user_name.text.toString()


    override fun getCountry(): Country =
            user_country.tag as Country

}

interface OnCountrySelectCallback {
    fun onCountrySelected(country: Country)
}