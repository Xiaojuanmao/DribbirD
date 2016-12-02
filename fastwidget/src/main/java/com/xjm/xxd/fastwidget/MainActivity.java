package com.xjm.xxd.fastwidget;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.xjm.xxd.fastwidget.container.WidgetGroupContainer;
import com.xjm.xxd.fastwidget.widget.TimeWidget;
import com.xjm.xxd.fastwidget.widget.WeatherWidget;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.container)
    WidgetGroupContainer mContainer;

    private static final int MENU_ID_MANAGE = 0;
    private static final int MENU_ID_WEATHER = 1;
    private static final int MENU_ID_TIME = 2;

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
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case MENU_ID_MANAGE:

                break;

            case MENU_ID_WEATHER:
                mContainer.addWidget(new WeatherWidget());
                break;

            case MENU_ID_TIME:
                mContainer.addWidget(new TimeWidget());
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
