package cn.gxh.faceattach.ui.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

import cn.gxh.faceattach.MainActivity;
import cn.gxh.faceattach.R;
import cn.gxh.faceattach.base.Global;
import cn.gxh.faceattach.base.MyApp;
import cn.gxh.faceattach.bean.LoginInfo;
import cn.gxh.faceattach.util.Logger;
import cn.gxh.faceattach.util.SharePrefUtil;

/**
 * 欢迎页面
 */
public class SplashActivity extends BaseActivity {

    private static final int REQUEST_CODE_QRCODE_PERMISSIONS = 1;
    private static final String[] NEEDED_PERMISSIONS = new String[]{
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE
    };


    @Override
    public int getLayoutRes() {
        return R.layout.activity_splash;
    }

    @Override
    public void initView(Bundle savedInstanceState) {

    }


    @Override
    public void initListener() {

    }

    @Override
    public void initData() {

        if (!checkPermissions(NEEDED_PERMISSIONS)) {
            ActivityCompat.requestPermissions(SplashActivity.this, NEEDED_PERMISSIONS, 2);
        } else {
            Logger.d("gxh", "isAllGranted 1");
            switchPage();
        }
    }

    private boolean checkPermissions(String[] neededPermissions) {
        if (neededPermissions == null || neededPermissions.length == 0) {
            return true;
        }
        boolean allGranted = true;
        for (String neededPermission : neededPermissions) {
            allGranted &= ContextCompat.checkSelfPermission(this.getApplicationContext(), neededPermission) == PackageManager.PERMISSION_GRANTED;
        }
        return allGranted;
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 2) {
            boolean isAllGranted = true;
            for (int grantResult : grantResults) {
                isAllGranted &= (grantResult == PackageManager.PERMISSION_GRANTED);
            }
            if (isAllGranted) {
                Logger.d("gxh", "isAllGranted 2");
                switchPage();
            } else {
                Global.showToast("权限拒绝");
                finish();
            }
        }
    }

    private void switchPage(){
        if(MyApp.loginInfo==null){
            startActivity(new Intent(this,LoginRootActivity.class));
            finish();
        }else {
            startActivity(new Intent(this,MainActivity.class));
            finish();
        }
    }
}
