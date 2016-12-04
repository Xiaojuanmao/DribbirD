package com.xjm.xxd.dribbird.splash;

import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.xjm.xxd.dribbird.R;
import com.xjm.xxd.dribbird.login.LoginActivity;
import com.xjm.xxd.dribbird.ui.MainActivity;
import com.xjm.xxd.dribbird.ui.base.BaseActivity;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SplashActivity extends BaseActivity implements
        SplashActivityView {

    @BindView(R.id.splash_background)
    ImageView mImageBackground;

    @Inject
    ISplashActivityPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //取消标题
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //取消状态栏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);
        getActivityComponent().inject(this);
        mPresenter.bindIView(this);

        initViews();

        mPresenter.checkAccount();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.onDestroy();
        }
    }

    private void initViews() {
        mImageBackground.setImageResource(R.drawable.bg_splash_activity);
        mImageBackground.setAlpha(0.3f);
        mImageBackground.animate().alpha(1.0f).setDuration(2000).start();
    }

    @Override
    public void jumpToLoginActivity() {
        LoginActivity.open(this);
        finish();
    }

    @Override
    public void jumpToMainPage() {
        MainActivity.open(this);
        finish();
    }

}
