package com.taatefi.movieinfo.RoomDb

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.taatefi.movieinfo.Retrofit.themovieresponsemodel

@Entity
class Movieinf {
    @PrimaryKey(autoGenerate = true)
    var id = 0
        private set

    @ColumnInfo(name = "tittle")
    var titlename: String
        private set

    @ColumnInfo(name = "backdrop")
    var backdroppic: String

    constructor(
        id: Int, titlename: String,
        backdroppic: String

    ) {
        this.id = id
        this.titlename = titlename
        this.backdroppic = backdroppic
    }
}