package com.design2.chili2.view.cells

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.MotionEvent
import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.core.view.MotionEventCompat
import androidx.core.view.isVisible
import com.design2.chili2.R
import com.design2.chili2.extensions.drawable
import com.design2.chili2.extensions.setOnSingleClickListener
import com.design2.chili2.util.IconSize

class EditableCellView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = R.attr.endIconCellViewDefaultStyle,
    defStyleRes: Int = R.style.Chili_CellViewStyle_BaseCellViewStyle_EndIcon
) : EndIconCellView(context, attrs, defStyleAttr, defStyleRes) {

    lateinit var views: EditableCellViewVariables

    override fun inflateView(context: Context) {
        super.inflateView(context)
        inflateView()
    }

    override fun obtainAttributes(
        context: Context, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int
    ) {
        super.obtainAttributes(context, attrs, defStyleAttr, defStyleRes)
        context.obtainStyledAttributes(
            attrs, R.styleable.EditableCellView, defStyleAttr, defStyleRes
        ).run {
            getResourceId(R.styleable.EditableCellView_dragIcon, -1).let {
                if (it > -1) setDragIcon(it)
            }
            getResourceId(R.styleable.EditableCellView_optionIcon, -1).let {
                if (it > -1) setOptionIcon(it)
            }
            getBoolean(R.styleable.EditableCellView_isDragIconVisible, false).let {
                setDragIconVisibility(it)
            }
            getBoolean(R.styleable.EditableCellView_isOptionIconVisible, false).let {
                setOptionIconVisibility(it)
            }
            getLayoutDimension(
                R.styleable.EditableCellView_editableIconsSize, IconSize.SMALL.value
            ).let {
                when (it) {
                    IconSize.SMALL.value -> setEndIconSize(IconSize.SMALL)
                    IconSize.MEDIUM.value -> setEndIconSize(IconSize.MEDIUM)
                    IconSize.LARGE.value -> setEndIconSize(IconSize.LARGE)
                    else -> setupEditableIconSize(it, it)
                }
            }
            recycle()
        }
    }

    fun setOnOptionClickListener(block: () -> Unit) {
        views.optionImg.setOnSingleClickListener(block)
    }

    @SuppressLint("ClickableViewAccessibility")
    fun setRvItemDragListener(block: () -> Unit) {
        views.dragImg.setOnTouchListener { _, event ->
            if (MotionEventCompat.getActionMasked(event) == MotionEvent.ACTION_DOWN) {
                block.invoke()
            }
            false
        }
    }

    fun setupEditableIconSize(widthPx: Int, heightPx: Int) {
        val dragParams = views.dragImg.layoutParams
        val optionParams = views.dragImg.layoutParams
        dragParams?.height = heightPx
        dragParams?.width = widthPx
        optionParams?.height = heightPx
        optionParams?.width = widthPx
        views.dragImg.layoutParams = dragParams
        views.optionImg.layoutParams = optionParams
    }

    fun setDragIconVisibility(isVisible: Boolean) {
        views.dragImg.isVisible = isVisible
    }

    fun setOptionIconVisibility(isVisible: Boolean) {
        views.dragImg.isVisible = isVisible
    }

    fun setDragIcon(@DrawableRes drawableRes: Int?) {
        drawableRes?.let {
            views.dragImg.setImageDrawable(context.drawable(drawableRes))
        }
    }

    fun setOptionIcon(@DrawableRes drawableRes: Int?) {
        drawableRes?.let {
            views.optionImg.setImageDrawable(context.drawable(drawableRes))
        }
    }

    private fun inflateView() {
        val view =
            LayoutInflater.from(context).inflate(R.layout.chili_view_cell_editable, this, false)
        views = EditableCellViewVariables(
            dragImg = view.findViewById(R.id.iv_chevron),
            optionImg = view.findViewById(R.id.iv_chevron)
        )
    }

}

class EditableCellViewVariables(
    val dragImg: ImageView, val optionImg: ImageView
)