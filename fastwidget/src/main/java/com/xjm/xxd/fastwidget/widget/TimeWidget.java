package com.xjm.xxd.fastwidget.widget;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextClock;

import com.xjm.xxd.fastwidget.R;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.view.View.GONE;

/**
 * Created by queda on 2016/12/2.
 */

public class TimeWidget extends BaseWidget {

    @BindView(R.id.text_clock)
    TextClock mTextClock;
    @BindView(R.id.progress_bar)
    ProgressBar mProgressBar;

    @Override
    protected View createView(LayoutInflater layoutInflater) {
        if (layoutInflater == null) {
            return null;
        }
        View view = layoutInflater.inflate(R.layout.layout_time_widget, null);
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
                mTextClock.setVisibility(View.VISIBLE);
            }
        }, 3000);
    }

}
