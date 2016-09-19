package app.yhpl.utils;

import java.io.IOException;
import java.io.OutputStream;

public class IOUtils {

	public static boolean closeInputStream(OutputStream out) {
		if (out == null) {
			return false;
		}
		try {
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return true;
	}
}
