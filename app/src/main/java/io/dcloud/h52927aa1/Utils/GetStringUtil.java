package io.dcloud.h52927aa1.Utils;

import android.content.Context;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;


public class GetStringUtil {
	public static String getString(Context context,String fileName) {
		try {
			InputStream is=context.getAssets().open(fileName);
			byte[] bytes =new byte[1024];
			int length;
			StringBuilder sb= new StringBuilder();
			while((length=is.read(bytes))!=-1){
				sb.append(new String(bytes,0,length));
			}
			return sb.toString();
		} catch (IOException e) {
			Toast.makeText(context, "��ȡ�ļ�ʧ��", Toast.LENGTH_SHORT).show();
			e.printStackTrace();
		}
		return "";
	}
}
