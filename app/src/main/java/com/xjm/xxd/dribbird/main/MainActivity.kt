package com.xjm.xxd.dribbird.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.widget.Toolbar
import android.widget.ImageView
import android.widget.TextView
import com.xjm.xxd.dribbird.R
import com.xjm.xxd.dribbird.model.UserModel
import com.xjm.xxd.dribbird.utils.StatusBarCompat
import com.xjm.xxd.framework.kotlinext.bindView
import com.xjm.xxd.skeleton.mvp.MVPActivity

class MainActivity : MVPActivity<MainActivityContract.Presenter, MainActivityContract.Viewer>(),
        MainActivityContract.Viewer {

    private val mDrawerLayout by bindView<DrawerLayout>(R.id.drawer_layout)
    private val mToolbar by bindView<Toolbar>(R.id.tool_bar)
    private val mNavigationView by bindView<NavigationView>(R.id.navigation_view)

    private val mIvUserAvatar by bindView<ImageView>(R.id.iv_navigation_header_user_avatar)
    private val mTvUserName by bindView<TextView>(R.id.tv_navigation_header_nickname)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        StatusBarCompat.compat(this)

        initViews()

        presenter().loadUserModel()
    }

    override fun createPresenter(): MainActivityContract.Presenter = MainActivityPresenter()

    private fun initViews() {
        mToolbar.setTitle(R.string.title_main_activity)
        setSupportActionBar(mToolbar)

        mNavigationView.setNavigationItemSelectedListener { menuItem ->
            menuItem.isChecked = true
            mDrawerLayout.closeDrawers()
            true
        }

        val drawerToggle = ActionBarDrawerToggle(this, mDrawerLayout, mToolbar, R.string.open, R.string.close)
        mDrawerLayout.addDrawerListener(drawerToggle)
        drawerToggle.syncState()
    }

    override fun showUserModel(userModel: UserModel?) {
        userModel?.let {
            mTvUserName.text = it.name
        }
    }

    companion object {

        fun open(context: Context?) {
            if (context == null) {
                return
            }
            val intent = Intent(context, MainActivity::class.java)
            context.startActivity(intent)
        }
    }

}
