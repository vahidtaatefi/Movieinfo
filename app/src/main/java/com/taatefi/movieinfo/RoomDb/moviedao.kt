package com.taatefi.movieinfo.RoomDb

import androidx.room.*
import io.reactivex.Flowable

@Dao
interface moviedao {
    @Query("SELECT * FROM movieinf")
    fun getAll(): List<Movieinf?>?

    @Query("SELECT * FROM movieinf")
    fun getAll2(): Flowable<List<Movieinf>>



    @Query("SELECT * from movieinf where tittle Like :tittle")
    fun findbytitle(tittle: String): Movieinf


    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insetdata(vararg moviedt:Movieinf)
    @Delete
    fun delete(movieinf:Movieinf)

    @Query("DELETE FROM Movieinf")
    fun deleteAll()

}