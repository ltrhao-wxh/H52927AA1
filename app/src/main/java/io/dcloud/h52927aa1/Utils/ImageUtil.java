package io.dcloud.h52927aa1.Utils;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore.Images.ImageColumns;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class ImageUtil {

	/**
	 * ѹ��ͼ��
	 * @param path
	 * @return
	 */
	public static String compressionImage(Context context,String path) {
		File f = new File(path);
		if (f.exists()) {
			String extensionName = FileUtil.getExtensionName(f.getName());
			String fileName = String.valueOf(System.currentTimeMillis());
			String newName = fileName+"."+extensionName;
			String cache = FileUtil.getCacheDir(context)+"temp_image/"; 
			FileUtil.checkDir(cache);
			String newPath = cache+ newName;
			FileInputStream fis;
			try {
				fis = new FileInputStream(f);
				//���ص�ʵ�ʿɶ��ֽ���,Ҳ�����ܴ�С ������Ҫע����ǡ�java������е��ַ���Unicode����  
				long size= fis.available();
	            fis.close();
	            fis=null;
	            if(size>204800L){   //200KB���ڲ�ѹ��
	    			Bitmap bm = getSmallBitmap(path,480,800);
	    			FileOutputStream fos = new FileOutputStream(new File(newPath));
	    			bm.compress(Bitmap.CompressFormat.JPEG, 90, fos);
	    			if(fos!=null){
	    				fos.close();
	    				fos=null;
	    			}
	    			bm.recycle();
	    			bm=null;
	    			
	    			return newPath;
	            }
			} catch (Exception e) {
					e.printStackTrace();
			}
        }
		return path;
	}
	
	/**
	 * ����·�����ͼƬ��ѹ������bitmap������ʾ
	 * @param
	 * @return
	 */
	public static Bitmap getSmallBitmap(String filePath,int width,int height) {
		final BitmapFactory.Options options = new BitmapFactory.Options();
		options.inJustDecodeBounds = true;
		BitmapFactory.decodeFile(filePath, options);
		// Calculate inSampleSize
		options.inSampleSize = calculateInSampleSize(options, width, height);
		// Decode bitmap with inSampleSize set
		options.inJustDecodeBounds = false;
		return BitmapFactory.decodeFile(filePath, options);
	}
	
	/**
	 * ����ͼƬ������ֵ
	 * 
	 * @param options
	 * @param reqWidth
	 * @param reqHeight
	 * @return
	 */
	public static int calculateInSampleSize(BitmapFactory.Options options,
			int reqWidth, int reqHeight) {
		// Raw height and width of image
		final int height = options.outHeight;
		final int width = options.outWidth;
		int inSampleSize = 1;

		if (height > reqHeight || width > reqWidth) {
			final int heightRatio = Math.round((float) height / (float) reqHeight);
			final int widthRatio = Math.round((float) width / (float) reqWidth);
			inSampleSize = heightRatio < widthRatio ? heightRatio : widthRatio;
		}
		return inSampleSize;
	}
	
	/**
	 * ��uriת��Ϊ����·��
	 * @param context
	 * @param uri
	 * @return
	 */
	public static String getRealFilePath( final Context context, final Uri uri ) {
	    if ( null == uri ) {
	    	return null;
	    }
	    final String scheme = uri.getScheme();
	    String data = null;
	    if ( scheme == null )
	        data = uri.getPath();
	    else if ( ContentResolver.SCHEME_FILE.equals( scheme ) ) {
	        data = uri.getPath();
	    } else if ( ContentResolver.SCHEME_CONTENT.equals( scheme ) ) {
	        Cursor cursor = context.getContentResolver().query( uri, new String[] { ImageColumns.DATA }, null, null, null );
	        if ( null != cursor ) {
	            if ( cursor.moveToFirst() ) {
	                int index = cursor.getColumnIndex( ImageColumns.DATA );
	                if ( index > -1 ) {
	                    data = cursor.getString( index );
	                }
	            }
	            cursor.close();
	        }
	    }
	    return data;
	}
	
}
