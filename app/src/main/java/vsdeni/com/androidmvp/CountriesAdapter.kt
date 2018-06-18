package vsdeni.com.androidmvp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide


class CountriesAdapter : BaseAdapter() {
    var data: List<Country>? = null
    var active: Country? = null

    fun set(countries: List<Country>, active: Country?) {
        this.data = countries
        this.active = active
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View? {
        val country: Country? = getItem(position)
        var view: View? = convertView
        val holder: ViewHolder
        if (view != null) {
            holder = view.tag as ViewHolder
        } else {
            view = LayoutInflater.from(parent?.context).inflate(R.layout.item_country, parent, false)
            holder = ViewHolder(view.findViewById(R.id.country_flag), view.findViewById(R.id.country_title), view.findViewById(R.id.country_active))
            view.tag = holder
        }
        holder.title.text = country?.name
        holder.active?.isChecked = country?.id == active?.id ?: -1
        Glide.with(view!!)
                .load(country?.flag_128?.asDrawable(view.context))
                .into(holder.icon)
        return view
    }

    override fun getItem(position: Int): Country? =
            data?.get(position)

    override fun getItemId(position: Int): Long =
            getItem(position)?.id ?: 0

    override fun getCount(): Int =
            data?.size ?: 0
}

data class ViewHolder(val icon: ImageView, val title: TextView, val active: CheckBox?)