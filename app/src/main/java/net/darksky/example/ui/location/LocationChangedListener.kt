package net.darksky.example.ui.location

import android.location.Location
import android.location.LocationListener
import android.os.Bundle

import timber.log.Timber

open class LocationChangedListener : LocationListener {
    override fun onStatusChanged(provider: String, status: Int, extras: Bundle) {
        Timber.d("Status changed %s", status)
    }

    override fun onProviderEnabled(provider: String) {
        Timber.d("Provider enabled %s", provider)
    }

    override fun onProviderDisabled(provider: String) {
        Timber.d("Provider disabled %s", provider)
    }

    override fun onLocationChanged(location: Location) {
        Timber.d("Location changed %s", location)
    }
}
