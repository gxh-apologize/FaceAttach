package cn.gxh.faceattach.util;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import static com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade;


/**
 * Description : 图片加载工具类 使用glide框架封装
 */
public class ImageLoaderUtils {

    private static RequestOptions options = new RequestOptions()
            .centerCrop()
            //.placeholder(R.drawable.hai_cao)
            .optionalTransform(new GlideRoundTransformCenterCrop());

    //圆角
    public static void displayRound(Context context, ImageView imageView, String url) {
        if (imageView == null) {
            throw new IllegalArgumentException("argument error");
        }
        Glide.with(context).load(url).apply(options).transition(withCrossFade()).into(imageView);
    }

}
