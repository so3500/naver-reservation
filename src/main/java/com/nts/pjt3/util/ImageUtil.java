package com.nts.pjt3.util;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.springframework.web.multipart.MultipartFile;

public class ImageUtil {
	private static final int MB = 1024 * 1024;
	private final String ROOT_PATH = "/reservation/";
	
	public static final int MAX_IMAGE_SIZE = 10 * MB;
	
	private ImageUtil() {}

	private static class Singleton {
		private static final ImageUtil INSTANCE = new ImageUtil();
	}

	public static ImageUtil getInstance() {
		return Singleton.INSTANCE;
	}

	/**
	 * @param imagePath 이미지가 저장된 경로
	 * @return imagePath에 저장된 이미지
	 * @exception imagePath에 해당하는 이지미가 없을 경우
	 */
	public byte[] getImageByteArray(String imagePath) {
		try (
			FileInputStream imageStream = new FileInputStream(ROOT_PATH + imagePath)) {
			return IOUtils.toByteArray(imageStream);
		} catch (Exception e) {
			throw new RuntimeException("can't open image file");
		}
	}

	/**
	 * @param imageFile 이미지 파일
	 * @param filePath 이미지 파일이 저장될 경로
	 */
	public void saveImageFileAtFileSystem(MultipartFile imageFile, String filePath) {
		try (
			FileOutputStream fos = new FileOutputStream(ROOT_PATH + filePath);
			InputStream is = imageFile.getInputStream();) {
			int readCount = 0;
			byte[] buffer = new byte[MAX_IMAGE_SIZE];
			while ((readCount = is.read(buffer)) != -1) {
				fos.write(buffer, 0, readCount);
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
