package net.darksky.example.util

object Converter {

    @JvmStatic
    fun f2c(f: Double?): String {
        if (f == null) return "0"

        return Math.round((f - 32) * 5 / 9).toString()
    }

    fun windDirection(windBearing: Int?): String {
        if (windBearing != null) {
            val i = Math.floor((windBearing / 11.25) + 0.5)
            val arr = arrayOf("n","nbe","nne","nebn","ne","nebe","ene","ebn",
                    "e","ens","ese","sebe","se","sebs","sse","sbe",
                    "s","sbw","ssw","swbs","sw","swbw","wsw","wbs",
                    "w","wbn","wnw","nwbw","nw","nwbn","nnw","nbw")
            return arr[Math.round(i).toInt()]
        }
        return ""
    }

    @JvmStatic
    fun round(f: Double?): String {
        if (f == null) return "0"

        return Math.round(f).toString()
    }


}