package vsdeni.com.androidmvp


import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ListView

class CountryPickerDialog : DialogFragment() {
    companion object {
        fun newInstance(title: String,
                        items: Collection<Country>,
                        country: Country): CountryPickerDialog {

            val dialog = CountryPickerDialog()
            val args = Bundle()
            args.putString("title", title)
            args.putParcelableArrayList("countries", ArrayList(items))
            args.putParcelable("active", country)
            dialog.arguments = args
            return dialog
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view: ListView = inflater.inflate(R.layout.dialog_countries, container, false) as ListView
        val adapter = CountriesAdapter()
        val items: ArrayList<Country> = arguments!!.getParcelableArrayList<Country>("countries")
        val active: Country? = arguments?.getParcelable("active")
        adapter.set(items, active)

        view.onItemClickListener = AdapterView.OnItemClickListener { _, _, position, _ ->
            if (activity is OnCountrySelectCallback) {
                (activity as OnCountrySelectCallback).onCountrySelected(items[position])
            }
            dismiss()
        }
        view.adapter = adapter
        return view
    }
}


