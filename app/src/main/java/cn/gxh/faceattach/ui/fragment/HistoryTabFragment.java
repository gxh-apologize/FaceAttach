package cn.gxh.faceattach.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import butterknife.Bind;
import cn.gxh.faceattach.R;
import cn.gxh.faceattach.adapter.HistoryAdapter;
import cn.gxh.faceattach.base.Global;
import cn.gxh.faceattach.base.MyApp;
import cn.gxh.faceattach.bean.MobileRecordResponse;
import cn.gxh.faceattach.http.HttpUrl;
import cn.gxh.faceattach.http.HttpUtil;
import cn.gxh.faceattach.interfaces.NetworkRequestListener;

/**
 * 历史记录
 */
public class HistoryTabFragment extends BaseFragment {

    @Bind(R.id.rlv_fragment_tab_history)
    RecyclerView recyclerView;
    @Bind(R.id.srlt_fragment_tab_history)
    SwipeRefreshLayout swipeRefreshLayout;
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

        recyclerView.setLayoutManager(new LinearLayoutManager(mActivity));
        historyAdapter = new HistoryAdapter(null);
        historyAdapter.bindToRecyclerView(recyclerView);
    }

    @Override
    public void initListener() {

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                record();
            }
        });

    }

    @Override
    public void initData() {

    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        record();
    }

    private void record(){
        String json = "{\n" +
                "  \"Id\": \"" + MyApp.loginInfo.getId() + "\"\n" +
                "}";
        HttpUtil.upJsonByPost(HttpUrl.MOBILE_RECORD, this, json,
                new NetworkRequestListener() {
                    @Override
                    public void onNetWorkError() {
                        swipeRefreshLayout.setRefreshing(false);
                    }

                    @Override
                    public void onSuccess(String response) {
                        swipeRefreshLayout.setRefreshing(false);
                        MobileRecordResponse mobileRecordResponse = Global.getGson().fromJson(response, MobileRecordResponse.class);
                        if(mobileRecordResponse.getState()==1){
                            historyAdapter.setNewData(mobileRecordResponse.getData());
                            if(mobileRecordResponse.getData()==null
                                    ||mobileRecordResponse.getData().isEmpty()){
                                historyAdapter.setEmptyView(R.layout.view_data);
                            }

                        }else {
                            Global.showToast(mobileRecordResponse.getMessage());
                        }
                    }

                    @Override
                    public void onError(int code, String response) {
                        swipeRefreshLayout.setRefreshing(false);
                    }
                });
    }
}
