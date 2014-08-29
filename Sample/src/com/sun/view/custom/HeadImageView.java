/**
 * 
 */
package com.sun.view.custom;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * @author sunny sun
 * @date 2013-5-23
 * 绘制特定形状的头像
 */
public class HeadImageView extends ImageView {
	
	private PorterDuffXfermode xfermode = new PorterDuffXfermode(PorterDuff.Mode.DST_IN);
	private Paint paint = new Paint();
	private Bitmap dst = null;
	private Matrix matrix;

	public HeadImageView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	public HeadImageView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public HeadImageView(Context context) {
		super(context);
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		if(dst != null && !dst.isRecycled()) {
	        int sc = canvas.saveLayer(0, 0, getWidth(), getHeight(), null,
	                                  Canvas.MATRIX_SAVE_FLAG |
	                                  Canvas.CLIP_SAVE_FLAG |
	                                  Canvas.HAS_ALPHA_LAYER_SAVE_FLAG |
	                                  Canvas.FULL_COLOR_LAYER_SAVE_FLAG |
	                                  Canvas.CLIP_TO_LAYER_SAVE_FLAG);
	        super.onDraw(canvas);
	        paint.setXfermode(xfermode);
	        if(matrix == null) {
	        	matrix = new Matrix();
	        	float ratio = (float)getWidth() / dst.getWidth();
	        	matrix.setScale(ratio, ratio);
	        }
//	        if(matrix == null)
//	        	canvas.drawBitmap(dst, 0, 0, paint);
//	        else
//	        	canvas.drawBitmap(dst, matrix, paint);
	        canvas.drawColor(0x000000);
	        canvas.drawCircle(getWidth() >> 1, getHeight() >> 1, getWidth() >> 1, paint);
	        paint.setXfermode(null);
	        canvas.restoreToCount(sc);
		} else {
			super.onDraw(canvas);
		}
	}
	
	public void setDstBitmap(Bitmap d) {
		dst = d; //BitmapFactory.decodeResource(getResources(), R.drawable.seven_pserson_shape_icon_style_one);
		matrix = null;
	}

}
