package net.darksky.example.vo

class Detail {
    var summary: String? = null
    var icon: String? = null

    var data: List<DataDetail>? = null

    override fun toString(): String {
        return "Detail(summary=$summary, icon=$icon, data=$data)"
    }
}
