package net.darksky.example.util

object Converter {

    @JvmStatic
    fun f2c(f: Double?): String {
        if (f == null) return "0"

        return Math.round((f - 32) * 5 / 9).toString()
    }

    @JvmStatic
    fun round(f: Double?): String {
        if (f == null) return "0"

        return Math.round(f).toString()
    }


}