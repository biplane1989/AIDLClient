package com.example.aidlserver

import android.os.Parcel
import android.os.Parcelable

class Candy(val name: String?) : Parcelable {
    constructor(parcel: Parcel) : this(parcel.readString()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Candy> {
        override fun createFromParcel(parcel: Parcel): Candy {
            return Candy(parcel)
        }

        override fun newArray(size: Int): Array<Candy?> {
            return arrayOfNulls(size)
        }
    }
}