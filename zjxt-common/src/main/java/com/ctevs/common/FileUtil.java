package com.ctevs.common;

import java.io.*;

import org.springframework.web.multipart.MultipartFile;

public class FileUtil {
	
	private static final String ydRootFileName = "ydRootFile";

	/**
	 * 上传文件，返回可浏览路径
	 *
	 * @param file
	 * @param filePath
	 *            保存路径,从配置文件中读取
	 * @return
	 * @throws IllegalStateException
	 * @throws IOException
	 */
	public static String getPath(MultipartFile file, String filePath) throws IllegalStateException, IOException {
		// 上传时文件名称
		String wholeName = file.getOriginalFilename();
		// 按照年月生成路径 filePath/年/月/日
		String dateNow = DateUtils.getDateYYMMDD();
		String relativePath = ydRootFileName+ File.separator+dateNow.substring(0, 4) + File.separator + dateNow.substring(4, 6) + File.separator
				+ dateNow.substring(6, dateNow.length()) + File.separator;
		// return saveFile(file, filePath,relativePath,
		// UUIDUtils.getUUID()+wholeName);
		return saveFile(file, filePath, relativePath, UUIDUtils.getUUID() + getPrefix(wholeName));
	}

	/**
	 * 得到文件后缀名
	 * 
	 * @return
	 */
	public static String getPrefix(String fileWholeName) {
		String prefix = "";
		if (fileWholeName != null && fileWholeName.contains(".")) {
			prefix = fileWholeName.substring(fileWholeName.lastIndexOf("."));
		}
		return prefix;
	}
	/**
	 * 
	 * @param file
	 * @param rootPath
	 *            根目录
	 * @param relativePath
	 *            相对于根目录路径
	 * @param fileName
	 *            文件存储名称
	 * @return 浏览器可浏览路径
	 * @throws IllegalStateException
	 * @throws IOException
	 */
	public static String saveFile(MultipartFile file, String rootPath, String relativePath, String fileName)
			throws IllegalStateException, IOException {
		String relativeFile = rootPath + relativePath;
		// 创建文件夹
		File newFile = new File(relativeFile);
		if (!newFile.exists()) {
			newFile.mkdirs();
		}
		// 保存
		String wholePath = relativeFile + fileName;
		file.transferTo(new File(wholePath));
		return changeToUrl(relativePath + fileName);
	}

	/**
	 * 将存储路径转化为浏览器浏览路径
	 * 
	 * @param relativeFile
	 * @return
	 */
	private static String changeToUrl(String relativeFile) {
		return relativeFile.replace(File.separatorChar, '/');
	}
}
