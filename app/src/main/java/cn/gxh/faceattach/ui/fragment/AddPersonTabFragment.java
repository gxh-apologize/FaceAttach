package cn.gxh.faceattach.ui.fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;

import java.util.List;

import butterknife.Bind;
import cn.gxh.faceattach.R;
import cn.gxh.faceattach.base.Global;
import cn.gxh.faceattach.bean.MobileAddSend;
import cn.gxh.faceattach.bean.MobileLoginSend;
import cn.gxh.faceattach.http.HttpUrl;
import cn.gxh.faceattach.http.HttpUtil;
import cn.gxh.faceattach.interfaces.NetworkRequestListener;
import cn.gxh.faceattach.util.ImageUtil;
import cn.gxh.faceattach.util.Logger;

/**
 * 添加人员
 */
public class AddPersonTabFragment extends BaseFragment {

    @Bind(R.id.btn_fragment_tab_add_person_picker)
    Button btnPicker;
    @Bind(R.id.iv_fragment_tab_add_person_pic)
    ImageView ivPic;
    @Bind(R.id.btn_fragment_tab_add_person_confirm)
    Button btnConfirm;
    @Bind(R.id.et_fragment_tab_add_person_name)
    EditText etName;
    private Bitmap bitmap;

    public static AddPersonTabFragment newInstance() {
        Bundle args = new Bundle();
        AddPersonTabFragment fragment = new AddPersonTabFragment();
        return fragment;
    }

    @Override
    public int getLayoutRes() {
        return R.layout.fragment_tab_add_person;
    }

    @Override
    public void initView(Bundle savedInstanceState) {

    }

    @Override
    public void initListener() {

        btnPicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                picturePicker();
            }
        });

        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirm();
            }
        });

    }

    @Override
    public void initData() {

    }

    @Override
    public void onSupportVisible() {
        super.onSupportVisible();
//        if(bitmap!=null && !bitmap.isRecycled()){
//            bitmap.recycle();
//            bitmap=null;
//        }
    }

    private void picturePicker(){
        PictureSelector.create(this)
                .openGallery(PictureMimeType.ofImage())
                .isCamera(true)
                .compress(true)
                .maxSelectNum(1)
                //Global.mContext.getExternalFilesDir("update").getAbsolutePath() ;
                .compressSavePath(Global.mContext.getExternalFilesDir("compress").getAbsolutePath())
                //.loadImageEngine(GlideEngine.createGlideEngine()) // 请参考Demo GlideEngine.java
                .forResult(PictureConfig.CHOOSE_REQUEST);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Logger.d("gxh",requestCode+";"+resultCode);
        if(requestCode==PictureConfig.CHOOSE_REQUEST){
            List<LocalMedia> list = PictureSelector.obtainMultipleResult(data);
            if(list==null || list.isEmpty()){
                //没有选择图片
                Logger.d("gxh","没有选择图片");
            }else {
                //选择了图片
                LocalMedia media = list.get(0);
                if(media.isCompressed()){
                    String compressPath = media.getCompressPath();
                    bitmap = BitmapFactory.decodeFile(compressPath);
                    ivPic.setImageBitmap(bitmap);
                }
            }
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(bitmap!=null && !bitmap.isRecycled()){
            bitmap.recycle();
            bitmap=null;
        }
    }

    private void confirm(){
        String name = etName.getText().toString().trim();
        if(TextUtils.isEmpty(name)){
            Global.showToast("请输入姓名");
            return;
        }

        if(bitmap==null){
            Global.showToast("请选择照片");
            return;
        }

        MobileAddSend mobileAddSend=new MobileAddSend();
        mobileAddSend.setPhoneNum("13521870263");
        mobileAddSend.setNickName(name);
        mobileAddSend.setId("bb808064-fad1-4abe-ba8b-4eb6a05c533b");
        mobileAddSend.setHeadUrlBase64(ImageUtil.bitmapoTBase64(bitmap));

        String json = Global.getGson().toJson(mobileAddSend);
        HttpUtil.upJsonByPost(HttpUrl.MOBILE_ADD, this, json,
                new NetworkRequestListener() {
                    @Override
                    public void onNetWorkError() {

                    }

                    @Override
                    public void onSuccess(String response) {
                        Logger.d("gxh",response);
                    }

                    @Override
                    public void onError(int code, String response) {

                    }
                });
    }


}

