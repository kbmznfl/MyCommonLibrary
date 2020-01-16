package com.itdlc.mycommonlibrary.base;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.itdlc.mycommonlibrary.mvp.UiView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseCommonFragment extends Fragment{

    protected Activity mActivity;
    private Unbinder mUnBinder;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = null;
        if (getLayoutId() != 0) {
            view = inflater.inflate(getLayoutId(), container, false);
            mUnBinder = ButterKnife.bind(this, view);
        }

        return view;
    }

    protected abstract int getLayoutId();

    @Override
    public void onDestroyView() {

        super.onDestroyView();

        if (mUnBinder != null) {
            mUnBinder.unbind();
        }
    }

    public void startActivity(Class<? extends Activity> cls) {
        startActivity(new Intent(getActivity(), cls));
    }

    public void showToast(int resId) {
        try {
            getActivityView().showToast(resId);
        } catch (Exception e) {
            //e.printStackTrace();
        }
    }

    public void showToast(String text) {
        try {
            getActivityView().showToast(text);
        } catch (Exception e) {
            //e.printStackTrace();
        }
    }

    public void showOneToast(int resId) {
        try {
            getActivityView().showOneToast(resId);
        } catch (Exception e) {
            //e.printStackTrace();
        }
    }

    public void showOneToast(String text) {
        try {
            getActivityView().showOneToast(text);
        } catch (Exception e) {
            //e.printStackTrace();
        }
    }

    public void showWaitingDialog(String text, boolean cancelable) {
        try {
            getActivityView().showWaitingDialog(text, cancelable);
        } catch (Exception e) {
            //e.printStackTrace();
        }
    }

    public void showWaitingDialog(int stringRes, boolean cancelable) {
        try {
            getActivityView().showWaitingDialog(stringRes, cancelable);
        } catch (Exception e) {
            //e.printStackTrace();
        }
    }

    public void dismissWaitingDialog() {
        try {
            getActivityView().dismissWaitingDialog();
        } catch (Exception e) {
            //e.printStackTrace();
        }
    }

    private UiView getActivityView() {
        return (UiView) mActivity;
    }

}
