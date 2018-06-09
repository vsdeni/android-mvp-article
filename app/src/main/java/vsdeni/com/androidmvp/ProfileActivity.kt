package vsdeni.com.androidmvp

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_profile.*


class ProfileActivity : AppCompatActivity(), ProfileView {
    private lateinit var presenter: ProfilePresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        initToolbar()

        presenter = Injector(applicationContext).provideProfilePresenter()
        presenter.attachView(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detachView(this)
    }

    private fun initToolbar() {
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = getString(R.string.toolbar_title_profile)
    }

    override fun onResume() {
        super.onResume()
        presenter.loadProfile()
    }

    override fun showName(name: String) {
        user_name.setText(name)
    }

    override fun showCountry(country: Country?, availableCountries: Collection<Country>) {
        val adapter = ArrayAdapter<Country>(this,
                android.R.layout.simple_spinner_item,
                availableCountries.toTypedArray())

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        user_country.adapter = adapter
        country?.let {
            user_country.setSelection(availableCountries.getPositionById(it.id))
        }
    }

    override fun getName(): String =
            user_name.text.toString()


    override fun getCountry(): Country =
            user_country.tag as Country

}

private fun Collection<Country>.getPositionById(id: Long): Int {
    return this.indexOf(this.firstOrNull { it.id == id })
}