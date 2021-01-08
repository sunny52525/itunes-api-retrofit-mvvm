package org.shaun.itunessearch.modelclass

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName


class iTunesItem {
    @SerializedName("wrapperType")
    @Expose
    var wrapperType :String=""

    @SerializedName("artistName")
    @Expose
    var artistName =""

    @SerializedName("collectionName")
    @Expose
    var collectionName=""

    @SerializedName("artistViewUrl")
    @Expose
    var artistViewUrl=""

    @SerializedName("artworkUrl100")
    @Expose
    var artworkUrl100=""


    override fun toString(): String {
        return """
            $wrapperType
            $artistName
            $collectionName
            $artistViewUrl
            $artworkUrl100
        """.trimIndent()
    }

}