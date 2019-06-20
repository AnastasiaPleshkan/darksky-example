package net.darksky.example.binding

import androidx.fragment.app.Fragment
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import javax.inject.Inject

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
        }catch (ignored:Exception) {}
    }
}

