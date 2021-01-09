package org.shaun.itunessearch.modelclass

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


data class ITunesItem(
    @SerializedName("wrapperType")
    @Expose
    var wrapperType: String?,

    @SerializedName("artistName")
    @Expose
    var artistName: String?,

    @SerializedName("collectionName")
    @Expose
    var collectionName: String?,

    @SerializedName("artworkUrl100")
    @Expose
    var artworkUrl100: String?
){

}

