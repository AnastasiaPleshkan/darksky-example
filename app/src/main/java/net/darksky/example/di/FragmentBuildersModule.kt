package net.darksky.example.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import net.darksky.example.ui.forecast.ForecastFragment
import net.darksky.example.ui.location.LocationFragment

@Suppress("unused")
@Module
abstract class FragmentBuildersModule {

    @ContributesAndroidInjector
    abstract fun forecastFragment(): ForecastFragment

    @ContributesAndroidInjector
    abstract fun locationFragment(): LocationFragment
}
