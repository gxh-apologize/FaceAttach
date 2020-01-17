package cn.gxh.faceattach.ui.fragment;

import android.media.midi.MidiOutputPort;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;

import butterknife.Bind;
import cn.gxh.faceattach.R;
import cn.gxh.faceattach.adapter.DeviceAdapter;
import cn.gxh.faceattach.base.Global;
import cn.gxh.faceattach.base.MyApp;
import cn.gxh.faceattach.bean.BaseBean;
import cn.gxh.faceattach.bean.MobileDevicesResponse;
import cn.gxh.faceattach.bean.MobileSetDoorStatusSend;
import cn.gxh.faceattach.http.HttpUrl;
import cn.gxh.faceattach.http.HttpUtil;
import cn.gxh.faceattach.interfaces.NetworkRequestListener;
import cn.gxh.faceattach.util.Logger;

/**
 * 控制
 */
public class ControlTabFragment extends BaseFragment {

    @Bind(R.id.rlv_fragment_tab_control)
    RecyclerView recyclerView;
    @Bind(R.id.srlt_fragment_tab_control)
    SwipeRefreshLayout swipeRefreshLayout;
    private DeviceAdapter deviceAdapter;

    public static ControlTabFragment newInstance() {
        Bundle args = new Bundle();
        ControlTabFragment fragment = new ControlTabFragment();
        return fragment;
    }

    @Override
    public int getLayoutRes() {
        return R.layout.fragment_tab_control;
    }

    @Override
    public void initView(Bundle savedInstanceState) {

        recyclerView.setLayoutManager(new LinearLayoutManager(mActivity));
        deviceAdapter = new DeviceAdapter(null);
        deviceAdapter.bindToRecyclerView(recyclerView);

    }

    @Override
    public void initListener() {

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getDevices();
            }
        });

        deviceAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                Logger.d("gxh","onItemChildClick");
                setDoorStatus(position);
            }
        });

    }

    @Override
    public void initData() {

    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        getDevices();
    }

    private void getDevices(){
        //String id="bb808064-fad1-4abe-ba8b-4eb6a05c533b";
        String json = "{\n" +
                "  \"Id\": \"" + MyApp.loginInfo.getId() + "\"\n" +
                "}";

        HttpUtil.upJsonByPost(HttpUrl.MOBILE_DEVICES, this, json,
                new NetworkRequestListener() {
                    @Override
                    public void onNetWorkError() {
                        swipeRefreshLayout.setRefreshing(false);
                    }

                    @Override
                    public void onSuccess(String response) {
                        swipeRefreshLayout.setRefreshing(false);
                        MobileDevicesResponse mobileDevicesResponse = Global.getGson().fromJson(response, MobileDevicesResponse.class);
                        if(mobileDevicesResponse.getState()==1){
                            deviceAdapter.setNewData(mobileDevicesResponse.getData());
                            if(mobileDevicesResponse.getData()==null ||
                                    mobileDevicesResponse.getData().isEmpty()){
                                deviceAdapter.setEmptyView(R.layout.view_data);
                            }
                        }else {
                            Global.showToast(mobileDevicesResponse.getMessage());
                        }
                    }

                    @Override
                    public void onError(int code, String response) {
                        swipeRefreshLayout.setRefreshing(false);
                    }
                });
    }

    private void setDoorStatus(int position){
        MobileSetDoorStatusSend mobileSetDoorStatusSend=new MobileSetDoorStatusSend();
        mobileSetDoorStatusSend.setDoorStatus(1);
        mobileSetDoorStatusSend.setMobileId(deviceAdapter.getData().get(position).getMobileId());
        String json = Global.getGson().toJson(mobileSetDoorStatusSend);
        HttpUtil.upJsonByPost(HttpUrl.MOBILE_SET_DOOR_STATUS, this, json,
                new  NetworkRequestListener() {
                     @Override
                    public void onNetWorkError() {

                    }

                    @Override
                    public void onSuccess(String response) {
                        Logger.d("gxh",response);
                        BaseBean baseBean = Global.getGson().fromJson(response, BaseBean.class);
                        if(baseBean.getState()==1){
                            Global.showToast("发送指令成功");
                        }else {
                            Global.showToast(baseBean.getMessage());
                        }
                    }

                    @Override
                    public void onError(int code, String response) {

                    }
                });
    }
}

