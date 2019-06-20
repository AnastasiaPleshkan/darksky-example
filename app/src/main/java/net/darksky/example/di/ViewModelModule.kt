package net.darksky.example.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

import net.darksky.example.viewmodel.DarkskyViewModelFactory

import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import net.darksky.example.ui.forecast.ForecastViewModel

@Suppress("unused")
@Module
abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(ForecastViewModel::class)
    abstract fun bindForecastViewModel(forecastViewModel: ForecastViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: DarkskyViewModelFactory): ViewModelProvider.Factory
}
