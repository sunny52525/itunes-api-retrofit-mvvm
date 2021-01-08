package org.shaun.itunessearch.modelclass

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName


class ResultModel {
    @SerializedName("results")
    @Expose
    var results:List<ITunesItem>?=null


    private fun setresults(result:List<ITunesItem>){
        this.results=results
    }
}