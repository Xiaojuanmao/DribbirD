package com.xjm.xxd.dribbird.user.profile

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.CollapsingToolbarLayout
import android.support.v7.widget.Toolbar
import com.xjm.xxd.dribbird.R
import com.xjm.xxd.dribbird.utils.StatusBarCompat
import com.xjm.xxd.framework.kotlinext.bindView
import com.xjm.xxd.skeleton.mvp.MVPActivity

class UserProfileActivity : MVPActivity<Presenter, Viewer>(),
        Viewer {

    private val mCollapsingToolbar by bindView<CollapsingToolbarLayout>(R.id.collapsing_toolbar)
    private val mToolbar by bindView<Toolbar>(R.id.tool_bar)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        StatusBarCompat.compat(this)
        setContentView(R.layout.activity_user_profile)

        mToolbar.title = getString(R.string.app_name)
        mToolbar.setNavigationOnClickListener {
            onBackPressed()
        }
    }

    override fun createPresenter(): Presenter = UserProfilePresenter()

    companion object {

        @JvmStatic
        fun open(context: Context) {
            context.startActivity(Intent(context, UserProfileActivity::class.java))
        }

    }

}
