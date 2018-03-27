package com.xjm.xxd.dribbird.user.profile

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.CollapsingToolbarLayout
import com.xjm.xxd.dribbird.R
import com.xjm.xxd.dribbird.utils.StatusBarCompat
import com.xjm.xxd.framework.kotlinext.bindView
import com.xjm.xxd.skeleton.mvp.MVPActivity

class UserProfileActivity : MVPActivity<Presenter, Viewer>(),
        Viewer {

    private val mCollaspingToolbar by bindView<CollapsingToolbarLayout>(R.id.collapsing_toolbar)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        StatusBarCompat.compat(this)
        setContentView(R.layout.activity_user_profile)
        mCollaspingToolbar.title = getString(R.string.app_name)
    }

    override fun createPresenter(): Presenter = UserProfilePresenter()

    companion object {

        @JvmStatic
        fun open(context: Context) {
            context.startActivity(Intent(context, UserProfileActivity::class.java))
        }

    }

}
