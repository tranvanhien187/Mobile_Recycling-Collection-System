package datn.cnpm.rcsystem.base

import android.view.View
import androidx.core.view.isVisible
import datn.cnpm.rcsystem.databinding.ToolBarBinding

interface ToolBarController {

    val toolbar: ToolBarBinding

    fun showToolbar(
        title: String,
        iconStart: Int? = null,
        iconEnd: Int? = null,
    ) {
        toolbar.appBarLayout.visibility = View.VISIBLE
        showToolbarTitle(title)
        if (iconStart != null) {
            showToolbarIconStart(iconStart)
        }
        if (iconEnd != null) {
            showToolbarIconEnd(iconEnd)
        }
    }

    fun hideToolbar() {
        toolbar.appBarLayout.visibility = View.GONE
    }

    fun showToolbarTitle(title: String) {
        toolbar.tvContentCenter.apply {
            text = title
            visibility = View.VISIBLE
        }
    }

    fun showToolbarIconStart(icon: Int) {
        toolbar.ivContentStart.apply {
            setImageResource(icon)
            visibility = View.VISIBLE
            setOnClickListener { onIconStartClicked() }
        }
    }

    fun showToolbarIconEnd(icon: Int) {
        toolbar.ivContentEnd.apply {
            setImageResource(icon)
            visibility = View.VISIBLE
            setOnClickListener { onIconEndClicked() }
        }
    }

    fun showToolbarIconEndSecondary(icon: Int) {
        toolbar.ivContentEndSecondary.apply {
            setImageResource(icon)
            visibility = View.VISIBLE
            setOnClickListener { onIconEndSecondaryClicked() }
        }
    }

    fun displayToolbarIconEnd(showIcon: Boolean) {
        toolbar.ivContentEnd.isVisible = showIcon
    }

    fun displayToolbarIconEndSecondary(showIcon: Boolean) {
        toolbar.ivContentEndSecondary.isVisible = showIcon
    }

//    fun showToolbarContentEnd(
//        label: String,
//        color: Int = R.attr.toolbarButtonCancel,
//        textAppearance: Int = R.attr.toolbarContentEndAppearance
//    ) {
//        toolbar.tvContentEnd.apply {
//            setTextColor(getColorFromAttr(context, color))
//            setTextAppearance(getResIdFromAttr(context, textAppearance))
//            text = label
//            isVisible = true
//            setOnClickListener { onContentEndClicked() }
//        }
//    }

    fun showToolbarContentEnd(
        label: String,
//        color: Int = R.attr.toolbarButtonCancel,
//        textAppearance: Int = R.attr.toolbarContentEndAppearance
    ) {
        toolbar.tvContentEnd.apply {
//            setTextColor(getColorFromAttr(context, color))
//            setTextAppearance(getResIdFromAttr(context, textAppearance))
            text = label
            isVisible = true
            setOnClickListener { onContentEndClicked() }
        }
    }

    fun displayToolbarContentEnd(showContent: Boolean) {
        toolbar.tvContentEnd.isVisible = showContent
    }

    fun onIconStartClicked() {
        // Let implementation override
    }

    fun onIconEndClicked() {
        // Let implementation override
    }

    fun onIconEndSecondaryClicked() {
        // Let implementation override
    }

    fun onContentEndClicked() {
        // Let implementation override
    }

    fun disableClickIconEnd(disable: Boolean, icon: Int) {
        toolbar.apply {
            ivContentEnd.isClickable = !disable
            ivContentEnd.setImageResource(icon)
        }
    }
}