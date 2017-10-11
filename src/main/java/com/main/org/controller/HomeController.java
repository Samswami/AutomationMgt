package com.main.org.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.main.org.testng.constants.TestNG_Constants;

@Controller
public class HomeController {
	@RequestMapping("/")
	public String home() {
		return "Pages/final-index";
	}
	@RequestMapping("/report")
	public String getReport(){
		String reportName = TestNG_Constants.EXTENT_REPORT_NAME.replace(".html", "");
//		System.out.println(reportName);
//		String reportName = "C:/ApiAutomation/Resource/Reports/ApiAutomation";
		return "Report/"+reportName;
//		return "redirect:";
	}
	
//	@RequestMapping("/upload")
//	public String upload(){
//		
//		return null;
//	}
@RequestMapping("/readme")
public String getReadMe(){
	return "Pages/Readme";
}
	
	
}
