package com.itdlc.mycommonlibrary;

import android.os.Bundle;
import android.widget.Button;

import com.itdlc.mycommonlibrary.base.BaseCommonActivity;

import butterknife.BindView;

public class MainActivity extends BaseCommonActivity {

    @BindView(R.id.begin)
    Button begin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

}
