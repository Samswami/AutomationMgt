package com.main.org.controller;

import java.io.IOException;
import java.util.Arrays;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.main.org.entity.ResponseStatus;
import com.main.org.service.FileUploadingService;
import com.main.org.testng.constants.TestNG_Constants;
import com.main.org.testng.constants.UploadConstants;
@RestController
public class UploadController {
	@Autowired
	FileUploadingService fileUploadingService;
	
	 
	@RequestMapping(value = "/upload", method = RequestMethod.POST)
    public ResponseStatus  uploadFileMulti(@RequestParam("files") MultipartFile[] uploadfiles) {

        // Get file name
        String updatedFileStatus = fileUploadingService.validateUploadedFiles(uploadfiles);
        if(updatedFileStatus == null) return new ResponseStatus("No File To Upload", 1);
        else if(updatedFileStatus.isEmpty()){ 
        	try{
        		fileUploadingService.checkAndRenameExcelAndJksFiles(uploadfiles);
        		fileUploadingService.saveUploadedFiles(uploadfiles);
//        		fileUploadingService.replaceOldExcelOrJksWithNewForDownload(uploadfiles);
        		String uploadedFileName = Arrays.stream(uploadfiles).map(x -> x.getOriginalFilename())
                        .filter(x -> !StringUtils.isEmpty(x)).collect(Collectors.joining(" , "));
        	return new ResponseStatus("Uploaded Files:>"+uploadedFileName, 00);
        	}catch(Exception e){
        		return new ResponseStatus("Exception occured try uploading again or check logs", 1);
        	}
        }
        else  return new ResponseStatus(updatedFileStatus, 1);
    }
	
	@RequestMapping ( method = RequestMethod.GET, value = "/downloadExcel" , produces="application/zip")
	public void downloadExcel( HttpServletResponse response) throws IOException{
		String timeStamp = System.currentTimeMillis() + "";
		 response.setStatus(HttpServletResponse.SC_OK);
		 response.addHeader("Content-Disposition", "attachment; filename=\"AutomationMgt"+timeStamp+".zip\"");
		    fileUploadingService.zipStream(response,UploadConstants.COMPLETE_EXCEL_PATH);
	}
	
	@RequestMapping ( method = RequestMethod.GET, value = "/downloadCert" , produces="application/zip")
	public void downloadCert( HttpServletResponse response) throws IOException{
		String timeStamp = System.currentTimeMillis() + "";
		 response.setStatus(HttpServletResponse.SC_OK);
		 response.addHeader("Content-Disposition", "attachment; filename=\"CertJks"+timeStamp+".zip\"");
		    fileUploadingService.zipStream(response,UploadConstants.COMPLETE_JKS_PATH);
	}
	
	@RequestMapping ( method = RequestMethod.GET, value = "/downloadReport" , produces="application/zip")
	public void downloadReport( HttpServletResponse response) throws IOException{
		String timeStamp = System.currentTimeMillis() + "";
		 response.setStatus(HttpServletResponse.SC_OK);
		 response.addHeader("Content-Disposition", "attachment; filename=\"Report"+timeStamp+".zip\"");
		    fileUploadingService.zipMulti(response,TestNG_Constants.EXTENT_REPORT_PATH);
	}
}
