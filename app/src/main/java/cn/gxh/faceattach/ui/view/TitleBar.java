package cn.gxh.faceattach.ui.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.luck.picture.lib.tools.Constant;

import cn.gxh.faceattach.R;
import cn.gxh.faceattach.interfaces.ViewClickListener;


/**
 * Created by GXH on 2018/2/6.
 */
public class TitleBar extends RelativeLayout {

    private int defaultTvColor = R.color.white;//文字默认颜色

    private String titleContent;
    private TextView tvContent;
    private ImageView ivBack;
    private int bgColor;
    private RelativeLayout rltRoot;
    private TextView tvRight;
    private boolean rightIsShow;
    private String rightContent;
    private TextView tvMidContent;
    private String midContent;
    private boolean isBackShow;


    public TitleBar(Context context) {
        super(context);
    }

    public TitleBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public TitleBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    public void init(Context context, AttributeSet attrs){

        TypedArray typedArray=context.obtainStyledAttributes(attrs, R.styleable.TitleBar);
        titleContent=typedArray.getString(R.styleable.TitleBar_TitleBarContent);
        bgColor=typedArray.getColor(R.styleable.TitleBar_TitleBarBgColor,getResources().getColor(defaultTvColor));
        rightIsShow = typedArray.getBoolean(R.styleable.TitleBar_TitleBarRightIsShow,false);
        rightContent = typedArray.getString(R.styleable.TitleBar_TitleBarRightContent);
        midContent = typedArray.getString(R.styleable.TitleBar_TitleBarMidContent);
        isBackShow = typedArray.getBoolean(R.styleable.TitleBar_TitleBarBackIsShow,false);

        RelativeLayout ll = (RelativeLayout) LayoutInflater.from(context).inflate(R.layout
                .title_bar, this);
        tvMidContent = ll.findViewById(R.id.tv_title_bar_mid_content);
        rltRoot = ll.findViewById(R.id.rlt_title_bar_root);
        tvContent = ll.findViewById(R.id.tv_title_bar_content);
        ivBack = ll.findViewById(R.id.iv_title_bar_back);
        tvRight = ll.findViewById(R.id.tv_title_bar_right);

        initData();
        initEvent();
    }

    private void initData(){
        tvContent.setText(titleContent);
        rltRoot.setBackgroundColor(bgColor);

        if(!TextUtils.isEmpty(midContent)){
            tvMidContent.setText(midContent);
        }

        if(rightIsShow){
            tvRight.setText(rightContent);
            tvRight.setVisibility(View.VISIBLE);
        }else {
            tvRight.setVisibility(View.GONE);
        }

        if(isBackShow){
            ivBack.setVisibility(View.VISIBLE);
        }else {
            ivBack.setVisibility(View.GONE);
        }

    }

    public void initEvent(){
        ivBack.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if(listener!=null){
                    listener.clickListener("",0);
                }
            }
        });

        tvRight.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if(listener!=null){
                    listener.clickListener("", 1);
                }
            }
        });
    }

    public void setRightIsShow(boolean isShow){
        if(isShow) {
            tvRight.setVisibility(VISIBLE);
        }else {
            tvRight.setVisibility(GONE);
        }
    }



    ViewClickListener listener;
    public void setOnTitleBarClickListener(ViewClickListener listener){
        this.listener=listener;
    }

}
