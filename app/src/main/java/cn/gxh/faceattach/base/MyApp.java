package cn.gxh.faceattach.base;

import android.app.Application;

import com.bulong.rudeness.RudenessScreenHelper;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheMode;

import java.util.concurrent.TimeUnit;

import cn.gxh.faceattach.bean.LoginInfo;
import cn.gxh.faceattach.util.SharePrefUtil;
import okhttp3.OkHttpClient;

public class MyApp extends Application {
    //设计图标注的宽度
    public static int designWidth = 720;
    public static LoginInfo loginInfo;

    @Override
    public void onCreate() {
        super.onCreate();
        //初始化适配类
        new RudenessScreenHelper(this, designWidth).activate();
        Global.init(this);
        SharePrefUtil.getInstance().initSP(this);

        loginInfo= (LoginInfo) SharePrefUtil.getInstance().getObj("login");

        initOkGo();
    }

    private void initOkGo() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        //全局的读取超时时间
        builder.readTimeout(OkGo.DEFAULT_MILLISECONDS, TimeUnit.MILLISECONDS);
        //全局的写入超时时间
        builder.writeTimeout(OkGo.DEFAULT_MILLISECONDS, TimeUnit.MILLISECONDS);
        //全局的连接超时时间
        builder.connectTimeout(OkGo.DEFAULT_MILLISECONDS, TimeUnit.MILLISECONDS);
//        HttpsUtils.SSLParams sslParams1 = HttpsUtils.getSslSocketFactory();
//        builder.sslSocketFactory(sslParams1.sSLSocketFactory, sslParams1.trustManager);
        //配置https的域名匹配规则，详细看demo的初始化介绍，不需要就不要加入，使用不当会导致https握手失败
        //builder.hostnameVerifier(new SafeHostnameVerifier());


//        HttpHeaders headers = new HttpHeaders();
//        HttpParams params = new HttpParams();

        OkGo.getInstance().init(this)                       //必须调用初始化
                .setOkHttpClient(builder.build())               //建议设置OkHttpClient，不设置将使用默认的
                .setCacheMode(CacheMode.NO_CACHE)//全局统一缓存模式，默认不使用缓存，可以不传
                //.setCacheTime(CacheEntity.CACHE_NEVER_EXPIRE)   //全局统一缓存时间，默认永不过期，可以不传
                .setRetryCount(3);                             //全局统一超时重连次数，默认为三次，那么最差的情况会请求4次(一次原始请求，三次重连请求)，不需要可以设置为0
        //.addCommonHeaders(headers)                      //全局公共头
        //.addCommonParams(params);                       //全局公共参数

    }
}
