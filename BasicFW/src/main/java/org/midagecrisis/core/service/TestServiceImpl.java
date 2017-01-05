package org.midagecrisis.core.service;

import org.midagecrisis.core.dao.TestDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestServiceImpl implements TestService {
	@Autowired
	TestDao testDaoImpl;

	public String test() {
		return testDaoImpl.test();
	}
}