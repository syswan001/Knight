package com.sun.view;

import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.text.TextUtils;
import android.util.Log;

import com.sun.data.DataUtils;
import com.sun.inject.Injector;
import com.sun.util.Properties;

public abstract class BaseActionBarActivity extends ActionBarActivity implements BaseViewInterface {
	
	protected final String TAG = this.getClass().getSimpleName();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		if (Properties.isLog)Log.i(TAG, "onCreate");
		
		if (savedInstanceState != null) {
			DataUtils.onRestoreState(this, savedInstanceState);
		} else {
			initDataIntent(getIntent());
		}
		
		setContentView(getLayoutID());
		Injector.injectActivity(this);
		initView();
	}
	
	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		DataUtils.onSaveState(this, outState);
	}
	
	@Override
	protected void onRestart() {
		super.onRestart();
		if (Properties.isLog)Log.i(TAG, "onRestart");
	}
	
	@Override
	protected void onStart() {
		super.onStart();
		if (Properties.isLog)Log.i(TAG, "onStart");
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		if (Properties.isLog)Log.i(TAG, "onResume");
		onResumeView();
	}
	
	@Override
	protected void onPause() {
		super.onPause();
		if (Properties.isLog)Log.i(TAG, "onPause");
	}
	
	@Override
	protected void onStop() {
		super.onStop();
		if (Properties.isLog)Log.i(TAG, "onStop");
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		if (Properties.isLog)Log.i(TAG, "onDestroy");
	}

	@Override
	@Deprecated
	public void initDataBundle(Bundle bundle) {
	}
	
	@Override
	public void initDataIntent(Intent intent) {
		if (Properties.isLog)Log.i(TAG, "initDataIntent");
	}

	@Override
	public void initView() {
//		ActionBar actionBar = getSupportActionBar();
//		actionBar.setDisplayHomeAsUpEnabled(true);
//		actionBar.setHomeButtonEnabled(isHomeButtonEnabled());
//		actionBar.setIcon(com.sun.R.drawable.ic_launcher);
		if(!TextUtils.isEmpty(getActionBarTitle())) {
//			actionBar.setTitle(getActionBarTitle());
		}
	}
	
	@Override
	public boolean isHomeButtonEnabled() {
		return false;
	}

	@Override
	public void onResumeView() {
	}

	@Override
	public void handleMessage(Message msg) {
	}

}
