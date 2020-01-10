package cn.gxh.faceattach.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import java.util.List;

import cn.gxh.faceattach.R;


/**
 * 设备管理
 */
public class HistoryAdapter extends BaseQuickAdapter<String,BaseViewHolder> {

    public HistoryAdapter(@Nullable List<String> data) {
        super(R.layout.item_history, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {

    }
}
