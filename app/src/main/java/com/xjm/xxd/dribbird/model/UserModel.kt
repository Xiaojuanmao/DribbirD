package com.xjm.xxd.dribbird.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

/**
 * Created by queda on 2016/12/4.
 */

class UserModel() : Parcelable {

    @SerializedName("id")
    var id: Long = 0

    @SerializedName("name")
    var name: String = ""

    @SerializedName("login")
    var login: String = ""

    @SerializedName("html_url")
    var htmlUrl: String = ""

    @SerializedName("avatar_url")
    var avatar: String = ""

    @SerializedName("bio")
    var description: String = ""

    @SerializedName("location")
    var location: String = ""

    @SerializedName("links")
    var links: LinksModel? = null

    @SerializedName("can_upload_shot")
    var canUploadShot: Boolean = false

    @SerializedName("pro")
    var pro: Boolean = false

    @SerializedName("created_at")
    var createTime: String = ""

    @SerializedName("type")
    var type: String = ""

    @SerializedName("teams")
    var teamModelList: List<TeamModel>? = null

    constructor(parcel: Parcel) : this() {
        id = parcel.readLong()
        name = parcel.readString()
        login = parcel.readString()
        htmlUrl = parcel.readString()
        avatar = parcel.readString()
        description = parcel.readString()
        location = parcel.readString()
        links = parcel.readParcelable(LinksModel::class.java.classLoader)
        canUploadShot = parcel.readByte() != 0.toByte()
        pro = parcel.readByte() != 0.toByte()
        createTime = parcel.readString()
        type = parcel.readString()
        teamModelList = parcel.createTypedArrayList(TeamModel)
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeLong(id)
        parcel.writeString(name)
        parcel.writeString(login)
        parcel.writeString(htmlUrl)
        parcel.writeString(avatar)
        parcel.writeString(description)
        parcel.writeString(location)
        parcel.writeParcelable(links, flags)
        parcel.writeByte(if (canUploadShot) 1 else 0)
        parcel.writeByte(if (pro) 1 else 0)
        parcel.writeString(createTime)
        parcel.writeString(type)
        parcel.writeTypedList(teamModelList)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<UserModel> {
        override fun createFromParcel(parcel: Parcel): UserModel {
            return UserModel(parcel)
        }

        override fun newArray(size: Int): Array<UserModel?> {
            return arrayOfNulls(size)
        }
    }
}
