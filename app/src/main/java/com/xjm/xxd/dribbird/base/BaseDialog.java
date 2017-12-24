package com.xjm.xxd.dribbird.base;

import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.xjm.xxd.dribbird.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by queda on 2016/12/4.
 */

public class BaseDialog extends DialogFragment {

    @BindView(R.id.dialog_title_root)
    LinearLayout mTitleRoot;
    @BindView(R.id.dialog_title)
    TextView mTitleTv;
    @BindView(R.id.dialog_message_root)
    LinearLayout mMessageRoot;
    @BindView(R.id.dialog_message_progressbar)
    ProgressBar mProgressBar;
    @BindView(R.id.dialog_message)
    TextView mMessageTv;
    @BindView(R.id.dialog_manipulate_root)
    LinearLayout mManipulateRoot;
    @BindView(R.id.dialog_cancel)
    TextView mDialogCancel;
    @BindView(R.id.dialog_ensure)
    TextView mDialogEnsure;

    private String mTitle;
    private String mMessage;
    private boolean mHasProgressbar;

    private BaseDialogCallback mCallback;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_dialog_base, null);
        ButterKnife.bind(this, view);
        setTitle(mTitle);
        setMessage(mMessage);
        setCallback(mCallback);
        enableProgressbar(mHasProgressbar);
        if (mProgressBar.getVisibility() == View.GONE && mMessageTv.getVisibility() == View.GONE) {
            mMessageRoot.setVisibility(View.GONE);
        } else {
            mMessageRoot.setVisibility(View.VISIBLE);
        }
        return view;
    }

    @OnClick({R.id.dialog_ensure, R.id.dialog_cancel})
    void onManipulateClicked(View v) {
        switch (v.getId()) {
            case R.id.dialog_ensure:
                if (mCallback != null) {
                    mCallback.onEnsureClicked();
                }
                break;

            case R.id.dialog_cancel:
                if (mCallback != null) {
                    mCallback.onCancelClicked();
                }
                break;
        }
    }

    public void title(String title) {
        mTitle = title;
    }

    public void message(String message) {
        mMessage = message;
    }

    public void callback(BaseDialogCallback callback) {
        mCallback = callback;
    }

    public void progressBar(boolean enable) {
        mHasProgressbar = enable;
    }

    private void setTitle(String title) {
        if (TextUtils.isEmpty(title)) {
            enableTitle(false);
        } else {
            enableTitle(true);
            mTitleTv.setText(title);
        }
    }

    public void setMessage(String message) {
        if (TextUtils.isEmpty(message)) {
            enableMessage(false);
        } else {
            enableMessage(true);
            mMessageTv.setText(message);
        }
    }

    private void setCallback(BaseDialogCallback callback) {
        if (callback == null) {
            enableManipulateRoot(false);
        } else {
            enableManipulateRoot(true);
            mCallback = callback;
        }
    }

    private void enableMessage(boolean enable) {
        if (enable) {
            mMessageTv.setVisibility(View.VISIBLE);
        } else {
            mMessageTv.setVisibility(View.GONE);
        }
    }

    private void enableProgressbar(boolean enable) {
        if (enable) {
            mProgressBar.setVisibility(View.VISIBLE);
            mHasProgressbar = true;
        } else {
            mProgressBar.setVisibility(View.GONE);
            mHasProgressbar = false;
        }
    }

    private void enableTitle(boolean enable) {
        if (enable) {
            mTitleRoot.setVisibility(View.VISIBLE);
        } else {
            mTitleRoot.setVisibility(View.GONE);
        }
    }

    private void enableManipulateRoot(boolean enable) {
        if (enable) {
            mManipulateRoot.setVisibility(View.VISIBLE);
        } else {
            mManipulateRoot.setVisibility(View.GONE);
        }
    }

    public static class Builder {

        private String mTitle;
        private String mMessage;
        private BaseDialogCallback mCallback;
        private boolean mHasProgressBar;

        public Builder title(String title) {
            mTitle = title;
            return this;
        }

        public Builder message(String message) {
            mMessage = message;
            return this;
        }

        public Builder callback(BaseDialogCallback callback) {
            mCallback = callback;
            return this;
        }

        public Builder progressBar(boolean enable) {
            mHasProgressBar = enable;
            return this;
        }

        public BaseDialog build() {
            BaseDialog baseDialog = new BaseDialog();
            baseDialog.title(mTitle);
            baseDialog.message(mMessage);
            baseDialog.callback(mCallback);
            baseDialog.progressBar(mHasProgressBar);
            return baseDialog;
        }
    }

    /**
     * ensure or cancel button listener
     */
    public interface BaseDialogCallback {

        void onEnsureClicked();

        void onCancelClicked();
    }

}
