package com.xjm.xxd.dribbird.user.profile

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.xjm.xxd.dribbird.R
import com.xjm.xxd.skeleton.mvp.MVPActivity

class UserProfileActivity : MVPActivity<Presenter, Viewer>(),
        Viewer {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_profile)
    }

    override fun createPresenter(): Presenter = UserProfilePresenter()

    companion object {

        @JvmStatic
        fun open(context: Context) {
            context.startActivity(Intent(context, UserProfileActivity::class.java))
        }

    }

}
