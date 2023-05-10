package com.design2.chili2.view.card

import android.content.Context
import android.content.res.TypedArray
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.design2.chili2.R
import com.design2.chili2.extensions.visible
import com.facebook.shimmer.ShimmerFrameLayout

class BalanceCardView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = R.attr.balanceCardViewDefaultStyle,
    defStyleRes: Int = R.style.Chili_CardViewStyle_BalanceCardView
): BaseCardView(context, attrs, defStyleAttr, defStyleRes) {

    private lateinit var view: BalanceCardViewViewVariables

    override val styleableAttrRes: IntArray = R.styleable.BalanceCardView

    override val rootContainer: View
        get() = view.root

    override fun inflateView(context: Context) {
        val view = LayoutInflater.from(context).inflate(R.layout.chili_view_card_balance, this, true)
        this.view = BalanceCardViewViewVariables(
            tvLabel = view.findViewById(R.id.tv_title),
            tvValue = view.findViewById(R.id.tv_value),
            ivIcon = view.findViewById(R.id.iv_icon),
            root = view.findViewById(R.id.root_view),
            titleShimmering = view.findViewById(R.id.view_title_shimmer),
            subtitleShimmering = view.findViewById(R.id.view_subtitle_shimmer)
        )
    }

    init { initView(context, attrs, defStyleAttr, defStyleRes) }


    override fun TypedArray.obtainAttributes() {
        setTitle(getText(R.styleable.BalanceCardView_title))
        setValueText(getText(R.styleable.BalanceCardView_value))
        getResourceId(R.styleable.BalanceCardView_titleTextAppearance, -1)
            .takeIf { it != -1 }?.let { setTitleTextAppearance(it) }
        getResourceId(R.styleable.BalanceCardView_valueTextAppearance, -1)
            .takeIf { it != -1 }?.let { setValueTextTextAppearance(it) }
        getResourceId(R.styleable.BalanceCardView_icon, -1)
            .takeIf { it != -1 }?.let { setIcon(it) }
    }

    override fun setupShimmeringViews() {
        super.setupShimmeringViews()
        shimmeringPairs[view.tvLabel] = view.titleShimmering
        shimmeringPairs[view.tvValue] = view.subtitleShimmering
    }

    fun setTitle(charSequence: CharSequence) {
        view.tvLabel.text = charSequence
    }

    fun setTitle(resId: Int) {
        view.tvLabel.setText(resId)
    }

    fun setTitleTextAppearance(resId: Int) {
        view.tvLabel.setTextAppearance(resId)
    }

    fun setValueText(charSequence: CharSequence?) {
        view.tvValue.text = charSequence
    }

    fun setValueText(resId: Int) {
        view.tvValue.setText(resId)
    }

    fun setValueTextTextAppearance(resId: Int) {
        view.tvValue.setTextAppearance(resId)
    }

    fun setIcon(resId: Int) {
        view.ivIcon.apply {
            setImageResource(resId)
            visible()
            shimmeringPairs[view.ivIcon] = null
        }
    }

    fun setIcon(drawable: Drawable) {
        view.ivIcon.apply {
            visible()
            setImageDrawable(drawable)
            shimmeringPairs[view.ivIcon] = null
        }
    }
}

data class BalanceCardViewViewVariables(
    val tvLabel: TextView,
    val tvValue: TextView,
    val ivIcon: ImageView,
    val root: ConstraintLayout,
    val titleShimmering: ShimmerFrameLayout,
    val subtitleShimmering: ShimmerFrameLayout,
)