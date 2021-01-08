package org.shaun.itunessearch.modelclass

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName


class ResultModel {

    @SerializedName("resultCount")
    @Expose
    var resultCount:Int=0

    @SerializedName("results")
    @Expose
    var results:List<iTunesItem>?=null


}