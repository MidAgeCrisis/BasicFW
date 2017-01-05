package org.midagecrisis.core.dao;

import org.springframework.stereotype.Repository;

@Repository
public class TestDaoImpl implements TestDao {
	public String test() {
		return "hello word";
	}
}
