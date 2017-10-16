package com.main.org.testng.fileutil;

import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import com.main.org.testng.constants.TestNG_Constants;

public class FileUtil {

	public static Logger log = Logger.getLogger(FileUtil.class);
	
	
	public boolean checkFileExists() {
		File file = new File(TestNG_Constants.TEST_NG_HOMEDIRECTORY + TestNG_Constants.TEST_NG_FILENAME);
		if (file.exists())
			return true;
		else
			return false;
	}

	public boolean renameTestNG_XML() {
		String filePath = TestNG_Constants.TEST_NG_HOMEDIRECTORY + TestNG_Constants.TEST_NG_FILENAME;
		File oldFile = new File(filePath);
		File newFile = new File(filePath + "(" + System.currentTimeMillis() + ")");
		if (oldFile.renameTo(newFile))
			return true;
		else
			return false;
	}

	public boolean checkAndRemoveTestNG_XMLFile() {
		if (checkFileExists())
			cleanUpFolder();
		else
			return false;
		return true;
	}

	public void cleanUpFolder()  {
		File file = new File(TestNG_Constants.TEST_NG_HOMEDIRECTORY);
		File[] listOfFiles = file.listFiles();
		for (int i = 0; i < listOfFiles.length; i++) {
//			if (listOfFiles[i].getName().equalsIgnoreCase(TestNG_Constants.TEST_NG_FILENAME))
//				continue;
			listOfFiles[i].delete();
		}
		System.out.println("Cleaning Success");
	}
	public void moveToDestinationFolder(String sourcePath, String destinationPath){
		File destinationFolder = new File(destinationPath);
	    File sourceFolder = new File(sourcePath);
	    // Check weather source exists and it is folder.
	    if (sourceFolder.exists() && sourceFolder.isDirectory())
	    {
	        // Get list of the files and iterate over them
	        File[] listOfFiles = sourceFolder.listFiles();

	        if (listOfFiles.length != 0)
	        {
	        	//create destination folder
	        	  if (!destinationFolder.exists())
	      	    {
	      	        destinationFolder.mkdirs();
	      	    }
	            for (File child : listOfFiles )
	            {
	                // Move files to destination folder
	                child.renameTo(new File(destinationFolder + "\\" + child.getName()));
	            }
	            // Add if you want to delete the source folder 
	    		for (int i = 0; i < listOfFiles.length; i++) {
	    			listOfFiles[i].delete();
	    		}
	        }
	    }
	    else
	    {
	        System.out.println(sourceFolder + "  Folder does not exists");
	    }
	}
	
	public void copyToDestinationFolder(String sourcePath, String destinationPath){
		File destinationFolder = new File(destinationPath);
	    File sourceFolder = new File(sourcePath);
	    sourceFolder.renameTo(new File(destinationFolder+"/"+sourceFolder.getName()));
	}
	public void copyFileToDirectory(String sourcePath, String destinationPath){
		File destinationFolder = new File(destinationPath);
	    File sourceFolder = new File(sourcePath);
	    try {
			FileUtils.copyFileToDirectory(sourceFolder, destinationFolder);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void copyDirectory(String sourcePath, String destinationPath){
	    File sourceFolder = new File(sourcePath);
	    File destinationFolder = new File(destinationPath+"/"+sourceFolder.getName());
	    try {
			FileUtils.copyDirectory(sourceFolder, destinationFolder);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void deleteFile(String folderPath){
		File folderToDelete = new File(folderPath);
		if(folderToDelete.exists())		folderToDelete.delete();
		}
	public void deleteInnerFiles(String folderPath){
		File innerFolderToDelete = new File(folderPath);
		log.info("Path to delete file "+innerFolderToDelete.getAbsolutePath());
		File[] listOfFiles = innerFolderToDelete.listFiles();
		for (int i = 0; i < listOfFiles.length; i++) {
			if(listOfFiles[i].exists()) listOfFiles[i].delete();
		}
	}
	public void deleteDirectory(String directoryPath){
		File directoryFile = new File(directoryPath);
		try {
			FileUtils.deleteDirectory(directoryFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
