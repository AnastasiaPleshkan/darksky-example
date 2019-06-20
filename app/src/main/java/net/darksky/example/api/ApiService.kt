package net.darksky.example.api

import io.reactivex.Observable
import net.darksky.example.vo.Forecast
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * REST API access points
 */
interface ApiService {

    @GET("{lat},{lon}")
    fun get(@Path("lat") lat: Double, @Path("lon") lon: Double): Observable<Forecast>

}
