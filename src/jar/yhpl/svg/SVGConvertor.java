package jar.yhpl.svg;

import jar.yhpl.svg.svg.Svg2Vector;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileFilter;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import app.yhpl.utils.FileUtils;

public class SVGConvertor {
	private final static String FILE_FORMAT = ".svg";
	private final static String FILE_FORMAT_XML = ".xml";
	private final static String XML_HEADER = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n";

	private final FileFilter mFileFilter = new FileFilter() {

		@Override
		public boolean accept(File pathname) {
			if (pathname.isDirectory()) {
				return false;
			}
			if (pathname.isFile() && pathname.getName().endsWith(FILE_FORMAT)) {
				return true;
			}
			return false;
		}
	};

	public void convertAtPathWithTargetPath(File dir, File target) {
		System.out.println("当前路径:" + dir.getPath());
		FileUtils.checkDir(target);
		List<File> fileList = getAllFiltAtAfterFixBelowPath(dir);
		int fileSize = fileList.size();
		String logTrackBegin = "%1$d/" + "%2$d --- begin";
		String logTrackEnd = "		%1$d/ " + "%2$d  ####:end:%3$s";
		if (fileSize > 0) {
			for (int i = 0; i < fileSize; i++) {
				System.out.println(String.format(logTrackBegin, i + 1, fileSize));
				File file = fileList.get(i);
				String fileName = FileUtils.getFileName(file);
				String xmlContent = generateVectorXml(file);
				FileUtils.write2FileWithName(target, fileName, FILE_FORMAT_XML, xmlContent);
				System.out.println(String.format(logTrackEnd, i + 1, fileSize, fileName));
			}
		} else {
			System.out.println("没有找到任何SVG文件");
		}
	}

	private List<File> getAllFiltAtAfterFixBelowPath(File path) {
		List<File> result = new ArrayList<File>();
		if (!path.isDirectory()) {
			return result;
		}
		File[] filterFiles = path.listFiles(mFileFilter);
		result.addAll(Arrays.asList(filterFiles));
		return result;
	}

	public static String generateVectorXml(File inputSvgFile) {
		OutputStream outStream = new ByteArrayOutputStream();

		// String parseError =
		Svg2Vector.parseSvgToXml(inputSvgFile, outStream);
		// System.out.println("error:" + parseError);
		String vectorXmlContent = outStream.toString();

		// Return null content to make sure the preview image will be gone!
		return XML_HEADER + vectorXmlContent;
	}
}
