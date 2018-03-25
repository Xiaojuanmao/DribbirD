package com.xjm.xxd.dribbird.splash

import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import android.widget.ImageView
import com.xjm.xxd.dribbird.R
import com.xjm.xxd.dribbird.login.LoginActivity
import com.xjm.xxd.dribbird.homepage.MainActivity
import com.xjm.xxd.framework.kotlinext.bindView
import com.xjm.xxd.skeleton.mvp.MVPActivity

class SplashActivity : MVPActivity<SplashActivityContract.Presenter, SplashActivityContract.Viewer>(),
        SplashActivityContract.Viewer {

    private val mIvBackground by bindView<ImageView>(R.id.iv_background)

    override fun onCreate(savedInstanceState: Bundle?) {
        //取消标题
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        //取消状态栏
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        initViews()
        presenter().checkAccount()
    }

    override fun createPresenter(): SplashActivityContract.Presenter = SplashActivityPresenter()

    private fun initViews() {
        mIvBackground.alpha = 0.3f
        mIvBackground.setImageResource(R.drawable.bg_splash_activity)
        mIvBackground.animate().alpha(1.0f).setDuration(2000).start()
    }

    override fun jumpToLoginActivity() {
        LoginActivity.open(this)
        finish()
    }

    override fun jumpToMainPage() {
        MainActivity.open(this)
        finish()
    }

}
