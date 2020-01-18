package com.itdlc.mycommonlibrary.base;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.itdlc.mycommonlibrary.dialog.WaitingDialog;
import com.itdlc.mycommonlibrary.dialog.WaitingDialogImpl;
import com.itdlc.mycommonlibrary.utils.ToastUtil;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseCommonActivity extends AppCompatActivity {

    private Unbinder mUnbinder;
    private WaitingDialog mWaitingDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getLayoutId() != 0) {// 子类设置布局id使用,
            setContentView(getLayoutId());
        }

        if (useButterKnife()) {
            //所有的ButterKnife绑定让父类完成
            mUnbinder = ButterKnife.bind(this);
        }
    }

    protected abstract int getLayoutId();

    @Override
    protected void onDestroy() {

        dismissWaitingDialog();
        super.onDestroy();

        if (useButterKnife()) {
            mUnbinder.unbind();//在页面销毁时解绑
        }
    }

    /**
     * 是否使用ButterKnife
     *
     * @return
     */
    protected boolean useButterKnife() {
        return true;
    }

    public void startActivity(Class<? extends Activity> cls) {
        startActivity(new Intent(this, cls));
    }

    public void showToast(int resId) {
        ToastUtil.show(this, resId);
    }

    public void showToast(String text) {
        ToastUtil.show(this, text);
    }

    public void showOneToast(int resId) {
        ToastUtil.showOne(this, resId);
    }

    public void showOneToast(String text) {
        ToastUtil.showOne(this, text);
    }

    /**
     * 获取等待对话框实例，可以重写这个方法以实现自己的等待对话框
     *
     * @return
     */
    protected WaitingDialog getWaitingDialogInstance() {
        return WaitingDialogImpl.newDialog(this);
    }

    public void showWaitingDialog(String text, boolean cancelable) {
        if (mWaitingDialog == null) {
            mWaitingDialog = getWaitingDialogInstance();
        }
        if (mWaitingDialog.isShowing()) {
            mWaitingDialog.dismiss();
        }

        mWaitingDialog.setMessage(text).setCancelable(cancelable).show();
    }

    public void showWaitingDialog(int stringRes, boolean cancelable) {
        showWaitingDialog(getString(stringRes), cancelable);
    }

    public void dismissWaitingDialog() {
        try {
            if (mWaitingDialog != null && mWaitingDialog.isShowing()) {
                mWaitingDialog.dismiss();
            }
        } catch (Exception e) {
            //e.printStackTrace();
        }
    }
    
    /*public void activityCountdown(int mTime,TextView title) {           //倒计时（时间结束时返回结束当前页面）
        final int[] time = { mTime };
        Observable.interval(0, 1, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .take(mTime + 1)
                .observeOn(AndroidSchedulers.mainThread())
                .compose(this.<Long>bindUntilEvent(ActivityEvent.STOP))
                .subscribe(new Observer<Long>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Long aLong) {
                        time[0] -= 1;
                        if (time[0] > 0) {
                            title.setText(time[0] + "s");
                        } else {
                            finish();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void waitCountdown(int mTime,TextView title) {            //倒计时（时间结束时结束所有页面）
        final int[] time = { mTime };
        Observable.interval(0, 1, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .take(mTime + 1)
                .observeOn(AndroidSchedulers.mainThread())
                .doFinally(new Action() {
                    @Override
                    public void run() throws Exception {
                        Intent intent = new Intent(getApplicationContext(),SelectAccessActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                        finish();
                    }
                })
                .compose(this.<Long>bindUntilEvent(ActivityEvent.STOP))
                .subscribe(new Observer<Long>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Long aLong) {
                        time[0] -= 1;
                        if (time[0] > 0) {
                            title.setText(time[0] + "s");
                        } else {
                            finish();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }*/

}
