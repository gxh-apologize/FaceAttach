package cn.gxh.faceattach.ui.fragment;

import android.os.Bundle;

import butterknife.Bind;
import cn.gxh.faceattach.R;
import cn.gxh.faceattach.ui.view.BottomBar;
import cn.gxh.faceattach.ui.view.BottomBarTab;
import cn.gxh.faceattach.util.Logger;
import me.yokeyword.eventbusactivityscope.EventBusActivityScope;
import me.yokeyword.fragmentation.SupportFragment;

public class MainFragment extends BaseFragment {

    @Bind(R.id.bb_fragment_main)
    BottomBar bottomBar;

    private SupportFragment[] mFragments = new SupportFragment[3];
    public static MainFragment newInstance() {
        Bundle args = new Bundle();
        MainFragment fragment = new MainFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getLayoutRes() {
        return R.layout.fragment_main;
    }

    @Override
    public void initView(Bundle savedInstanceState) {

    }

    @Override
    public void initListener() {

    }

    @Override
    public void initData() {

        Logger.d("gxh","initData");

        SupportFragment firstFragment = findChildFragment(ControlTabFragment.class);
        if (firstFragment == null) {
            mFragments[0] = ControlTabFragment.newInstance();
            mFragments[1] = AddPersonTabFragment.newInstance();
            mFragments[2] = HistoryTabFragment.newInstance();

            loadMultipleRootFragment(R.id.flt_fragment_main, 0,
                    mFragments[0],
                    mFragments[1],
                    mFragments[2]);
        } else {
            // 这里库已经做了Fragment恢复,所有不需要额外的处理了, 不会出现重叠问题

            // 这里我们需要拿到mFragments的引用
            mFragments[0] = firstFragment;
            mFragments[1] = findChildFragment(AddPersonTabFragment.class);
            mFragments[2] = findChildFragment(HistoryTabFragment.class);
        }



        bottomBar
                .addItem(new BottomBarTab(_mActivity, R.drawable.ic_message_white_24dp, getString(R.string.bottomBar_control)))
                .addItem(new BottomBarTab(_mActivity, R.drawable.ic_home_white_24dp, getString(R.string.bottomBar_add)))
                .addItem(new BottomBarTab(_mActivity, R.drawable.ic_discover_white_24dp, getString(R.string.bottomBar_history)));
        // 模拟未读消息
        //   bottomBar.getItem(0).setUnreadCount(9);
        bottomBar.setOnTabSelectedListener(new BottomBar.OnTabSelectedListener() {
            @Override
            public void onTabSelected(int position, int prePosition) {
                showHideFragment(mFragments[position], mFragments[prePosition]);
//                BottomBarTab tab = bottomBar.getItem(0);
//                if (position == 0) {
//                    tab.setUnreadCount(0);
//                } else {
//                    tab.setUnreadCount(tab.getUnreadCount() + 1);
//                }
            }

            @Override
            public void onTabUnselected(int position) {

            }

            @Override
            public void onTabReselected(int position) {
                // 在FirstPagerFragment,FirstHomeFragment中接收, 因为是嵌套的Fragment
                // 主要为了交互: 重选tab 如果列表不在顶部则移动到顶部,如果已经在顶部,则刷新
                //EventBusActivityScope.getDefault(_mActivity).post(new TabSelectedEvent(position));
            }
        });
    }

    /**
     * start other BrotherFragment
     */
    public void startBrotherFragment(SupportFragment targetFragment) {
        start(targetFragment);
    }
}
