package app.yhpl.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileUtils {
	public static String getFileName(File file) {
		String name = "";
		if (file == null) {
			return name;
		}
		String fullName = file.getName();
		int endIndex = fullName.lastIndexOf('.');
		if (endIndex > 0) {
			name = fullName.substring(0, endIndex);
		}
		return name;
	}

	public static boolean write2FileWithName(File parent, String name, String afterFix, String content) {
		byte[] bytes = EncodingUtils.string2Byte(content);
		if (bytes == null || bytes.length == 0) {
			return false;
		}
		File file = new File(parent, name + afterFix);
		if (file.exists()) {
			file.delete();
		}
		FileOutputStream fos = null;
		try {
			file.createNewFile();
			fos = new FileOutputStream(file);
			fos.write(bytes);
			fos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			IOUtils.closeInputStream(fos);
		}
		return true;
	}

	public static boolean checkDir(File dir) {
		if (!dir.exists()) {
			dir.mkdirs();
			return true;
		}
		return false;
	}
}
