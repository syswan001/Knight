/**
 * 
 */
package com.sun.util;

/**
 * @author
 *
 */
public class Properties {
	
	private Properties() {
	}
	public static final int currentVersion = 601;
	public static final String version = "601";
	public static final String platform = "360";
	
	private static final boolean isTestUrl = false;
	public static final String mUrl = "http://i.tappal.com/client/";
	public static final String mTestUrl = "http://i1.tappal.com/client/";
	
	public static final String apk_url = "http://www.tappal.com/tappal.400.apk";
	
	public static final boolean isLog = true;
	
	public static final String getHttpUrl() {
		return isTestUrl ? mTestUrl : mUrl;
	}
	
	/**
	平台   |  tag名称
	google play  |  google
	360手机助手  |  360
	豌豆  |  bean
	91 | 91
	新浪微博应用中心  |  sina
	百度手机助手  |  baidu
	安卓市场  |  anzuo
	安智市场  |  anzhi
	tencent | tencent
	MIUI	| miui
	木蚂 | mumayi
	Tappal | tappal*/

}
