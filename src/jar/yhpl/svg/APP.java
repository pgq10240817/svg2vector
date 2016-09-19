package jar.yhpl.svg;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

public class APP {

	public static void main(String[] args) {
		SVGConvertor mConvertor = new SVGConvertor();
		if (args.length < 1) {
			System.out.println("没有输入文件");
			System.out.println("是否读取当前目录下所有的svg 请选择:\n	1.是 \n	2:退出");
			char i;
			try {
				i = (char) System.in.read();
				System.out.println("输入了:" + i);
				if (i == '1') {
					String dir = System.getProperty("user.dir");
					System.out.println(dir);
					mConvertor.convertAtPathWithTargetPath(new File(dir), new File(dir));
				}
			} catch (IOException e) {
				e.printStackTrace();
			}

			return;
		}
		String targetPath, svgPath;
		targetPath = svgPath = args[0];
		if (args.length > 1) {
			targetPath = args[1];
		}
		mConvertor.convertAtPathWithTargetPath(new File(svgPath), new File(targetPath));

		// String filePath = "raw/1.svg";
		// File file = new File("raw/ai_svg_2.svg");
		// // String xmlFileContent = SVGConvertor.generateVectorXml(file);
		// // System.out.println(xmlFileContent);
		// testXMLWithDOM(file);

	}

	public static void testXMLWithDOM(File file) {
		long time = System.currentTimeMillis();
		System.out.println("begin --- ::");
		try {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			dbf.setValidating(false);
			dbf.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false);

			DocumentBuilder builder = dbf.newDocumentBuilder();
			InputStream in = new FileInputStream(file);
			System.out.println("###############");
			Document doc = builder.parse(in);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("cost: " + (System.currentTimeMillis() - time));
	}
}
