package net.darksky.example.repository

import io.reactivex.Observable
import net.darksky.example.AppExecutors
import net.darksky.example.api.ApiService
import net.darksky.example.vo.Forecast
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DataRepository @Inject constructor(private val restApi: ApiService,
                                         private val appExecutors: AppExecutors
        /*,
                                         private val dbHelper: DBHelper*/) {
    fun get(lat: Double, lon: Double): Observable<Forecast> {
        return CloudDataStore(restApi/*, dbHelper*/).get(lat, lon)/*.map {
            UserEntityMapper().transform(it)
        }*/
    }
}