package com.design2.chili2.view.common

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.*
import androidx.core.text.parseAsHtml
import com.design2.chili2.R
import com.design2.chili2.extensions.gone
import com.design2.chili2.extensions.handleUrlClicks
import com.design2.chili2.extensions.visible

class AgreementView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
) : LinearLayout(context, attrs) {

    private lateinit var view: AgreementViewVariables

    init {
        initView(context)
        obtainAttributes(context, attrs)
    }

    private fun initView(context: Context) {
        val view =  LayoutInflater.from(context).inflate(R.layout.chili_view_agreement, this)
        this.view = AgreementViewVariables(
            cbAgreement = view.findViewById(R.id.cb_agreement),
            ivChecked = view.findViewById(R.id.iv_checked),
            tvTitle = view.findViewById(R.id.tv_title)
        )
    }

    private fun obtainAttributes(context: Context, attrs: AttributeSet?) {
        context.obtainStyledAttributes(attrs, R.styleable.AgreementView).run {
            getString(R.styleable.AgreementView_title)?.let { title -> setTitle(title) }
            setChecked(getBoolean(R.styleable.AgreementView_android_checked, false))
            setIsEditable(getBoolean(R.styleable.AgreementView_isEditable, true))
            recycle()
        }
    }

    fun setTitle(title: String) {
        view.tvTitle.text = title.parseAsHtml()
    }

    fun onUrlClick(action: (url: String) -> (Unit)) {
        view.tvTitle.handleUrlClicks { action.invoke(it) }
    }

    fun setChecked(isChecked: Boolean) {
        view.cbAgreement.isChecked = isChecked
    }

    fun setOnCheckChangeListener(listener: (CompoundButton, Boolean) -> Unit) {
        view.cbAgreement.setOnCheckedChangeListener(listener)
    }

    fun setIsEditable(isEditable: Boolean) {
        when (isEditable) {
            true -> editableMode()
            else -> nonEditableMode()
        }
    }

    private fun editableMode() {
        view.ivChecked.gone()
        view.cbAgreement.visible()
    }

    private fun nonEditableMode() {
        view.ivChecked.visible()
        view.cbAgreement.gone()
    }

}

data class AgreementViewVariables(
    val cbAgreement: CheckBox,
    val ivChecked: ImageView,
    val tvTitle: TextView
)