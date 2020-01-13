package cn.gxh.faceattach.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import cn.gxh.faceattach.R;
import cn.gxh.faceattach.bean.MobileDevicesResponse;


/**
 * 设备管理
 */
public class DeviceAdapter extends BaseQuickAdapter<MobileDevicesResponse.DataBean,BaseViewHolder> {

    public DeviceAdapter(@Nullable List<MobileDevicesResponse.DataBean> data) {
        super(R.layout.item_device, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, MobileDevicesResponse.DataBean item) {
        helper.setText(R.id.tv_item_device_name,item.getMobileName())
            .addOnClickListener(R.id.tv_item_device_open);
    }
}
