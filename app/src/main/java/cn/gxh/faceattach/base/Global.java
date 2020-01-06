package cn.gxh.faceattach.base;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.blankj.utilcode.util.ToastUtils;
import com.google.gson.Gson;

import java.util.List;

/**
 * 封装一些常用操作，如：屏幕尺寸获取，主线程运行，toast
 * 
 * @author gxh
 */
public class Global {
	
	public static Context mContext;
	
	/** 屏幕的宽度 */
	public static int mScreenWidth;
	/** 屏幕的高度 */
	public static int mScreenHeight;
	/** 屏幕密度 */
	public static float mDensity;

	private static Gson gson;
	public static byte[] bytes=new byte[6];
	
	private static Handler mHandler = new Handler();
	
	public static void init(Context context) {
		mContext = context;
		bytes[0]= (byte) 0xE1;
		bytes[1]= (byte) 0xE2;
		bytes[2]= (byte) 0xA1;
		bytes[3]= (byte) 0xB1;
		bytes[4]= (byte) 0xEE;
		bytes[5]= (byte) 0xEF;
		initScreenSize();
	}

	private static final char[] HEX_CHAR = {'0', '1', '2', '3', '4', '5',
			'6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
	/**
	 * 方法二：
	 * byte[] to hex string
	 *
	 * @param bytes
	 * @return
	 */
	public static String bytesToHex2(byte[] bytes) {
		char[] buf = new char[bytes.length * 2];
		int index = 0;
		for(byte b : bytes) { // 利用位运算进行转换，可以看作方法一的变种
			buf[index++] = HEX_CHAR[b >>> 4 & 0xf];
			buf[index++] = HEX_CHAR[b & 0xf];
		}
		return new String(buf);
	}

	/** 初始化屏幕参数 */
	private static void initScreenSize() {
		DisplayMetrics dm = mContext.getResources().getDisplayMetrics();
		mScreenWidth = dm.widthPixels;
		mScreenHeight = dm.heightPixels;
		mDensity = dm.density;
	}
	
	public static int dp2px(int dp) {
		return (int) (mDensity * dp + 0.5f);
	}
	
	public static Handler getHandler() {
		return mHandler;
	}
	
	/** 判断当前是否在主线程运行 */
	public static boolean isMainThread() {
		return Looper.getMainLooper() == Looper.myLooper();
	}

	/**
	 * 在主线程执行
	 * @param runnable
	 */
	public static void runInUIThread(Runnable runnable) {
		if (isMainThread()) {
			runnable.run();
		} else {
			mHandler.post(runnable);
		}
	}
	
	private static Toast mToast;
	
	/**
	 * 显示文本，可以在子线程运行
	 * 
	 * @param text
	 */
	public static void showToast(final String text) {
		runInUIThread(new Runnable() {

			@Override
			public void run() {
				if (mToast  == null) {
					mToast = Toast.makeText(mContext, text, Toast.LENGTH_SHORT);
				}
				mToast.setText(text);
				mToast.show();
			}
		});
//		ToastUtils.setMsgTextSize(50);
//		ToastUtils.setBgColor(mContext.getResources().getColor(R.color.white));
//		ToastUtils.setGravity(1,0,0);
//		ToastUtils.setMsgColor(mContext.getResources().getColor(R.color.colorRed));
//		ToastUtils.showShort(text);
	}

	public static View inflate(int layout, ViewGroup parent) {
		return LayoutInflater.from(mContext).inflate(layout, parent, false);
	}

	public static Gson getGson(){
		if(gson==null){
			return new Gson();
		}
		return gson;
	}

	/**
	 * Service是否正在运行
	 * @param className
	 * @return
	 */
	public static boolean isServiceRunning(final String className) {
		ActivityManager activityManager = (ActivityManager) mContext.getSystemService(Context.ACTIVITY_SERVICE);
		List<ActivityManager.RunningServiceInfo> info = activityManager.getRunningServices(Integer.MAX_VALUE);
		if (info == null || info.size() == 0) return false;
		for (ActivityManager.RunningServiceInfo aInfo : info) {
			if (className.equals(aInfo.service.getClassName())) return true;
		}
		return false;
	}
}
















