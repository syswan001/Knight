package com.sun.data;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;

import android.os.Bundle;
import android.os.Parcelable;

import com.sun.inject.InjectData;

public class DataUtils {
	
	private DataUtils() {}
	
	/************************************
	 * 通过反射（InjectData）实现
	 * 保存Activity/Fragment对象中常用变量
	 * 由于在父类实现
	 * 数据必须是public和protected，建议使用protected
	 * {@link onRestoreState}
	 * *********************************/
	public static final void onSaveState(Object obj, Bundle outState) {
		onSaveState(obj.getClass().getDeclaredFields(), obj, outState);
		Class<? extends Object> _supperclass = obj.getClass().getSuperclass();
		if(_supperclass != null) {
			onSaveState(_supperclass.getDeclaredFields(), obj, outState);
		}
	}
	/************************************
	 * 通过反射（InjectData）实现
	 * 恢复Activity/Fragment对象中常用变量
	 * 数据必须是public和protected，建议使用protected
	 * {@link onSaveState}
	 * *********************************/
	public static final void onRestoreState(Object obj, Bundle savedInstanceState) {
		onRestore(obj.getClass().getDeclaredFields(), obj, savedInstanceState);
		Class<? extends Object> _supperclass = obj.getClass().getSuperclass();
		if(_supperclass != null) {
			onRestore(_supperclass.getDeclaredFields(), obj, savedInstanceState);
		}
	}
	
	private static final void onSaveState(Field[] fields, Object obj, Bundle outsave) {
		if(fields == null || fields.length == 0) return;
		try {
			for (Field field : fields) {
				if(Modifier.isPublic(field.getModifiers()) || Modifier.isProtected(field.getModifiers())) {
					if(field.isAnnotationPresent(InjectData.class)) {
						Object value = field.get(obj);
						if(value instanceof String) {
							outsave.putString(field.getName(), (String)value);
						} else if(value instanceof Integer) {
							outsave.putInt(field.getName(), (Integer)value);
						} else if(value instanceof Parcelable) {
							outsave.putParcelable(field.getName(), (Parcelable)value);
						} else if(value instanceof Float) {
							outsave.putFloat(field.getName(), (Float)value);
						} else if(value instanceof Double) {
							outsave.putDouble(field.getName(), (Double)value);
						} else if(value instanceof ArrayList<?>) {
							outsave.putParcelableArrayList(field.getName(), (ArrayList<Parcelable>)value);
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static final void onRestore(Field[] fields, Object obj, Bundle savedInstanceState) {
		if(fields == null || fields.length == 0) return;
		try {
			for (Field field : fields) {
				if(Modifier.isPublic(field.getModifiers()) || Modifier.isProtected(field.getModifiers())) {
					if(field.isAnnotationPresent(InjectData.class)) {
						Object value = savedInstanceState.get(field.getName());
						field.set(obj, value);
						field.setAccessible(false);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
