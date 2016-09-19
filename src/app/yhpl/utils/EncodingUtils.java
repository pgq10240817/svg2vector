package app.yhpl.utils;

import java.io.UnsupportedEncodingException;

public class EncodingUtils {
	public final static String ENCODING_UTF8 = "UTF-8";

	public static byte[] string2Byte(String content) {
		byte[] bytes = null;
		try {
			bytes = content.getBytes(ENCODING_UTF8);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bytes;
	}
}
