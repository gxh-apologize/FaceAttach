package cn.gxh.faceattach.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.blankj.utilcode.util.RegexUtils;

import butterknife.Bind;
import cn.gxh.faceattach.MainActivity;
import cn.gxh.faceattach.R;
import cn.gxh.faceattach.base.Global;
import cn.gxh.faceattach.base.MyApp;
import cn.gxh.faceattach.bean.LoginInfo;
import cn.gxh.faceattach.bean.MobileLoginResponse;
import cn.gxh.faceattach.bean.MobileLoginSend;
import cn.gxh.faceattach.http.HttpUrl;
import cn.gxh.faceattach.http.HttpUtil;
import cn.gxh.faceattach.interfaces.NetworkRequestListener;
import cn.gxh.faceattach.ui.view.XEditLayout;
import cn.gxh.faceattach.util.Logger;
import cn.gxh.faceattach.util.SharePrefUtil;

public class LoginFragment extends BaseFragment {

    @Bind(R.id.xet_fragment_login_phone)
    XEditLayout xetPhone;
    @Bind(R.id.xet_fragment_login_password)
    XEditLayout xetPassword;
    @Bind(R.id.tv_fragment_login_save)
    TextView tvSave;
    @Bind(R.id.tv_fragment_login_forget_register)
    TextView tvRegister;

    public static LoginFragment newInstance() {
        Bundle args = new Bundle();
        LoginFragment fragment = new LoginFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getLayoutRes() {
        return R.layout.fragment_login;
    }

    @Override
    public void initView(Bundle savedInstanceState) {

    }

    @Override
    public void initListener() {
        tvSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });

        tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                start(RegisterFragment.newInstance());
            }
        });
    }

    @Override
    public void initData() {

    }

    private void login(){

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

        MobileLoginSend mobileLoginSend=new MobileLoginSend();
        mobileLoginSend.setUserName(phone);
        mobileLoginSend.setUserPass(password);
        String json = Global.getGson().toJson(mobileLoginSend);
        HttpUtil.upJsonByPost(HttpUrl.MOBILE_LOGIN, this, json,
                new NetworkRequestListener() {
                    @Override
                    public void onNetWorkError() {

                    }

                    @Override
                    public void onSuccess(String response) {
                        Logger.d("gxh","onSuccess:"+response);
                        MobileLoginResponse mobileLoginResponse = Global.getGson().fromJson(response, MobileLoginResponse.class);
                        if(mobileLoginResponse.getState()==1){

                            //保存到本地
                            LoginInfo loginInfo=new LoginInfo();
                            loginInfo.setId(mobileLoginResponse.getData().getId());
                            loginInfo.setUserName(phone);
                            loginInfo.setNickName(mobileLoginResponse.getData().getNickName());
                            SharePrefUtil.getInstance().saveObj("login",loginInfo);

                            MyApp.loginInfo=loginInfo;

                            startActivity(new Intent(mActivity,MainActivity.class));
                            mActivity.finish();
                        }else {
                            Global.showToast(mobileLoginResponse.getMessage());
                        }
                    }

                    @Override
                    public void onError(int code, String response) {

                    }
                });
    }
}
