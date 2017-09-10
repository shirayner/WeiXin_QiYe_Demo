package com.ray.util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.Random;
import javax.imageio.ImageIO;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.Binarizer;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.DecodeHintType;
import com.google.zxing.EncodeHintType;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;



public class QRCodeUtil {

	/**
	 * 根据内容，生成指定宽高、指定格式的二维码图片
	 *
	 * @param text   内容
	 * @param width  宽
	 * @param height 高
	 * @param format 图片格式
	 * @return 生成的二维码图片路径
	 * @throws Exception
	 */
	public static String generateQRCode(String text, int width, int height, String format) throws Exception {
		Hashtable<EncodeHintType, Object> hints = new Hashtable<>();
		hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
		BitMatrix bitMatrix = new MultiFormatWriter().encode(text, BarcodeFormat.QR_CODE, width, height, hints);
		//String pathName = "D:/new.png";
		String pathName = "D:/new.png";

		Path outputFile=Paths.get(pathName);
		MatrixToImageWriter.writeToPath(bitMatrix, format, outputFile);

		return pathName;
	}

	/**
	 * 随机生成指定长度的验证码
	 *
	 * @param length 验证码长度
	 * @return 生成的验证码
	 */
	public  static String generateNumCode(int length) {
		String val = "";
		String charStr = "char";
		String numStr = "num";
		Random random = new Random();

		//参数length，表示生成几位随机数
		for (int i = 0; i < length; i++) {

			String charOrNum = random.nextInt(2) % 2 == 0 ? charStr : numStr;
			//输出字母还是数字
			if (charStr.equalsIgnoreCase(charOrNum)) {
				//输出是大写字母还是小写字母
				int temp = random.nextInt(2) % 2 == 0 ? 65 : 97;
				val += (char) (random.nextInt(26) + temp);
			} else if (numStr.equalsIgnoreCase(charOrNum)) {
				val += String.valueOf(random.nextInt(10));
			}
		}
		return val;
	}

	/**
	 * 解析指定路径下的二维码图片
	 *
	 * @param filePath 二维码图片路径
	 * @return
	 */
	public static String parseQRCode(String filePath) {
		String content = "";
		try {
			File file = new File(filePath);
			BufferedImage image = ImageIO.read(file);

			LuminanceSource source = new BufferedImageLuminanceSource(image);
			Binarizer binarizer = new HybridBinarizer(source);
			BinaryBitmap binaryBitmap = new BinaryBitmap(binarizer);

			Map<DecodeHintType, Object> hints = new HashMap<>();
			// 解码设置编码方式为：utf-8，
			hints.put(DecodeHintType.CHARACTER_SET, "UTF-8");
			//优化精度
			hints.put(DecodeHintType.TRY_HARDER, Boolean.TRUE);
			//复杂模式，开启PURE_BARCODE模式
			hints.put(DecodeHintType.PURE_BARCODE, Boolean.TRUE);

			MultiFormatReader formatReader = new MultiFormatReader();
			Result result = formatReader.decode(binaryBitmap, hints);

			System.out.println("result 为：" + result.toString());
			System.out.println("resultFormat 为：" + result.getBarcodeFormat());
			System.out.println("resultText 为：" + result.getText());
			//设置返回值
			content = result.getText();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return content;
	}

	public static String decode(File file){//, Map<DecodeHintType, Object> hints) throws Exception {
		// check the required parameters
		if (file == null || file.getName().trim().isEmpty())
			throw new IllegalArgumentException("File not found, or invalid file name.");
		BufferedImage image = null;
		try {
			image = ImageIO.read(file);
			
		} catch (IOException ioe) {
			try {
				throw new Exception(ioe.getMessage());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (image == null)
			throw new IllegalArgumentException("Could not decode image.");

		
		LuminanceSource source = new BufferedImageLuminanceSource(image);
		BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
		MultiFormatReader barcodeReader = new MultiFormatReader();
		Result result;
		String finalResult = null;
		try {
			//if (hints != null && ! hints.isEmpty())
			//  result = barcodeReader.decode(bitmap, hints);
			//else
			result = barcodeReader.decode(bitmap);
			// setting results.
			finalResult = String.valueOf(result.getText());
		} catch (Exception e) {
			e.printStackTrace();
			//  throw new BarcodeEngine().new BarcodeEngineException(e.getMessage());
		}
		return finalResult;
	}


}
