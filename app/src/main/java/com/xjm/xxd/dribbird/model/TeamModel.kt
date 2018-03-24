package com.xjm.xxd.dribbird.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

/**
 * Created by queda on 2018/3/19.
 */

class TeamModel() : Parcelable {

    @SerializedName("id")
    var id: Long = 0

    @SerializedName("name")
    var name: String = ""

    @SerializedName("login")
    var login: String = ""

    @SerializedName("html_url")
    var htmlUrl: String = ""

    @SerializedName("avatar_url")
    var avatarUrl: String = ""

    @SerializedName("bio")
    var bio: String = ""

    @SerializedName("location")
    var location: String = ""

    @SerializedName("links")
    var groupLinks: LinksModel? = null

    @SerializedName("type")
    var type: String = ""

    @SerializedName("created_at")
    var createTime: String = ""

    @SerializedName("updated_at")
    var updateTime: String = ""

    constructor(parcel: Parcel) : this() {
        id = parcel.readLong()
        name = parcel.readString()
        login = parcel.readString()
        htmlUrl = parcel.readString()
        avatarUrl = parcel.readString()
        bio = parcel.readString()
        location = parcel.readString()
        groupLinks = parcel.readParcelable(LinksModel::class.java.classLoader)
        type = parcel.readString()
        createTime = parcel.readString()
        updateTime = parcel.readString()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeLong(id)
        parcel.writeString(name)
        parcel.writeString(login)
        parcel.writeString(htmlUrl)
        parcel.writeString(avatarUrl)
        parcel.writeString(bio)
        parcel.writeString(location)
        parcel.writeParcelable(groupLinks, flags)
        parcel.writeString(type)
        parcel.writeString(createTime)
        parcel.writeString(updateTime)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<TeamModel> {
        override fun createFromParcel(parcel: Parcel): TeamModel {
            return TeamModel(parcel)
        }

        override fun newArray(size: Int): Array<TeamModel?> {
            return arrayOfNulls(size)
        }
    }

}