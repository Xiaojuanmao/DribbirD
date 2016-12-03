package com.xjm.xxd.fastwidget.edit;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.xjm.xxd.fastwidget.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by queda on 2016/12/2.
 */

public class EditWidgetView extends RelativeLayout {

    @BindView(R.id.tool_bar_ensure)
    ImageView mEnsure;

    public EditWidgetView(Context context) {
        this(context, null);
    }

    public EditWidgetView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public EditWidgetView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initViews(context);
    }

    private void initViews(Context context) {
        View view = inflate(context, R.layout.layout_edit_widget_view, this);
        ButterKnife.bind(this, view);
    }



}
