package com.xjm.xxd.fastwidget.edit;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.xjm.xxd.fastwidget.R;
import com.xjm.xxd.fastwidget.widget.WidgetConfig;

import java.lang.ref.WeakReference;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by queda on 2016/12/2.
 */

public class EditWidgetView extends RelativeLayout implements IEditView {

    @BindView(R.id.tool_bar_ensure)
    ImageView mEnsure;
    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;

    private IEditManager mManager;

    private EditWidgetAdapter mAdapter;
    private EditItemTouchHelperCallback mTouchCallback;
    private ItemTouchHelper mItemTouchHelper;

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

        mRecyclerView.setLayoutManager(new LinearLayoutManager(context));
        mTouchCallback = new EditItemTouchHelperCallback();
        mItemTouchHelper = new ItemTouchHelper(mTouchCallback);
        mItemTouchHelper.attachToRecyclerView(mRecyclerView);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        mManager = new EditWidgetManager(new WeakReference<>(getContext()));
        mManager.bindView(this);
        mAdapter = new EditWidgetAdapter(LayoutInflater.from(getContext()), mManager);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void loadWidgetConfigSuccess(List<WidgetConfig> shownConfigs) {
        // 拿到了widget列表
        mAdapter.bindShownWidgetConfigs(shownConfigs);
    }

    @Override
    public void loadWidgetConfigFailed() {

    }

    /**
     * 对外公开的展示方法
     * 执行出场动画，异步获取数据并刷新界面
     */
    public void show() {
        if (getVisibility() == GONE) {
            mManager.loadWidgetConfig();
            setVisibility(View.VISIBLE);
            WindowManager wm = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
            int height = wm.getDefaultDisplay().getHeight();
            TranslateAnimation translateAnimation = new TranslateAnimation(0, 0, height, 0);
            translateAnimation.setDuration(300);
            translateAnimation.setInterpolator(new DecelerateInterpolator());
            translateAnimation.setFillAfter(true);
            startAnimation(translateAnimation);
        }
    }

    /**
     * 对外公开的隐藏方法
     * 执行退场动画
     */
    public void hide() {
        WindowManager wm = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
        int height = wm.getDefaultDisplay().getHeight();
        TranslateAnimation translateAnimation = new TranslateAnimation(0, 0, 0, height);
        translateAnimation.setDuration(300);
        translateAnimation.setInterpolator(new AccelerateInterpolator());
        translateAnimation.setFillAfter(true);
        translateAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                setVisibility(GONE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        startAnimation(translateAnimation);
    }


}
