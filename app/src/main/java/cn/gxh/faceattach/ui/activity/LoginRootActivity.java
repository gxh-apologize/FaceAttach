package cn.gxh.faceattach.ui.activity;

import android.os.Bundle;

import cn.gxh.faceattach.R;
import cn.gxh.faceattach.ui.fragment.LoginFragment;

public class LoginRootActivity extends BaseActivity{
    @Override
    public int getLayoutRes() {
        return R.layout.activity_login_root;
    }

    @Override
    public void initView(Bundle savedInstanceState) {

    }

    @Override
    public void initListener() {

    }

    @Override
    public void initData() {

        loadRootFragment(R.id.flt_activity_login_root,LoginFragment.newInstance(),
                false,false);
    }
}
