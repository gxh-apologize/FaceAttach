package cn.gxh.faceattach;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import cn.gxh.faceattach.ui.activity.BaseActivity;
import cn.gxh.faceattach.ui.fragment.MainFragment;

public class MainActivity extends BaseActivity {

    @Override
    public int getLayoutRes() {
        return R.layout.activity_main;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        if (findFragment(MainFragment.class) == null) {
            loadRootFragment(R.id.flt_activity_main, MainFragment.newInstance());
        }
    }

    @Override
    public void initListener() {

    }

    @Override
    public void initData() {

    }
}
