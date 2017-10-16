package com.main.org.testng.runner;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.apache.log4j.Logger;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.testng.TestNG;

import com.main.org.testng.constants.TestNG_Constants;
import com.main.org.testng.entity.*;
import com.main.org.testng.fileutil.FileUtil;

@SpringBootApplication
public class TestNG_Runner {
	private static JAXBContext context;
	private static Marshaller marshaller;
	private FileUtil fileUtil;
	public static Logger log = Logger.getLogger(TestNG_Runner.class);

	public TestNG_Runner() {
		try {
			fileUtil = new FileUtil();
			context = JAXBContext.newInstance(Suite.class);
			marshaller = context.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			marshaller.setProperty(TestNG_Constants.XML_HEADER_PACKAGE, TestNG_Constants.XML_HEADERS);
			marshaller.setProperty(Marshaller.JAXB_FRAGMENT, true);
			// marshaller.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);
		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}

	public void writeToConsole(Suite suite) {
		try {
			marshaller.marshal(suite, System.out);
		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}

	public void writeToFile(Suite suite) {
		try {
			fileUtil.checkAndRemoveTestNG_XMLFile();
			marshaller.marshal(suite,
					new File(TestNG_Constants.TEST_NG_HOMEDIRECTORY + TestNG_Constants.TEST_NG_FILENAME));
			System.out.println("Successfully wirtten");
		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}

	public Suite createSuiteObject(List<String> classesName, String suiteName, String testName) {
		List<Clas> clas = new ArrayList<Clas>();
		for (String cn : classesName) {
			Clas c = new Clas(cn);
			clas.add(c);
		}
		Classes classes = new Classes(clas);
		Test test = new Test(testName, createListOfClasses(classes));
		Listeners listeners = new Listeners(createListOfListener());
		return new Suite(suiteName, createListOfTest(test), listeners);
	}

	public List<Classes> createListOfClasses(Classes classes) {
		List<Classes> listOfClasses = new ArrayList<Classes>();
		listOfClasses.add(classes);
		return listOfClasses;
	}

	public List<Test> createListOfTest(Test test) {
		List<Test> listOfTest = new ArrayList<Test>();
		listOfTest.add(test);
		return listOfTest;
	}

	public List<Listener> createListOfListener() {
		List<Listener> listOfListener = new ArrayList<Listener>();
		// listOfListener.add(new
		// Listener(TestNG_Constants.HTML_REPOTER_LISTENER));
		// listOfListener.add(new
		// Listener(TestNG_Constants.JUNIT_REPOTER_LISTENER));
		listOfListener.add(new Listener(TestNG_Constants.EXTENT_REPOTER_LISTENER));
		return listOfListener;
	}

	public void runTestNG(String path) {
		log.info("Executing Scripts present in TestNg PAth = "+path);
		TestNG testNG = new TestNG();
		List<String> suiteFile = new ArrayList<String>();
		suiteFile.add(path);
		testNG.setTestSuites(suiteFile);
		testNG.run();
	}

	public void createTestNG_XML(List<String> classesName) {
		Suite suite = createSuiteObject(classesName, TestNG_Constants.SUITE_NAME, TestNG_Constants.TEST_NAME);
		writeToFile(suite);
	}

	public void executeTestNG_XMLAndGenReport(List<String> classesName, String suiteName) {
		try {
			Suite suite = createSuiteObject(classesName, suiteName, TestNG_Constants.TEST_NAME);
			log.info("created suite object");
			writeToFile(suite);
			log.info("created xml");
			fileUtil.moveToDestinationFolder(TestNG_Constants.EXTENT_REPORT_PATH,
					TestNG_Constants.BACKUP_REPORT_PATH + "Report-(" + System.currentTimeMillis() + ")");
			log.info("moved to Old Report Folder");
			log.info("Report path "+TestNG_Constants.REPORT_FOLDER);
			fileUtil.deleteInnerFiles(TestNG_Constants.REPORT_FOLDER);
			fileUtil.deleteDirectory(TestNG_Constants.STATIC_FOLDER + TestNG_Constants.EXTENT_REPORT_CSS_FOLDER_NAME);
			log.info("cleaned up report in class path");
			runTestNG(TestNG_Constants.TEST_NG_HOMEDIRECTORY + TestNG_Constants.TEST_NG_FILENAME);
			log.info("Completed running");
			fileUtil.copyFileToDirectory(TestNG_Constants.EXTENT_REPORT_PATH + TestNG_Constants.EXTENT_REPORT_NAME,
			TestNG_Constants.REPORT_FOLDER);
			Thread.sleep(3000);
			fileUtil.copyDirectory(TestNG_Constants.EXTENT_REPORT_PATH + TestNG_Constants.EXTENT_REPORT_CSS_FOLDER_NAME,
			TestNG_Constants.STATIC_FOLDER);
			Thread.sleep(1000);
			fileUtil.copyDirectory(TestNG_Constants.EXTENT_REPORT_PATH + TestNG_Constants.EXTENT_REPORT_SCREENSHOT_FOLDER_NAME,
					TestNG_Constants.STATIC_FOLDER);
			Thread.sleep(3000);
			log.info("Report written to class path");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
}
