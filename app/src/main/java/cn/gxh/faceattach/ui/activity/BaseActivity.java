package cn.gxh.faceattach.ui.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;
import cn.gxh.faceattach.interfaces.IUIOperation;
import me.yokeyword.fragmentation.SupportActivity;


/**
 * Created by GXH on 2018/1/11.
 */
public abstract class BaseActivity extends SupportActivity implements IUIOperation {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutRes());
        ButterKnife.bind(this);
        initView(savedInstanceState);
        initData();
        initListener();
      // RefWatcher refWatcher = MyApp.getRefWatcher(this);
      // refWatcher.watch(this);
    }


//    LoadingDialog mLoadingDailog;
//    public void showLoadingDailog(){
//        // 使用的时候只需要new出来就行，然后指定我们自定义的style
//        if(mLoadingDailog==null){
//            mLoadingDailog = new LoadingDialog(this, R.style.progress_dialog);
//        }
//        // 显示的时候需要show()出来
//        mLoadingDailog.show();
//    }
//
//    public void closeLoadingDailog(){
//        if(mLoadingDailog != null){
//            mLoadingDailog.dismiss();
//            mLoadingDailog = null;
//        }
//    }
}
