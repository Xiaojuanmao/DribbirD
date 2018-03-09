package com.xjm.xxd.framework.widget

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import com.xjm.xxd.framework.R

/**
 * Created by queda on 2018/3/9.
 */

class ContentContainer @JvmOverloads constructor(
        context: Context,
        attributeSet: AttributeSet? = null,
        defStyleAttr: Int = 0) : FrameLayout(context, attributeSet, defStyleAttr) {

    private lateinit var mContentView: View
    private val mEmptyView by lazy { initEmptyView() }

    override fun onFinishInflate() {
        super.onFinishInflate()
        init()
    }

    private fun init() {
        mContentView = findViewById(R.id.framework_id_content_view)
        addView(mEmptyView)
    }

    private fun initEmptyView(): EmptyView {
        val emptyView = EmptyView(context)
        val lp = FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        emptyView.layoutParams = lp
        return emptyView
    }

    public fun showContent() {
        showEmptyView(false)
        showContent(true)
    }

    private fun showEmptyView(show: Boolean) {
        mEmptyView.visibility = if (show) View.VISIBLE else View.INVISIBLE
        if (show) {
            bringChildToFront(mEmptyView)
        }
    }

    private fun showContent(show: Boolean) {
        if (show) {
            if (mContentView.visibility == View.VISIBLE) {
                mContentView.alpha = 1f
            } else {
                mContentView.visibility = View.VISIBLE
                mContentView.alpha = 0f
                mContentView.animate().alpha(1.0f).setDuration(260).start()
            }
            bringChildToFront(mContentView)
            mContentView.visibility = View.VISIBLE
        } else {
            mContentView.visibility = View.GONE
        }

    }

}