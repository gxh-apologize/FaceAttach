package cn.gxh.faceattach.interfaces;

/**
 * 网络请求回调
 */
public interface NetworkRequestListener {

    //没网
    void onNetWorkError();
    void onSuccess(String response);
    void onError(int code, String response);

}
