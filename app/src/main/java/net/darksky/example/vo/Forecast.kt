package net.darksky.example.vo

class Forecast {
    var latitude: Double? = null
    var longitude: Double? = null
    var timezone: String? = null
    var currently:DataDetail? = null
    var hourly:Detail? = null
    var daily:Detail? = null
    override fun toString(): String {
        return "Forecast(latitude=$latitude, longitude=$longitude, timezone=$timezone, currently=$currently, hourly=$hourly, daily=$daily)"
    }
}
