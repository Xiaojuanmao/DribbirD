package com.xjm.xxd.dribbird.homepage

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.widget.Toolbar
import android.view.View
import android.widget.TextView
import com.facebook.drawee.view.SimpleDraweeView
import com.xjm.xxd.dribbird.R
import com.xjm.xxd.dribbird.model.UserModel
import com.xjm.xxd.dribbird.user.profile.UserProfileActivity
import com.xjm.xxd.dribbird.utils.StatusBarCompat
import com.xjm.xxd.framework.kotlinext.bindView
import com.xjm.xxd.skeleton.mvp.MVPActivity

class MainActivity : MVPActivity<Presenter, Viewer>(),
        Viewer {

    private val mDrawerLayout by bindView<DrawerLayout>(R.id.drawer_layout)
    private val mToolbar by bindView<Toolbar>(R.id.tool_bar)
    private val mNavigationView by bindView<NavigationView>(R.id.navigation_view)

    private lateinit var mIvUserAvatar: SimpleDraweeView
    private lateinit var mTvUserName: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        StatusBarCompat.compat(this)
        setContentView(R.layout.activity_main)

        initViews()

        presenter().loadUserModel()
    }

    override fun createPresenter(): Presenter = MainActivityPresenter()

    private fun initViews() {
        mToolbar.setTitle(R.string.title_main_activity)
        setSupportActionBar(mToolbar)

        val headerView = mNavigationView.inflateHeaderView(R.layout.layout_main_activity_navigation_header)
        mNavigationView.inflateMenu(R.menu.menu_main_activity_navigation)
        mIvUserAvatar = headerView.findViewById(R.id.iv_navigation_header_user_avatar)
        mTvUserName = headerView.findViewById(R.id.tv_navigation_header_nickname)
        mIvUserAvatar.setOnClickListener {
            mDrawerLayout.closeDrawers()
            UserProfileActivity.open(this@MainActivity)
        }
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
            mIvUserAvatar.setImageURI(it.avatar)
        }
    }

    companion object {

        fun open(context: Context) {
            val intent = Intent(context, MainActivity::class.java)
            context.startActivity(intent)
        }
    }

}
