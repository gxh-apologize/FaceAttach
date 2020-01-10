package cn.gxh.faceattach.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import butterknife.Bind;
import cn.gxh.faceattach.R;
import cn.gxh.faceattach.adapter.HistoryAdapter;

/**
 * 历史记录
 */
public class HistoryTabFragment extends BaseFragment {

    @Bind(R.id.rlv_fragment_tab_history)
    RecyclerView recyclerView;
    private HistoryAdapter historyAdapter;

    public static HistoryTabFragment newInstance() {
        Bundle args = new Bundle();
        HistoryTabFragment fragment = new HistoryTabFragment();
        return fragment;
    }

    @Override
    public int getLayoutRes() {
        return R.layout.fragment_tab_history;
    }

    @Override
    public void initView(Bundle savedInstanceState) {

        historyAdapter = new HistoryAdapter(null);
        historyAdapter.bindToRecyclerView(recyclerView);
    }

    @Override
    public void initListener() {

    }

    @Override
    public void initData() {

    }
}
