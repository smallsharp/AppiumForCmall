package top.base.utils;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class ImageUtil {
	
	/**
	 * 
	 * @param mImage 第一张图片
	 * @param uImage 第二张图片
	 * @param percent 指定相同的比例
	 * @return 如果指定的比例<=实际相同的比例，则为True
	 */
	public static boolean sameAs(File imageA, File imageB, double percent) {
		
		BufferedImage bufferedImageA = null;
		BufferedImage bufferedImageB = null;

		try {

			bufferedImageA = ImageIO.read(imageA);
			bufferedImageB = ImageIO.read(imageB);

		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}

		return sameAs(bufferedImageA, bufferedImageB, percent);
	}

	/**
	 * 
	 * @param mImage 第一张图片
	 * @param uImage 第二张图片
	 * @param percent 指定相同的比例
	 * @return 如果指定的比例<=实际相同的比例，则为True
	 */
	public static boolean sameAs(BufferedImage mImage, BufferedImage uImage, double percent) {

		if (uImage.getWidth() != mImage.getWidth()) {
			System.out.println("两张图片的宽度不一样");
			return false;
		}
		if (uImage.getHeight() != mImage.getHeight()) {
			System.out.println("两张图片的高度不一样");
			return false;
		}

		int width = mImage.getWidth();
		int height = mImage.getHeight();

		int numDiffPixels = 0;

		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				if (mImage.getRGB(x, y) != uImage.getRGB(x, y)) {
					numDiffPixels++;
				}
			}
		}
		double numberPixels = height * width;
		double diffPercent = numDiffPixels / numberPixels;
		System.out.println("总像素点："+ numberPixels);
		System.out.println("不同的像素点："+ numDiffPixels);
		System.out.println("不同的像素点占比："+diffPercent);
		System.out.println("两张图片的实际相似度：" + (1.0D - diffPercent));
		return percent <= 1.0D - diffPercent;
	}
	
	
	/**
	 * 对比图片A 和 图片B
	 * @param ImageA
	 * @param ImageB
	 * @return 两张图片的相似比例
	 */
	public static double getSamePercentFrom(File imageA, File imageB) {
		
		BufferedImage bufferedImageA = null;
		BufferedImage bufferedImageB = null;

		try {

			bufferedImageA = ImageIO.read(imageA);
			bufferedImageB = ImageIO.read(imageB);

		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}

		return getSamePercentFrom(bufferedImageA, bufferedImageB);
	}
	
	
	/**
	 * 
	 * @param bufferImageA 第一张图片
	 * @param bufferImageB 第二张图片
	 * @return 两张的图片的相似比例
	 */
	public static double getSamePercentFrom(BufferedImage bufferImageA, BufferedImage bufferImageB) {

		if (bufferImageB.getWidth() != bufferImageA.getWidth()) {
			System.out.println("两张图片的宽度不一样");
		}
		if (bufferImageB.getHeight() != bufferImageA.getHeight()) {
			System.out.println("两张图片的高度不一样");
		}

		int width = bufferImageA.getWidth();
		int height = bufferImageA.getHeight();

		int numDiffPixels = 0;

		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				if (bufferImageA.getRGB(x, y) != bufferImageB.getRGB(x, y)) {
					numDiffPixels++;
				}
			}
		}
		double numberPixels = height * width;
		double diffPercent = numDiffPixels / numberPixels;
		double s = 1.0D - diffPercent;
		System.out.println("相似比例："+s);
		return 1.0D - diffPercent;
	}
	
	
	public static BufferedImage getSubImage(BufferedImage image, int x, int y, int w, int h) {
		return image.getSubimage(x, y, w, h);
	}

	public static BufferedImage getImageFromFile(File f) {

		BufferedImage img = null;

		try {
			img = ImageIO.read(f);
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
		return img;
	}

}
