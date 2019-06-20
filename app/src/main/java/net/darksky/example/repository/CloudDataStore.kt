package net.darksky.example.repository

import io.reactivex.Observable
import net.darksky.example.api.ApiService
import net.darksky.example.vo.Forecast


class CloudDataStore(private val restApi: ApiService/*, private var dbHelper: DBHelper*/) : DataStore {
    override fun get(lat: Double, lon:Double): Observable<Forecast> {
        return restApi.get(lat, lon)/*.doOnNext { dbHelper.save(it) }*/
    }

}