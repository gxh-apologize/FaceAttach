package cn.gxh.faceattach.ui.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.InputType;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.PasswordTransformationMethod;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import cn.gxh.faceattach.R;
import cn.gxh.faceattach.interfaces.ViewClickListener;
import cn.gxh.faceattach.util.Logger;


/**
 * 扩展的编辑框, 输入内容后，右侧有清除小图标，点击可清空编辑框输入内容
 *
 * @author gxh
 */
public class XEditLayout extends LinearLayout {


    private EditText editText;
    private ImageView ivDel;
    private String hitStr;
    private int color;
    private int hitColor;
    private int size;
    private int type;
    private boolean isSearch;

    public XEditLayout(Context context) {
        super(context);
    }

    public XEditLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public XEditLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.XEditLayout);

        hitStr = typedArray.getString(R.styleable.XEditLayout_xeditLayoutHitStr);
        color = typedArray.getColor(R.styleable.XEditLayout_xeditLayoutColor, getResources().getColor(R.color.black_1));
        hitColor = typedArray.getColor(R.styleable.XEditLayout_xeditLayoutHitColor, getResources().getColor(R.color.gray_9));
        size = typedArray.getDimensionPixelSize(R.styleable.XEditLayout_xeditLayoutStrSize, 24);
        type = typedArray.getInt(R.styleable.XEditLayout_xeditLayoutType, 3);
        isSearch = typedArray.getBoolean(R.styleable.XEditLayout_xeditLayoutIsSearch, false);

        LinearLayout llt = (LinearLayout) LayoutInflater.from(context).inflate(R.layout.view_xedit_layout, this);
        editText = llt.findViewById(R.id.et_view_xedit_layout);
        ivDel = llt.findViewById(R.id.iv_view_xedit_layout);

        initData();
        initEvent();
    }

    private void initData() {

        if(!TextUtils.isEmpty(hitStr)){
            editText.setHint(hitStr);
        }

        editText.setTextColor(color);
        editText.setHintTextColor(hitColor);
        editText.setTextSize(TypedValue.COMPLEX_UNIT_PT, size);

        switch (type){
            case 1:
                editText.setInputType(InputType.TYPE_CLASS_NUMBER);
                break;
            case 2:
                setPasswordStyle();
                break;
            case 3:
                break;
        }

        if(isSearch) {
            editText.setImeOptions(EditorInfo.IME_ACTION_SEARCH);
        }

    }

    private void initEvent() {
        ivDel.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                editText.getText().clear();
            }
        });

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                Logger.d("gxh", "beforeTextChanged" + ":" + s.toString());
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Logger.d("gxh", "onTextChanged" + ":" + s.toString());
                if (TextUtils.isEmpty(s.toString().trim())) {
                    ivDel.setVisibility(View.GONE);
                } else {
                    ivDel.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                Logger.d("gxh", "afterTextChanged" + ":" + s.toString());
            }
        });


        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {

                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    if (viewClickListener != null) {
                        viewClickListener.clickListener(v.getText().toString().trim(), 0);
                    }
                }
                return true;
            }
        });
    }

    ViewClickListener viewClickListener;
    public void setViewClickListener(ViewClickListener viewClickListener) {
        this.viewClickListener = viewClickListener;
    }

    public String getEditStr() {
        return editText.getText().toString().trim();
    }

    public void setPasswordStyle() {
        editText.setTransformationMethod(PasswordTransformationMethod.getInstance());
    }
}