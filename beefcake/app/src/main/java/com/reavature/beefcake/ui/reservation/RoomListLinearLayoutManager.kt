package com.reavature.beefcake.ui.reservation

import android.content.Context
import android.util.AttributeSet
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

/**
 * API Integration: Rogerr Oliva
 * API Creation: Ankit Patel
 */

    class RoomLinearLayoutManager(
        val context: Context,
        orientation: Int = LinearLayout.VERTICAL,
        reverseLayout: Boolean = false
    ): LinearLayoutManager(context) {

       constructor(
            context: Context?, attrs: AttributeSet?, defStyleAttr: Int,
            defStyleRes: Int
        ):this(context!!) {
            val properties = getProperties(
                context!!, attrs, defStyleAttr, defStyleRes
            )
            orientation = properties.orientation
            reverseLayout = properties.reverseLayout
            stackFromEnd = properties.stackFromEnd
        }


        override fun generateDefaultLayoutParams(): RecyclerView.LayoutParams =
            spanLayoutSize(super.generateDefaultLayoutParams())
        override fun generateLayoutParams(lp: ViewGroup.LayoutParams?): RecyclerView.LayoutParams =
            spanLayoutSize(super.generateLayoutParams(lp))
        private fun spanLayoutSize(layoutParams: RecyclerView.LayoutParams): RecyclerView.LayoutParams {
            when(orientation) {
                VERTICAL -> layoutParams.width = horizontalSpace
                HORIZONTAL -> layoutParams.height = verticalSpace
            }
            return layoutParams
        }
        override fun canScrollHorizontally(): Boolean = false
        override fun canScrollVertically(): Boolean = true
        private val horizontalSpace: Int
            get() = width - paddingStart - paddingEnd
        private val verticalSpace: Int
            get() = height - paddingBottom - paddingTop

    }

