package com.main.org.controller;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.main.org.entity.ResponseStatus;
import com.main.org.service.FileUploadingService;
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
        		fileUploadingService.replaceOldExcelOrJksWithNewForDownload(uploadfiles);
        		String uploadedFileName = Arrays.stream(uploadfiles).map(x -> x.getOriginalFilename())
                        .filter(x -> !StringUtils.isEmpty(x)).collect(Collectors.joining(" , "));
        	return new ResponseStatus("Uploaded Files:>"+uploadedFileName, 00);
        	}catch(Exception e){
        		return new ResponseStatus("Exception occured try uploading again or check logs", 1);
        	}
        }
        else  return new ResponseStatus(updatedFileStatus, 1);
        
        /*String uploadedFileName = Arrays.stream(uploadfiles).map(x -> x.getOriginalFilename())
                .filter(x -> !StringUtils.isEmpty(x)).collect(Collectors.joining(" , "));*/
     /*   System.out.println(uploadedFileName);
        if (StringUtils.isEmpty(uploadedFileName)) {
            return new  ResponseStatus("please select a file!", 00);
        }
        
        try {

        	fileUploadingService.saveUploadedFiles(Arrays.asList(uploadfiles));

        } catch (IOException e) {
            return new ResponseStatus("IO Exception", 00);
        }*/

        /*return new  ResponseStatus("Successfully Uploaded"+ uploadedFileName, 00);*/

    }
}
