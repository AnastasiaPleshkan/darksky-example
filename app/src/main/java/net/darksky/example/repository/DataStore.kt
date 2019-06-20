package net.darksky.example.repository

import io.reactivex.Observable
import net.darksky.example.vo.Forecast


interface DataStore {
    fun get(lat:Double, lon:Double): Observable<Forecast>
}
