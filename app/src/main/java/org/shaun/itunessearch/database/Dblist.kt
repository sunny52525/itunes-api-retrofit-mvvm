package org.shaun.itunessearch.database

import org.shaun.itunessearch.modelclass.ITunesItem

class Dblist(val list: List<ITunesItem>){

    override fun toString(): String {
        return """
            list ${list[0].artistName}
        """.trimIndent()
    }
}