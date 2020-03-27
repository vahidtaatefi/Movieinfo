package com.taatefi.movieinfo.Retrofit
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query
interface RetrofitInterfacethmd {
    //https://api.themoviedb.org/3/search/movie?api_key=29c980d915e3290456b6d3c897ff2ba4&language=en-US&query=jocker&page=1&include_adult=false
    @GET("movie")
    fun getmoviinfo(
        @Query("api_key") apikey: String,
        @Query("language") language: String,
        @Query("query") query: String
    ) : Observable<themovieresponsemodel>
}