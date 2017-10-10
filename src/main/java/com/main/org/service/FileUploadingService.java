package com.main.org.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.main.org.testng.constants.UploadConstants;

@Service
public class FileUploadingService {

	/*****************************  Main Methods **************************/
	public void saveUploadedFiles(MultipartFile[] files) throws IOException {
      /*  for (MultipartFile file : files) {
            if (file.isEmpty()) {
                continue; //next pls
            }
            byte[] bytes = file.getBytes();
            Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
            Files.write(path, bytes);
        }*/
		if(!(files[0].getOriginalFilename().isEmpty())) writeFile(files[0], UploadConstants.COMPLETE_EXCEL_PATH);
		if(!(files[1].getOriginalFilename().isEmpty())) writeFile(files[1], UploadConstants.COMPLETE_JKS_PATH);
		
    }
	
	public String validateUploadedFiles(MultipartFile[] files){
		String excelFileName = files[0].getOriginalFilename();
		String jksFileName = files[1].getOriginalFilename();
		if( (excelFileName.isEmpty()) && (jksFileName.isEmpty()) ){
			return null; // nothing to upload
		}else{
			if(  (!(excelFileName.isEmpty()))    &&  (!(jksFileName.isEmpty())) ) return validateBothFile(files);
			else if(!(excelFileName.isEmpty())) return validateExcelFile(files[0]);
			else if(!(jksFileName.isEmpty()))  return validateJKSFile(files[1]);
			else return "";
		}
	}
	
	public void checkAndRenameExcelAndJksFiles(MultipartFile[] files){
		if(!(files[0].isEmpty())){
			if(checkExcelFilePresent()) renameExcelFile();
		}
		if(!(files[1].isEmpty())){
			if(checkJksFilePresent()) renameJksFile();
		}
	}
	
	public void replaceOldExcelOrJksWithNewForDownload(MultipartFile[] files){
		String excelFileName = files[0].getOriginalFilename();
		String jksFileName = files[1].getOriginalFilename();
		if( !(excelFileName.isEmpty()) ){
			checkAndDeleteOldExcelForDownload();
			writeFile(files[0], UploadConstants.EXCEL_DOWNLOAD_PATH+UploadConstants.EXCEL_DOWNLOAD_FILE_NAME);
		}
		if( !(jksFileName.isEmpty()) ){
			checkAndDeleteOldJksForDownload();
			writeFile(files[1], UploadConstants.JKS_DOWNLOAD_PATH+UploadConstants.JKS_DOWNLOAD_FILE_NAME);
		}
	}
	
	/*****************************  Sub Methods **************************/
	
	public String validateExcelFile(MultipartFile excelFile){
		String fileExtension = FilenameUtils.getExtension(excelFile.getOriginalFilename());
		System.out.println("Excel Extension = "+ fileExtension);
		if( (fileExtension.equalsIgnoreCase("xls")) || (fileExtension.equalsIgnoreCase("xlsx")) ) return "";
		else return "Upload Excel File at 1st upload option";
	}
	
	
	public String validateJKSFile(MultipartFile JksFile){
		String fileExtension = FilenameUtils.getExtension(JksFile.getOriginalFilename());
		System.out.println("JKS Extension = "+ fileExtension);
		if (fileExtension.equalsIgnoreCase("jks")) return "";
		else return "Upload Jks file at 2nd upload option";
	}
	
	
	private String validateBothFile(MultipartFile[] uploadfiles) {
		if( (validateExcelFile(uploadfiles[0]).isEmpty()) && (validateJKSFile(uploadfiles[1]).isEmpty()) ) return "";
		else{
			return "Upload only excel and jks files";
		}
	}
	
	public boolean checkExcelFilePresent(){
		File excelFile = new File(UploadConstants.COMPLETE_EXCEL_PATH);
		if(excelFile.exists()) return true;
		else return false;
	}
	public boolean checkJksFilePresent(){
		File jksFile = new File(UploadConstants.COMPLETE_JKS_PATH);
		if(jksFile.exists()) return true;
		else return false;
	}
	public void renameExcelFile(){
		File oldExcelFile = new File(UploadConstants.COMPLETE_EXCEL_PATH);
		File newExcelFile = new File(UploadConstants.EXCEL_FOLDER_PATH +UploadConstants.EXCEL_FILE_NAME+System.currentTimeMillis()+UploadConstants.EXCEL_FILE_EXTENSION);
		oldExcelFile.renameTo(newExcelFile);
		}
	public void renameJksFile(){
		File oldExcelFile = new File(UploadConstants.COMPLETE_JKS_PATH);
		File newExcelFile = new File(UploadConstants.JKS_FOLDER_PATH +UploadConstants.JKS_FILE_NAME+System.currentTimeMillis()+UploadConstants.JKS_FILE_EXTENSION);
		oldExcelFile.renameTo(newExcelFile);
	}
	public void writeFile(MultipartFile file, String filePathToWrite){
		try {
			byte[] bytes = file.getBytes();
			 Path path = Paths.get(filePathToWrite);
		        Files.write(path, bytes);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void checkAndDeleteOldExcelForDownload(){
		String excelPath = UploadConstants.EXCEL_DOWNLOAD_PATH + UploadConstants.EXCEL_DOWNLOAD_FILE_NAME;
		File file = new File(excelPath);
		if(file.exists()) file.delete();
	}
	public void checkAndDeleteOldJksForDownload(){
		String jksPath = UploadConstants.JKS_DOWNLOAD_PATH + UploadConstants.JKS_DOWNLOAD_FILE_NAME;
		File file = new File(jksPath);
		if(file.exists()) file.delete();
	}
	
	public static void main(String[] args) {
		FileUploadingService f = new FileUploadingService();
		f.renameExcelFile();
	}
	
}
