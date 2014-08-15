/**
 * 
 */
package com.sun.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;

/**
 * @author D&K
 * @date 2014-8-8
 * @TODO
 */
public interface BaseViewInterface {
	/******************************
	 * layout xml文件
	 * ****************************/
	public int getLayoutID();
	/******************************
	 * ActionBar title
	 * ****************************/
	public String getActionBarTitle();
	/******************************
	 * ActionBar Icon
	 * ****************************/
	public int getActionBarIcon();
	
	public boolean isHomeButtonEnabled();
	/******************************
	 * 接受传入数据
	 * {@link Activity}
	 * ****************************/
	public void initDataIntent(Intent intent);
	/******************************
	 * 接受传入数据
	 * {@link Fragment}
	 * ****************************/
	public void initDataBundle(Bundle bundle);
	/******************************
	 * 初始化View
	 * ****************************/
	public void initView();
	/******************************
	 * 添加页面数据
	 * ****************************/
	public void onResumeView();
	
	public void handleMessage(android.os.Message msg);
	
}
