package net.darksky.example.binding

import androidx.fragment.app.Fragment
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import javax.inject.Inject
import android.widget.TextView
import net.darksky.example.R
import net.darksky.example.util.Converter


/**
 * Binding adapters that work with a fragment instance.
 */
class FragmentBindingAdapters @Inject constructor(val fragment: Fragment) {

    @BindingAdapter(value = ["imageIcon"], requireAll = false)
    fun bindImage(imageView: ImageView, icon: String?) {
        try {
            val resID = fragment.requireContext().resources.getIdentifier(icon?.replace("-", "_"), "drawable",
                    fragment.requireContext().getPackageName())
            if (resID > -1)
                imageView.setImageResource(resID)
        } catch (ignored: Exception) {
        }
    }

    @BindingAdapter("stringResId", "windBearing", "windSpeed")
    fun setConvertDirection(textView: TextView, stringResId: Int, windBearing: Int?, windSpeed: Double?) {
        try {
            val directionResID = fragment.requireContext().resources.getIdentifier("weather_wind_direction_${Converter.windDirection(windBearing)}",
                    "string", fragment.requireContext().packageName)

            textView.text = fragment.getString(stringResId, fragment.getString(directionResID), Converter.round(windSpeed))
        } catch (ignored: Exception) {
        }
    }
}

