package cn.gxh.faceattach.ui.fragment;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.blankj.utilcode.util.RegexUtils;

import butterknife.Bind;
import cn.gxh.faceattach.R;
import cn.gxh.faceattach.base.Global;
import cn.gxh.faceattach.bean.BaseBean;
import cn.gxh.faceattach.bean.MobileRegisterSend;
import cn.gxh.faceattach.http.HttpUrl;
import cn.gxh.faceattach.http.HttpUtil;
import cn.gxh.faceattach.interfaces.NetworkRequestListener;
import cn.gxh.faceattach.interfaces.ViewClickListener;
import cn.gxh.faceattach.ui.view.TitleBar;
import cn.gxh.faceattach.ui.view.XEditLayout;

public class RegisterFragment extends BaseFragment{

    @Bind(R.id.tv_fragment_register_save)
    TextView tvSave;
    @Bind(R.id.xet_fragment_register_password)
    XEditLayout xetPassword;
    @Bind(R.id.xet_fragment_register_phone)
    XEditLayout xetPhone;
    @Bind(R.id.tb_fragment_register)
    TitleBar titleBar;


    public static RegisterFragment newInstance() {
        Bundle args = new Bundle();
        RegisterFragment fragment = new RegisterFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public int getLayoutRes() {
        return R.layout.fragment_register;
    }

    @Override
    public void initView(Bundle savedInstanceState) {

        tvSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                register();
            }
        });
    }

    @Override
    public void initListener() {

        titleBar.setOnTitleBarClickListener(new ViewClickListener() {
            @Override
            public void clickListener(String msg, int type) {
                if(type==0){
                    pop();
                }
            }
        });
    }

    @Override
    public void initData() {

    }

    private void register(){

        final String phone = xetPhone.getEditStr();
        if(TextUtils.isEmpty(phone) || !RegexUtils.isMobileExact(phone)){
            Global.showToast("请输入正确的手机号");
            return;
        }

        String password = xetPassword.getEditStr();
        if(TextUtils.isEmpty(password)){
            Global.showToast("请输入密码");
            return;
        }


        MobileRegisterSend mobileRegisterSend=new MobileRegisterSend();
        mobileRegisterSend.setUserName(phone);
        mobileRegisterSend.setUserPass(password);
        mobileRegisterSend.setNickName("Mobile用户");
        String json = Global.getGson().toJson(mobileRegisterSend);

        HttpUtil.upJsonByPost(HttpUrl.MOBILE_REGISTER, this, json,
                new NetworkRequestListener() {
                    @Override
                    public void onNetWorkError() {

                    }

                    @Override
                    public void onSuccess(String response) {
                        BaseBean baseBean = Global.getGson().fromJson(response, BaseBean.class);
                        if(baseBean.getState()==1){
                            Global.showToast("注册成功,请重新登录");
                            pop();
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
