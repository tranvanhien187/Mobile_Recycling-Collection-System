package datn.cnpm.rcsystem.common.widget

import android.content.Context
import android.util.AttributeSet
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import androidx.appcompat.widget.LinearLayoutCompat

/**
 * Created by NguyenHa on 01/10/2022.
 */
class HorizontalOptionView @JvmOverloads constructor(
    context: Context,
    attributes: AttributeSet,
    defStyle: Int = 0,
) : LinearLayoutCompat(context, attributes, defStyle) {

    internal var onItemClicked: (String) -> Unit = {}

    init {
        layoutParams = LayoutParams(WRAP_CONTENT, WRAP_CONTENT)
//        background =
    }

    internal fun applyData(data: List<String>){
        removeAllViews()
        data.forEach {

        }
    }
}