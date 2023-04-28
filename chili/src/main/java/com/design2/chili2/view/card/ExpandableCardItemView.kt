package com.design2.chili2.view.card

import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import com.design2.chili2.R
import com.design2.chili2.extensions.setTextOrHide
import com.design2.chili2.view.shimmer.FacebookShimmering
import com.facebook.shimmer.ShimmerFrameLayout

class ExpandableCardItemView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = R.attr.expandableCardItemViewDefaultStyle,
    defStyleRes: Int = R.style.Chili_CardViewStyle_ExpandableCardItemView
) : BaseCardView(context, attrs, defStyleAttr, defStyleRes), FacebookShimmering {

    override val styleableAttrRes: IntArray = R.styleable.ExpandableCardItemView

    private val mutableShimmeringViewMap = mutableMapOf<View, View?>()
    private val shimmerViewGroup: List<ShimmerFrameLayout> by lazy {
        listOf(
            findViewById(R.id.view_title_shimmer),
            findViewById(R.id.view_subtitle_shimmer),
            findViewById(R.id.view_title_value_shimmer),
            findViewById(R.id.view_subtitle_value_shimmer),
        )
    }

    private lateinit var view: ExpandableCardItemViewVariables

    init { initView(context, attrs, defStyleAttr, defStyleRes) }

    override fun inflateView(context: Context) {
        val view = LayoutInflater.from(context).inflate(R.layout.chili_view_expandable_card_item, this, true)
        this.view = ExpandableCardItemViewVariables(
            tvTitle = view.findViewById(R.id.tv_title),
            tvSubtitle = view.findViewById(R.id.tv_subtitle),
            tvTitleValue = view.findViewById(R.id.tv_title_value),
            tvSubtitleValue = view.findViewById(R.id.tv_subtitle_value),
            tvTitleShimmer = view.findViewById(R.id.view_title_shimmer),
            tvSubtitleShimmer = view.findViewById(R.id.view_subtitle_shimmer),
            tvTitleValueShimmer = view.findViewById(R.id.view_title_value_shimmer),
            tvSubtitleValueShimmer = view.findViewById(R.id.view_subtitle_value_shimmer),
        )
    }

    override fun TypedArray.obtainAttributes() {
        getString(R.styleable.ExpandableCardItemView_title).run { setTitle(this) }
        getString(R.styleable.ExpandableCardItemView_subtitle).run { setSubtitle(this) }
        getString(R.styleable.ExpandableCardItemView_titleValue).run { setTitleValue(this) }
        getString(R.styleable.ExpandableCardItemView_subtitleValue).run { setSubtitleValue(this) }
    }

    override fun setupView() {
        mutableShimmeringViewMap[view.tvTitle] = view.tvTitleShimmer
    }


    fun setTitle(charSequence: CharSequence?) {
        view.tvTitle.text = charSequence
    }

    fun setTitle(resId: Int) {
        view.tvTitle.setText(resId)
    }

    fun setSubtitle(charSequence: CharSequence?) {
        view.tvSubtitle.setTextOrHide(charSequence)
        if (charSequence == null) mutableShimmeringViewMap.remove(view.tvSubtitle)
        else mutableShimmeringViewMap[view.tvSubtitle] = view.tvSubtitleShimmer
    }

    fun setSubtitle(resId: Int?) {
        view.tvSubtitle.setTextOrHide(resId)
        if (resId == null) mutableShimmeringViewMap.remove(view.tvSubtitle)
        else mutableShimmeringViewMap[view.tvSubtitle] = view.tvSubtitleShimmer
    }

    fun setTitleValue(charSequence: CharSequence?) {
        view.tvTitleValue.setTextOrHide(charSequence)
        if (charSequence == null) mutableShimmeringViewMap.remove(view.tvTitleValue)
        else mutableShimmeringViewMap[view.tvTitleValue] = view.tvTitleValueShimmer
    }

    fun setTitleValue(resId: Int) {
        view.tvTitleValue.setText(resId)
        mutableShimmeringViewMap[view.tvTitleValue] = view.tvTitleValueShimmer
    }

    fun setSubtitleValue(charSequence: CharSequence?) {
        view.tvSubtitleValue.setTextOrHide(charSequence)
        if (charSequence == null) mutableShimmeringViewMap.remove(view.tvSubtitleValue)
        else mutableShimmeringViewMap[view.tvSubtitleValue] = view.tvSubtitleValueShimmer
    }

    fun setSubtitleValue(resId: Int) {
        view.tvSubtitleValue.setText(resId)
        mutableShimmeringViewMap[view.tvSubtitleValue] = view.tvSubtitleValueShimmer
    }

    override fun onStartShimmer() {}
    override fun onStopShimmer() {}
    override fun getShimmerLayouts(): List<ShimmerFrameLayout> = shimmerViewGroup
    override fun getShimmeribleViewsPair(): Map<View, View?> = mutableShimmeringViewMap
}

data class ExpandableCardItemViewVariables(
    val tvTitle: TextView,
    val tvSubtitle: TextView,
    val tvTitleValue: TextView,
    val tvSubtitleValue: TextView,
    val tvTitleShimmer: View,
    val tvSubtitleShimmer: View,
    val tvTitleValueShimmer: View,
    val tvSubtitleValueShimmer: View,
)