package cn.gxh.faceattach.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import java.util.List;
import cn.gxh.faceattach.R;
import cn.gxh.faceattach.bean.MobileRecordResponse;
import cn.gxh.faceattach.util.ImageLoaderUtils;


/**
 * 记录
 */
public class HistoryAdapter extends BaseQuickAdapter<MobileRecordResponse.DataBean,BaseViewHolder> {

    public HistoryAdapter(@Nullable List<MobileRecordResponse.DataBean> data) {
        super(R.layout.item_history, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, MobileRecordResponse.DataBean item) {

        helper.setText(R.id.tv_item_history_time,item.getRecordTimeStr());
        helper.setText(R.id.tv_item_history_name,item.getNickName());

        ImageView view = helper.getView(R.id.iv_item_history_pic);
        ImageLoaderUtils.displayRound(mContext,view,item.getPicPath());
    }
}
