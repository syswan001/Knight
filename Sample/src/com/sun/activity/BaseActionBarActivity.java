package com.sun.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.text.TextUtils;

import com.sun.data.DataUtils;
import com.sun.inject.Injector;
import com.sun.slidingmenu.R;

public abstract class BaseActionBarActivity extends ActionBarActivity implements BaseViewInterface {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (savedInstanceState != null) {
			initDataIntent(null);
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
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		onResumeView();
	}
	
	@Override
	protected void onPause() {
		super.onPause();
	}
	
	@Override
	protected void onStop() {
		super.onStop();
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
	}

	@Override
	@Deprecated
	public void initDataBundle(Bundle bundle) {
	}
	
	@Override
	public void initDataIntent(Intent intent) {
	}

	@Override
	public void initView() {
		ActionBar actionBar = getSupportActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);
		actionBar.setHomeButtonEnabled(isHomeButtonEnabled());
		actionBar.setIcon(R.drawable.ic_launcher);
		if(!TextUtils.isEmpty(getActionBarTitle())) {
			actionBar.setTitle(getActionBarTitle());
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
