package com.rawchen.utils;

import java.text.DecimalFormat;

public class FileUtil {
	public static String imageType(String perfix){
		//图片
		if(perfix.equalsIgnoreCase("JPG") || perfix.equalsIgnoreCase("JPEG") || perfix.equalsIgnoreCase("GIF")
				|| perfix.equalsIgnoreCase("PNG") || perfix.equalsIgnoreCase("BMP") || perfix.equalsIgnoreCase("PCX")
				|| perfix.equalsIgnoreCase("TGA") || perfix.equalsIgnoreCase("TIFF") || perfix.equalsIgnoreCase("SVG")
				|| perfix.equalsIgnoreCase("RAW") || perfix.equalsIgnoreCase("EPS") || perfix.equalsIgnoreCase("AI")
				|| perfix.equalsIgnoreCase("ICO")) {
			return "image";
		}
		if(perfix.equalsIgnoreCase("PSD")) {
			return "psd";
		}
		//压缩文件
		if(perfix.equalsIgnoreCase("ZIP") || perfix.equalsIgnoreCase("7Z") || perfix.equalsIgnoreCase("RAR") || perfix.equalsIgnoreCase("7-ZIP")) {
			return "compress";
		}
		if(perfix.equalsIgnoreCase("ISO") || perfix.equalsIgnoreCase("DMG")) {
			return "iso";
		}
		//音频
		if(perfix.equalsIgnoreCase("MP3") || perfix.equalsIgnoreCase("WAV") || perfix.equalsIgnoreCase("MIDI")
				|| perfix.equalsIgnoreCase("WMA") || perfix.equalsIgnoreCase("OGG") || perfix.equalsIgnoreCase("APE")) {
			return "music";
		}
		//视频
		if(perfix.equalsIgnoreCase("MP4") || perfix.equalsIgnoreCase("RMVB") || perfix.equalsIgnoreCase("AVI")
				|| perfix.equalsIgnoreCase("RM") || perfix.equalsIgnoreCase("FLV") || perfix.equalsIgnoreCase("MPG")
				|| perfix.equalsIgnoreCase("MOV") || perfix.equalsIgnoreCase("MKV") || perfix.equalsIgnoreCase("ASF")
				|| perfix.equalsIgnoreCase("NAVI")  || perfix.equalsIgnoreCase("MPEG") || perfix.equalsIgnoreCase("3GP")) {
			return "video";
		}
		//PPT
		if(perfix.equalsIgnoreCase("PPT") || perfix.equalsIgnoreCase("PPTX")) {
			return "ppt";
		}
		//WORD
		if(perfix.equalsIgnoreCase("DOC") || perfix.equalsIgnoreCase("DOCX")) {
			return "doc";
		}
		//XLS
		if(perfix.equalsIgnoreCase("XLS") || perfix.equalsIgnoreCase("XLSX")) {
			return "xls";
		}
		//txt
		if(perfix.equalsIgnoreCase("TXT")) {
			return "txt";
		}
		//PDF
		if(perfix.equalsIgnoreCase("PDF")) {
			return "pdf";
		}
		//APK
		if(perfix.equalsIgnoreCase("APK")) {
			return "apk";
		}
		//mac
		if(perfix.equalsIgnoreCase("IPA") || perfix.equalsIgnoreCase("PKG")) {
			return "mac";
		}
		//system
		if(perfix.equalsIgnoreCase("EXE") || perfix.equalsIgnoreCase("BAT") || perfix.equalsIgnoreCase("BIN")
				|| perfix.equalsIgnoreCase("DLL") || perfix.equalsIgnoreCase("CMD") || perfix.equalsIgnoreCase("REG")
				|| perfix.equalsIgnoreCase("DB")) {
			return "system";
		}
		//code
		if(perfix.equalsIgnoreCase("JAVA") || perfix.equalsIgnoreCase("SQL") || perfix.equalsIgnoreCase("html")
				|| perfix.equalsIgnoreCase("XML") || perfix.equalsIgnoreCase("MD") || perfix.equalsIgnoreCase("CSS")
				|| perfix.equalsIgnoreCase("SCSS") || perfix.equalsIgnoreCase("JS") || perfix.equalsIgnoreCase("C")
				|| perfix.equalsIgnoreCase("CS") || perfix.equalsIgnoreCase("SH") || perfix.equalsIgnoreCase("TS")
				|| perfix.equalsIgnoreCase("BASIC") || perfix.equalsIgnoreCase("CPP") || perfix.equalsIgnoreCase("CMAKE")
				|| perfix.equalsIgnoreCase("GO") || perfix.equalsIgnoreCase("GROOVY") || perfix.equalsIgnoreCase("JSON")
				|| perfix.equalsIgnoreCase("KT") || perfix.equalsIgnoreCase("TEX") || perfix.equalsIgnoreCase("LESS")
				|| perfix.equalsIgnoreCase("M") || perfix.equalsIgnoreCase("PASCAL") || perfix.equalsIgnoreCase("PHP")
				|| perfix.equalsIgnoreCase("VB") || perfix.equalsIgnoreCase("SASS") || perfix.equalsIgnoreCase("RUBY")
				|| perfix.equalsIgnoreCase("PY") || perfix.equalsIgnoreCase("PROPERTIES") || perfix.equalsIgnoreCase("R")
				|| perfix.equalsIgnoreCase("SCALA")) {
			return "code";
		}
		return "other";
	}

	public static String formatBytes(long size){
		StringBuffer bytes = new StringBuffer();
		DecimalFormat format = new DecimalFormat("###.00");
		if (size >= 1024 * 1024 * 1024) {
			double i = (size / (1024.0 * 1024.0 * 1024.0));
			bytes.append(format.format(i)).append(" GB");
		}
		else if (size >= 1024 * 1024) {
			double i = (size / (1024.0 * 1024.0));
			bytes.append(format.format(i)).append(" MB");
		}
		else if (size >= 1024) {
			double i = (size / (1024.0));
			bytes.append(format.format(i)).append(" KB");
		}
		else if (size < 1024) {
			if (size <= 0) {
				bytes.append("0 Bytes");
			}
			else {
				bytes.append((int) size).append(" B");
			}
		}
		return bytes.toString();
	}
}
