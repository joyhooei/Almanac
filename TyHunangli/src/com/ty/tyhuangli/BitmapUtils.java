package com.ty.tyhuangli;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.view.View;

public class BitmapUtils {
	/**
	 * ��ȡView��ͼ
	 * 
	 * @param view
	 * @return ��ͼ���Bitmap
	 * */
	public static Bitmap getBitmapByView(View view) {
		view.setDrawingCacheEnabled(true);
		view.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
		view.buildDrawingCache();
		return view.getDrawingCache();
	}

	/**
	 * Bitmapת�����ֽ�
	 * 
	 * @param view
	 * @return byte[]
	 * */
	public static byte[] Bitmap2Bytes(Bitmap bm) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		bm.compress(Bitmap.CompressFormat.PNG, 100, baos);
		return baos.toByteArray();
	}

	/**
	 * �ֽ�ת����Bitmap
	 * 
	 * @param view
	 * @return Bitmap
	 * */
	public static Bitmap Bytes2Bimap(byte[] b) {
		if (b.length != 0) {
			return BitmapFactory.decodeByteArray(b, 0, b.length);
		} else {
			return null;
		}
	}

	/**
	 * ����ΪBitmap
	 * 
	 * @param bitName
	 *            λͼ����
	 * @param bitmap
	 *            ������λͼ
	 * @return ����λͼ������URI
	 * */
	public static Uri saveMyBitmap(String bitName, Bitmap bitmap) {
		File photo = new File(Environment.getExternalStorageDirectory(),
				bitName);

		FileOutputStream fOut = null;
		try {
			photo.createNewFile();
			fOut = new FileOutputStream(photo);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		bitmap.compress(Bitmap.CompressFormat.PNG, 100, fOut);
		try {
			fOut.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			fOut.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return Uri.fromFile(photo);
	}
}
