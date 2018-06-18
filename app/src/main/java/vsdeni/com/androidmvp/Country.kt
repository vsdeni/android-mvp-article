package vsdeni.com.androidmvp

import android.content.Context
import android.graphics.drawable.Drawable
import android.os.Parcel
import android.os.Parcelable
import java.io.IOException


data class Country(val id: Long, val name: String, val flag_32: FlagIcon, val flag_128: FlagIcon) : Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readLong(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString())

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeLong(id)
        parcel.writeString(name)
        parcel.writeString(flag_32)
        parcel.writeString(flag_128)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Country> {
        override fun createFromParcel(parcel: Parcel): Country {
            return Country(parcel)
        }

        override fun newArray(size: Int): Array<Country?> {
            return arrayOfNulls(size)
        }
    }

    override fun toString(): String {
        return name
    }
}

typealias FlagIcon = String

fun FlagIcon.asDrawable(context: Context): Drawable? =
        try {
            Drawable.createFromStream(context.assets.open("flags/"+this), this)
        } catch (e: IOException) {
            null
        }


val EMPTY_COUNTRY = Country(-1, "", "", "")