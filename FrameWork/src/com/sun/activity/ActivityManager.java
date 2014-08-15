package com.sun.activity;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

public class ActivityManager {
	
	private List<BaseViewInterface> activity_list = new ArrayList<BaseViewInterface>();
	
	private MyHandler handler = null;
	
	private ActivityManager() {
		handler = new MyHandler();
	}
	
	private static ActivityManager instance;
	
	public void addActivity(BaseViewInterface activity) {
		activity_list.add(activity);
		handler.setActivity(activity);
	}
	
	public void removeActivity(BaseViewInterface activity) {
		activity_list.remove(activity);
		handler.removeActivity();
	}
	
	public static ActivityManager instance() {
		if(instance == null) {
			instance = new ActivityManager();
		}
		return instance;
	}
	
	public static void clear() {
		if(instance == null)return;
		instance.activity_list.clear();
		instance = null;
	}
	
	public boolean sendMessage(int what, Object obj) {
		Message msg = new Message();
		msg.what = what;
		msg.obj = obj;
		return handler.sendMessage(msg);
	}
	
	public boolean sendEmptyMessage(int what) {
		return handler.sendEmptyMessage(what);
	}
	
	public boolean sendEmptyMessageAtTime(int what, long uptimeMillis) {
		return handler.sendEmptyMessageAtTime(what, uptimeMillis);
	}
	
	public boolean sendEmptyMessageDelayed(int what, long delayMillis) {
		return handler.sendEmptyMessageDelayed(what, delayMillis);
	}
	
	public void removeMessages(int what) {
		if (handler == null) return;
		handler.removeMessages(what);
	}

	public void removeCallbacksAndMessages(Object token) {
		if (handler != null) {
			handler.removeCallbacksAndMessages(null);
		}
	}
	
	static class MyHandler extends Handler {

		public MyHandler() {
		}

		public MyHandler(Callback callback) {
			super(callback);
		}

		public MyHandler(Looper looper) {
			super(looper);
		}

		public MyHandler(Looper looper, Callback callback) {
			super(looper, callback);
		}
		
		private WeakReference<BaseViewInterface> base = null;
		
		public void setActivity(BaseViewInterface base) {
			this.base = new WeakReference<BaseViewInterface>(base);
		}
		
		public void removeActivity() {
			if(base == null) return;
			base.clear();
			base = null;
		}
		
		public void handleMessage(android.os.Message msg) {
			if(base == null || base.get() == null) {
				return;
			}
			base.get().handleMessage(msg);
		};

	}

}
