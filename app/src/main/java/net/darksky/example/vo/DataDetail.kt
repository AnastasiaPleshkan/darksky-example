package net.darksky.example.vo

class DataDetail {
    var time: Long? = null
    var summary: String? = null
    var icon: String? = null
    var temperature: Double? = null
    var temperatureHigh: Double? = null
    var temperatureLow: Double? = null
    var precipProbability: Double? = null
    var humidity: Double? = null
    var pressure: Double? = null
    var windSpeed: Double? = null
    var windBearing: Int? = null
    override fun toString(): String {
        return "DataDetail(time=$time, summary=$summary, icon=$icon, temperature=$temperature, temperatureHigh=$temperatureHigh, temperatureLow=$temperatureLow, precipProbability=$precipProbability, humidity=$humidity, pressure=$pressure, windSpeed=$windSpeed, windBearing=$windBearing)"
    }

    fun temp(): Double? {
        return if (temperature != null) return temperature else ((temperatureHigh!! + temperatureLow!!) / 2)
    }
}
