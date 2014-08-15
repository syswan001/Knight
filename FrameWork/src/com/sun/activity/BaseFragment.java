package com.sun.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.sun.inject.Injector;
import com.sun.util.Properties;

public abstract class BaseFragment extends Fragment implements BaseViewInterface {

	protected final String TAG = this.getClass().getSimpleName();
	
	@Override
	public String getActionBarTitle() {
		return null;
	}

	@Override
	public int getActionBarIcon() {
		return 0;
	}

	@Override
	public boolean isHomeButtonEnabled() {
		return false;
	}

	@Override
	@Deprecated
	public void initDataIntent(Intent intent) {
	}

	@Override
	public void initDataBundle(Bundle bundle) {
	}

	@Override
	public void initView() {
	}

	@Override
	public void onResumeView() {
	}

	@Override
	public void handleMessage(Message msg) {
	}
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		if (Properties.isLog)Log.i(TAG, "onAttach");
	}
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (Properties.isLog)Log.i(TAG, "onCreate");
		if(savedInstanceState != null) {
			initDataBundle(savedInstanceState);
		}
	}
	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		if (Properties.isLog)Log.i(TAG, "onSaveInstanceState");
	}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		if (Properties.isLog)Log.i(TAG, "onCreateView");
		return inflater.inflate(getLayoutID(), container, false);
	}
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		if (Properties.isLog)Log.i(TAG, "onActivityCreated");
		Injector.injectFragment(this, getView());
		initView();
	}
	@Override
	public void onViewStateRestored(Bundle savedInstanceState) {
		super.onViewStateRestored(savedInstanceState);
		if (Properties.isLog)Log.i(TAG, "onViewStateRestored");
	}
	@Override
	public void onDetach() {
		super.onDetach();
		if (Properties.isLog)Log.i(TAG, "onDetach");
	}
	@Override
	public void onResume() {
		super.onResume();
		if (Properties.isLog)Log.i(TAG, "onResume");
		onResumeView();
	}
	@Override
	public void onStart() {
		super.onStart();
		if (Properties.isLog)Log.i(TAG, "onStart");
	}
	@Override
	public void onPause() {
		super.onPause();
		if (Properties.isLog)Log.i(TAG, "onPause");
	}
	@Override
	public void onStop() {
		super.onStop();
		if (Properties.isLog)Log.i(TAG, "onStop");
	}
	@Override
	public void onDestroy() {
		super.onDestroy();
		if (Properties.isLog)Log.i(TAG, "onDestroy");
	}
	@Override
	public void onDestroyView() {
		super.onDestroyView();
		if (Properties.isLog)Log.i(TAG, "onDestroyView");
	}
	
	@Override
	public void onDestroyOptionsMenu() {
		super.onDestroyOptionsMenu();
		if (Properties.isLog)Log.i(TAG, "onDestroyOptionsMenu");
	}
	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		if (Properties.isLog)Log.i(TAG, "onConfigurationChanged");
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (Properties.isLog)Log.i(TAG, "onOptionsItemSelected");
		return super.onOptionsItemSelected(item);
	}
	@Override
	public void onOptionsMenuClosed(Menu menu) {
		super.onOptionsMenuClosed(menu);
		if (Properties.isLog)Log.i(TAG, "onOptionsMenuClosed");
	}
	

}
