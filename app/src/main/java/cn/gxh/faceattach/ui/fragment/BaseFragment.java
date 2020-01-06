package cn.gxh.faceattach.ui.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bulong.rudeness.RudenessScreenHelper;

import butterknife.ButterKnife;
import cn.gxh.faceattach.base.Global;
import cn.gxh.faceattach.interfaces.IUIOperation;
import me.yokeyword.fragmentation.SupportFragment;

public abstract class BaseFragment extends SupportFragment implements IUIOperation {


    public View root;
    public Activity mActivity;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity = getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        RudenessScreenHelper.resetDensity(getContext(), 720);
        if (root == null) {
            root = inflater.inflate(getLayoutRes(), null);

            ButterKnife.bind(this, root);
            initView(savedInstanceState);
            initListener();
            initData();
        }
        return root;
    }

    public void showToast(String msg) {
        Global.showToast(msg);
    }

    /**
     * 查找子控件，可以省略强转操作
     */
    public <T> T findView(int id) {
        T view = (T) root.findViewById(id);
        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }


//    LoadingDailog mLoadingDailog;
//    public void showLoadingDailog() {
//        // 使用的时候只需要new出来就行，然后指定我们自定义的style
//        if (mLoadingDailog == null) {
//            LoadingDailog.Builder loadBuilder = new LoadingDailog.Builder(getActivity())
//                    .setMessage("加载中...")
//                    .setCancelable(false)
//                    .setCancelOutside(false);
//            mLoadingDailog = loadBuilder.create();
//        }
//        // 显示的时候需要show()出来
//        mLoadingDailog.show();
//    }
//
//    public void closeLoadingDailog() {
//        if (mLoadingDailog != null) {
//            mLoadingDailog.dismiss();
//            mLoadingDailog = null;
//        }
//    }

}
