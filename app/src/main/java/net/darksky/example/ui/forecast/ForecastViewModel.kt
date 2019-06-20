package net.darksky.example.ui.forecast

import androidx.lifecycle.*
import net.darksky.example.usecases.DefaultObserver
import net.darksky.example.usecases.GetForecastUseCase
import javax.inject.Inject
import net.darksky.example.vo.Forecast as Forecast1

class ForecastViewModel
@Inject constructor(private val searchUseCase: GetForecastUseCase) : ViewModel() {

    fun load(lat: Double, lon: Double, observer: DefaultObserver<net.darksky.example.vo.Forecast>) {
        searchUseCase.execute(observer, GetForecastUseCase.Params.create(lat, lon))
    }

}
