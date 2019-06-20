package net.darksky.example.usecases

import io.reactivex.Observable
import net.darksky.example.AppExecutors
import net.darksky.example.repository.DataRepository
import net.darksky.example.vo.Forecast
import javax.inject.Inject

class GetForecastUseCase @Inject
constructor(appExecutors: AppExecutors,
            private val repository: DataRepository) : UseCase<Forecast, GetForecastUseCase.Params>(appExecutors) {

    override fun buildUseCaseObservable(params: Params): Observable<Forecast> {
        return repository.get(params.lat, params.lon)
    }

    class Params private constructor(val lat: Double, val lon: Double) {
        companion object {
            fun create(lat: Double, lon: Double): Params {
                return Params(lat, lon)
            }
        }
    }
}