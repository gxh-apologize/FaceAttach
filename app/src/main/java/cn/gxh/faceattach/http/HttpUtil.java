package cn.gxh.faceattach.http;

import com.blankj.utilcode.util.NetworkUtils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

import java.util.Map;

import cn.gxh.faceattach.base.Global;
import cn.gxh.faceattach.base.MyApp;
import cn.gxh.faceattach.interfaces.NetworkRequestListener;

public class HttpUtil {

    /**
     * post请求
     *
     * @param url
     * @param tag
     * @param param
     * @param networkRequestListener
     */
    public static void post(String url, Object tag, Map<String, String> param,
                            final NetworkRequestListener networkRequestListener) {
        if (NetworkUtils.isConnected()) {
            OkGo.<String>post(url)
                    .tag(tag)
                    .params(param)
                    .execute(new StringCallback() {
                        @Override
                        public void onSuccess(Response<String> response) {
                            networkRequestListener.onSuccess(response.body());
                        }

                        //404/500/解析失败这三种情况会回调这个OkGo中的onError
                        @Override
                        public void onError(Response<String> response) {
                            super.onError(response);
                            networkRequestListener.onError(response.getRawResponse().code(),
                                    response.body());
                        }
                    });
        } else {
            Global.showToast("请检查网络是否连接");
            networkRequestListener.onNetWorkError();
        }
    }

    /**
     * 上传json
     * <p>
     * OkGo已经默认携带了请求头Content-Type: application/json;charset=utf-8
     * 不需要自己修改，也不支持自己修改
     *
     * @param url
     * @param tag
     * @param json
     * @param networkRequestListener
     */
    public static void upJsonByPost(String url, Object tag, String json,
                                    final NetworkRequestListener networkRequestListener) {

        if (NetworkUtils.isConnected()) {
            OkGo.<String>post(url)
                    .tag(tag)
                    .upJson(json)
                    .execute(new StringCallback() {
                        @Override
                        public void onSuccess(Response<String> response) {
                           networkRequestListener.onSuccess(response.body());
                        }

                        //404/>=500/解析失败这三种情况会回调这个OkGo中的onError
                        @Override
                        public void onError(Response<String> response) {
                            super.onError(response);
                            networkRequestListener.onError(response.getRawResponse().code(),
                                    response.body());
                        }
                    });

        } else {
            Global.showToast("请检查网络是否连接");
            networkRequestListener.onNetWorkError();
        }
    }

}
