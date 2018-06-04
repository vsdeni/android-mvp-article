package vsdeni.com.androidmvp

import android.content.Context
import com.google.gson.Gson
import io.reactivex.Observable
import java.io.BufferedReader
import java.io.InputStreamReader


class CountriesDataSource(private val context: Context, private val gson: Gson) : CountriesRepository {

    override fun getCountries(): Observable<Country> =
            Observable.create({ emitter ->
                val json = getJsonFromAssets()
                val countries = fromJsonToObjects(json)
                for (country in countries) {
                    emitter.onNext(country)
                }
                emitter.onComplete()
            })

    private fun getJsonFromAssets(): String {
        var reader: BufferedReader? = null
        val json = StringBuffer()
        try {
            reader = BufferedReader(InputStreamReader(context.assets.open("countries.json"), "UTF-8"))
            do {
                val line = reader.readLine()
                line?.let { json.append(line) }
            } while (line != null)

        } finally {
            reader?.close()
        }
        return json.toString()
    }

    private fun fromJsonToObjects(json: String): Array<Country> {
        return gson.fromJson(json, Array<Country>::class.java)
    }
}