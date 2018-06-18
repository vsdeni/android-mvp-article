package vsdeni.com.androidmvp

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.popup.activity_profile.*

class ProfileActivity : AppCompatActivity(), ProfileView, OnCountrySelectCallback {
    private lateinit var presenter: ProfilePresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = getString(R.string.toolbar_title_profile)

        presenter = Injector(this).provideProfilePresenter()
        presenter.attachView(this)

        user_country.setOnClickListener({ presenter.loadCountries() })
    }

    override fun onResume() {
        super.onResume()
        presenter.loadProfile()
    }

    override fun onCountrySelected(country: Country) {
        showCountry(country)
    }

    override fun showName(name: String) {
        user_name.setText(name)
    }

    override fun showCountry(country: Country?) {
        user_country.text = country?.name
        user_country.tag = country
        country?.flag_128?.asDrawable(this.applicationContext)
                ?.let {
                    user_country.setCompoundDrawablesWithIntrinsicBounds(it, null, null, null)
                }
    }

    override fun showCountriesPicker(countries: Collection<Country>) {
        val dialog = CountryPickerDialog.newInstance("Countries", countries, country())
        dialog.show(supportFragmentManager, "countries")
    }

    override fun name(): String =
            user_name.text.toString()


    override fun country(): Country =
            user_country.tag as Country
}

interface OnCountrySelectCallback {
    fun onCountrySelected(country: Country)
}