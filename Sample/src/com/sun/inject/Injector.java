/*
 * Copyright (C) 2011 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.sun.inject;

import java.lang.reflect.Field;

import android.app.Activity;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.View;

/**
 * Very lightweight form of injection, inspired by RoboGuice, for injecting common ui elements.
 * <p>
 * Usage is very simple. In your Activity, define some fields as follows:
 * 
 * <pre class="code">
 * &#064;InjectView(R.id.fetch_button)
 * private Button mFetchButton;
 * &#064;InjectView(R.id.submit_button)
 * private Button mSubmitButton;
 * &#064;InjectView(R.id.main_view)
 * private TextView mTextView;
 * </pre>
 * <p>
 * Then, inside your Activity's onCreate() method, perform the injection like this:
 * 
 * <pre class="code">
 * setContentView(R.layout.main_layout);
 * Injector.get(this).inject();
 * </pre>
 * <p>
 * See the {@link #inject()} method for full details of how it works. Note that the fields are fetched and assigned at the time you call {@link #inject()},
 * consequently you should not do this until after you've called the setContentView() method.
 */
public final class Injector {

	static final String TAG = "Injector";
	
	public static final void saveBundle() {
		
	}
	
	public static final void putBundleValue(Bundle outState, String name, Object value) {
		if(value instanceof Boolean) {
			outState.putBoolean(name, (Boolean)value);
		} else if(value instanceof String) {
			outState.putString(name, (String)value);
		} else if(value instanceof Integer) {
			outState.putInt(name, (Integer)value);
		} else if(value instanceof Parcelable) {
			outState.putParcelable(name, (Parcelable)value);
		} else if(value instanceof Float) {
			outState.putFloat(name, (Float)value);
		} else if(value instanceof Double) {
			outState.putDouble(name, (Double)value);
		}
	}
	
	/**
	 * Injects all fields that are marked with the {@link InjectView} annotation.
	 * <p>
	 * For each field marked with the InjectView annotation, a call to {@link Activity#findViewById(int)} will be made, passing in the resource id stored in the
	 * value() method of the InjectView annotation as the int parameter, and the result of this call will be assigned to the field.
	 * 
	 * @throws IllegalStateException
	 *             if injection fails, common causes being that you have used an invalid id value, or you haven't called setContentView() on your Activity.
	 */
	public static final void injectActivity(Activity activity) {
		if(activity == null) return;
		Field[] fields = activity.getClass().getDeclaredFields();
		if(fields == null || fields.length == 0) return;
		try {
			for (Field field : fields) {
				if (field.isAnnotationPresent(InjectView.class)) {
					injectViewByActivity(field, activity);
				} else if (field.isAnnotationPresent(InjectFragment.class)) {
					injectFragmentByActivity(field, activity);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static final void injectFragment(Fragment frgment, View view) {
		if(frgment == null || view == null) return;
		Field[] fields = frgment.getClass().getDeclaredFields();
		if(fields == null || fields.length == 0) return;
		try {
			for (Field field : fields) {
				if (field.isAnnotationPresent(InjectView.class)) {
					injectViewByFragment(field, frgment, view);
				} else if (field.isAnnotationPresent(InjectFragment.class)) {
					injectFragmentByFragment(field, frgment);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static final void injectViewByActivity(Field field, Activity act) throws IllegalArgumentException, IllegalAccessException {
		InjectView mInjectView = field.getAnnotation(InjectView.class);
		int idValue = mInjectView.value();
//		Log.e(TAG, "\t\tidValue :" + idValue + " " + field);
		field.setAccessible(true);
		Object injectObject = act.findViewById(idValue);
//		Log.e(TAG, "\t\tinjectObject :" + injectObject);
		if (injectObject == null) {
			throw new IllegalStateException("findViewById(" + idValue + ") gave null for " + field + ", can't inject");
		}
		field.set(act, injectObject);
		field.setAccessible(false);
	}

	private static final void injectFragmentByActivity(Field field, Activity act) throws IllegalArgumentException, IllegalAccessException {
		InjectFragment mInjectView = field.getAnnotation(InjectFragment.class);
		int idValue = mInjectView.value();
//		Log.e(TAG, "\t\tidValue :" + idValue + " " + field);
		// int type = mInjectView.type();
		field.setAccessible(true);
		Object injectObject = ((FragmentActivity) act).getSupportFragmentManager().findFragmentById(idValue);
//		Log.e(TAG, "\t\tinjectObject :" + injectObject);
		if (injectObject == null) {
			throw new IllegalStateException("findViewById(" + idValue + ") gave null for " + field + ", can't inject");
		}
		field.set(act, injectObject);
		field.setAccessible(false);
	}

	private static final void injectViewByFragment(Field field, Fragment frgment, View view) throws IllegalArgumentException, IllegalAccessException {
		// Class<?> fieldType = field.getType();
		InjectView mInjectView = field.getAnnotation(InjectView.class);
		int idValue = mInjectView.value();
//		Log.e(TAG, "\t\tidValue :" + idValue + " " + field);
		// int type = mInjectView.type();
		field.setAccessible(true);
		Object injectObject = view.findViewById(idValue);
//		Log.e(TAG, "\t\tinjectObject :" + injectObject);
		// if (injectObject == null) {
		// return;
		// }
		// Object injectedValue = fieldType.cast(injectObject);
		if (injectObject == null) {
			throw new IllegalStateException("findViewById(" + idValue + ") gave null for " + field + ", can't inject");
		}
		field.set(frgment, injectObject);
		field.setAccessible(false);
	}

	private static final void injectFragmentByFragment(Field field, Fragment frgment) throws IllegalArgumentException, IllegalAccessException {
		InjectFragment mInjectView = field.getAnnotation(InjectFragment.class);
		int idValue = mInjectView.value();
//		Log.e(TAG, "\t\tidValue :" + idValue + " " + field);
		// int type = mInjectView.type();
		field.setAccessible(true);
		Object injectObject = frgment.getFragmentManager().findFragmentById(idValue);
//		Log.e(TAG, "\t\tinjectObject :" + injectObject);
		if (injectObject == null) {
			throw new IllegalStateException("findViewById(" + idValue + ") gave null for " + field + ", can't inject");
		}
		field.set(frgment, injectObject);
		field.setAccessible(false);
	}

}
