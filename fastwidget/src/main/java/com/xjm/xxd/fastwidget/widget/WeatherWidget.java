package com.xjm.xxd.fastwidget.widget;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.xjm.xxd.fastwidget.R;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.view.View.GONE;

/**
 * Created by queda on 2016/12/2.
 */

public class WeatherWidget extends BaseWidget {

    @BindView(R.id.tv_temp)
    TextView tvTemp;
    @BindView(R.id.tv_temp_detail)
    TextView tvDetail;
    @BindView(R.id.tv_loc_humidity)
    TextView tvHumidity;
    @BindView(R.id.tv_feel_temp)
    TextView tvFeelTemp;
    @BindView(R.id.progress_bar)
    ProgressBar mProgressBar;

    @Override
    protected View createView(LayoutInflater layoutInflater) {
        if (layoutInflater == null) {
            return null;
        }
        View view = layoutInflater.inflate(R.layout.layout_weather_widget, null);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        mProgressBar.postDelayed(new Runnable() {
            @Override
            public void run() {
                mProgressBar.setVisibility(GONE);
                tvDetail.setText("晴");
                tvTemp.setText("3°");
                tvFeelTemp.setText("1°");
                tvHumidity.setText("56%");
            }
        }, 2000);
    }

}
