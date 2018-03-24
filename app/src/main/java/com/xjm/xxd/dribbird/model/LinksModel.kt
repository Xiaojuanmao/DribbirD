package com.xjm.xxd.dribbird.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

/**
 * Created by queda on 2018/3/18.
 */

class LinksModel() : Parcelable {

    @SerializedName("web")
    var webLink: String = ""

    @SerializedName("twitter")
    var twitterLink: String = ""

    constructor(parcel: Parcel) : this() {
        webLink = parcel.readString()
        twitterLink = parcel.readString()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(webLink)
        parcel.writeString(twitterLink)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<LinksModel> {
        override fun createFromParcel(parcel: Parcel): LinksModel {
            return LinksModel(parcel)
        }

        override fun newArray(size: Int): Array<LinksModel?> {
            return arrayOfNulls(size)
        }
    }

}