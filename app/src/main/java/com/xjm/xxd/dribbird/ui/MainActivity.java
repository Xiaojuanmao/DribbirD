package com.xjm.xxd.dribbird.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.xjm.xxd.dribbird.R;
import com.xjm.xxd.dribbird.presenter.activity.IMainActivityPresenter;
import com.xjm.xxd.dribbird.ui.base.BaseActivity;
import com.xjm.xxd.dribbird.view.MainActivityView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity implements
        MainActivityView {

    @BindView(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;
    @BindView(R.id.tool_bar)
    Toolbar mToolbar;
    @BindView(R.id.navigation_view)
    NavigationView mNavigationView;

    private ActionBarDrawerToggle mDrawerToggle;

    @Inject
    IMainActivityPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        getActivityComponent().inject(this);
        mPresenter.bindIView(this);

        initViews();

    }

    private void initViews() {
        mToolbar.setTitle(R.string.main_activity_title);
        setSupportActionBar(mToolbar);

        final ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setHomeButtonEnabled(true);
            ab.setDisplayHomeAsUpEnabled(true);
        }

        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(MenuItem menuItem) {
                    menuItem.setChecked(true);
                    mDrawerLayout.closeDrawers();
                    return true;
                }
            });

        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.open, R.string.close);
        mDrawerToggle.syncState();
        mDrawerLayout.setDrawerListener(mDrawerToggle);

    }

    public static void open(BaseActivity baseActivity) {
        if (baseActivity == null) {
            return;
        }
        Intent intent = new Intent(baseActivity, MainActivity.class);
        baseActivity.startActivity(intent);
    }

}
