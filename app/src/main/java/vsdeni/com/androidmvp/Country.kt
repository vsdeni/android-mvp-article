package vsdeni.com.androidmvp

import android.os.Parcel
import android.os.Parcelable


data class Country(val id: Long, val name: String, val flag_32: String, val flag_128: String) : Parcelable {
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

val ABSENT_COUNTRY = Country(-1, "", "", "")