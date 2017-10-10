package com.main.org.testng.constants;

public class UploadConstants {

	public static String EXCEL_FOLDER_PATH = "C:/ApiAutomation/Resource/Excel/";
	public static String EXCEL_FILE_NAME = "ApiAutomation";
	public static String EXCEL_FILE_EXTENSION = ".xlsx";
	public static String COMPLETE_EXCEL_PATH = EXCEL_FOLDER_PATH + EXCEL_FILE_NAME + EXCEL_FILE_EXTENSION;
	
	public static String JKS_FOLDER_PATH = "C:/ApiAutomation/Resource/Jks/";
	public static String JKS_FILE_NAME = "ApiCert";
	public static String JKS_FILE_EXTENSION = ".jks";
	public static String COMPLETE_JKS_PATH = JKS_FOLDER_PATH+ JKS_FILE_NAME+ JKS_FILE_EXTENSION;
	
	public static String EXCEL_DOWNLOAD_PATH = System.getProperty("user.dir")+"/src/main/resources/static/Excel/";
	public static String EXCEL_DOWNLOAD_FILE_NAME = "ApiAutomation.xlsx";
	
	public static String JKS_DOWNLOAD_PATH = System.getProperty("user.dir")+"/src/main/resources/static/Jks/";
	public static String JKS_DOWNLOAD_FILE_NAME = "ApiCert.jks";
	
}
