package cn.gxh.faceattach.ui.fragment;

import android.os.Bundle;

import cn.gxh.faceattach.R;

/**
 * 控制
 */
public class ControlTabFragment extends BaseFragment {

    public static ControlTabFragment newInstance() {
        Bundle args = new Bundle();
        ControlTabFragment fragment = new ControlTabFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getLayoutRes() {
        return R.layout.fragment_tab_control;
    }

    @Override
    public void initView(Bundle savedInstanceState) {

    }

    @Override
    public void initListener() {

    }

    @Override
    public void initData() {

    }
}
