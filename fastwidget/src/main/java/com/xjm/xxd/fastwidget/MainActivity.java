package com.xjm.xxd.fastwidget;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.xjm.xxd.fastwidget.container.WidgetGroupContainer;
import com.xjm.xxd.fastwidget.edit.EditWidgetView;
import com.xjm.xxd.fastwidget.widget.NewsWidget;
import com.xjm.xxd.fastwidget.widget.TimeWidget;
import com.xjm.xxd.fastwidget.widget.WeatherWidget;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MainActivity extends AppCompatActivity {

    @BindView(R.id.container)
    WidgetGroupContainer mContainer;
    @BindView(R.id.edit_view)
    EditWidgetView mEditView;

    private static final int MENU_ID_MANAGE = 0;
    private static final int MENU_ID_WEATHER = 1;
    private static final int MENU_ID_TIME = 2;
    private static final int MENU_ID_NEWS = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(Menu.NONE, MENU_ID_MANAGE, Menu.NONE, "管理");
        menu.add(Menu.NONE, MENU_ID_WEATHER, Menu.NONE, "天氣");
        menu.add(Menu.NONE, MENU_ID_TIME, Menu.NONE, "時間");
        menu.add(Menu.NONE, MENU_ID_NEWS, Menu.NONE, "新闻");
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case MENU_ID_MANAGE:
                // TODO : 彈出管理widget的界面
                mEditView.show();
                break;

            case MENU_ID_WEATHER:
                mContainer.addWidget(new WeatherWidget());
                mContainer.saveWidgetConfigs();
                break;

            case MENU_ID_TIME:
                mContainer.addWidget(new TimeWidget());
                mContainer.saveWidgetConfigs();
                break;

            case MENU_ID_NEWS:
                mContainer.addWidget(new NewsWidget());
                mContainer.saveWidgetConfigs();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if (mEditView.getVisibility() == View.VISIBLE) {
            mEditView.hide();
        } else {
            super.onBackPressed();
        }
    }

}
