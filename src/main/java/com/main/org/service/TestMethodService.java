package com.main.org.service;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.main.org.entity.TestMethod;
import com.main.org.repository.TestMethodRepository;

@Service
public class TestMethodService {
	@Autowired
	TestMethodRepository testMethodRepository;

	public List<TestMethod> getAllTestMethod() {
		List<TestMethod> tm = new ArrayList<>();
		tm = testMethodRepository.findAll();
		return tm;
	}

	public boolean addTestMethod(TestMethod testMethod) {
		if ((!(testMethod.getTestMethodName().isEmpty()))
				&& (!(checkTestMethodNamePresent(testMethod.getTestMethodName())))) {
			testMethodRepository.save(testMethod);
			return true;
		} else
			return false;
	}

	public boolean updateTestMethod(TestMethod testMethod) {
		if ((!(testMethod.getTestMethodName().isEmpty())) && (!(validateSameDataPresent(testMethod)))) {
			testMethodRepository.save(testMethod);
			return true;
		} else
			return false;
	}

	public void deleteTestMethod(int[] testMethodId) {
		for (int i = 0; i < testMethodId.length; i++) {
			if (checkTestMethodIdPresent(testMethodId[i]))
				testMethodRepository.deleteById(testMethodId[i]);
		}
	}

	public List<String> createListOfData(int[] testMethodId) {
		List<String> data = new ArrayList<>();
		for (int i = 0; i < testMethodId.length; i++) {
			Optional<TestMethod> optionalTestMethod = testMethodRepository.findById(testMethodId[i]);
			TestMethod testMethod = optionalTestMethod.get();
			data.add(testMethod.getTestMethodName());
		}
		return data;
	}

	public boolean checkTestMethodIdPresent(int id) {
		TestMethod tm = testMethodRepository.findBytestMethodId(id);
		if (tm == null)
			return false;
		else
			return true;
	}

	public boolean checkTestMethodNamePresent(String name) {
		TestMethod tm = testMethodRepository.findBytestMethodName(name);
		if (tm == null)
			return false;
		else
			return true;
	}

	public boolean validateSameDataPresent(TestMethod testMethod) {
		TestMethod actualTestMethod = testMethodRepository.findBytestMethodId(testMethod.getTestMethodId());
//		System.out.println("name="+(testMethod.getTestMethodName().equals(actualTestMethod.getTestMethodName())) +"Description="+(testMethod.getTestMethodDescription().equals(actualTestMethod.getTestMethodDescription())));
		if ((testMethod.getTestMethodName().equals(actualTestMethod.getTestMethodName())) && (testMethod.getTestMethodDescription().equals(actualTestMethod.getTestMethodDescription()))){
			return true;
		}
		else return false;
	}
}
