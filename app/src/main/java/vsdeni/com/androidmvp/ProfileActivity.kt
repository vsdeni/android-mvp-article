package vsdeni.com.androidmvp

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_profile.*


class ProfileActivity : AppCompatActivity(), ProfileView {
    private lateinit var presenter: ProfilePresenter
    private lateinit var adapter: CountriesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        initToolbar()

        adapter = CountriesAdapter()
        user_country.adapter = adapter

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

    override fun showCountry(country: Country?) {
        user_country.tag = country//save initial value
        presenter.loadCountries()
    }

    override fun showCountries(countries: Collection<Country>) {
        adapter.data = countries.toList()
        adapter.notifyDataSetChanged()
        selectCountry()
    }

    private fun selectCountry() {
        user_country.tag?.run { this as Country }?.let { country ->
            adapter.data?.let { user_country.setSelection(it.getPositionById(country.id)) }
        }
    }

    override fun name(): String =
            user_name.text.toString()


    override fun country(): Country =
            user_country.selectedItem as Country

}

private fun List<Country>.getPositionById(id: Long): Int {
    return this.indexOf(this.firstOrNull { it.id == id })
}