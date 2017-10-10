package com.main.org.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.main.org.entity.TestMethod;

@Repository
public interface TestMethodRepository extends JpaRepository<TestMethod, Integer> {
	public TestMethod  findBytestMethodId(int testMethodId);
	public TestMethod findBytestMethodName(String testMethodName);
}
