package com.sun.activity;

import java.io.IOException;
import java.io.InputStream;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.widget.ImageView;

import com.custom.customview.lib.SwitchView;
import com.sun.R;
import com.sun.inject.InjectView;
import com.sun.view.BaseFragment;

public class CustomViewFragment extends BaseFragment {
	
	@InjectView(R.id.custom)
	private SwitchView custom;
	@InjectView(R.id.test_bitmap)
	private ImageView test_bitmap;
	
	public static CustomViewFragment newInstance(FragmentManager fragmentManager, int frame_id) {
		CustomViewFragment f = (CustomViewFragment) fragmentManager.findFragmentByTag(CustomViewFragment.class.getSimpleName());
		if (f == null) {
			f = new CustomViewFragment();
		}
		fragmentManager.beginTransaction().setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE).replace(frame_id, f).commitAllowingStateLoss();
		return f;
	}

	@Override
	public int getLayoutID() {
		return R.layout.frg_custom_view;
	}
	
	@Override
	public void initView() {
		super.initView();
		custom.setChecked(true);
		final BitmapFactory.Options options = new BitmapFactory.Options();
		Rect outPadding = new Rect(-75, 0, 75, 0);
		InputStream is;
		try {
			is = getActivity().getAssets().open("beach.jpg");
			Bitmap bitmap = BitmapFactory.decodeStream(is, outPadding, options);
			test_bitmap.setImageBitmap(bitmap);
			Log.e(TAG, "["+bitmap.getWidth()+" "+bitmap.getHeight());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
