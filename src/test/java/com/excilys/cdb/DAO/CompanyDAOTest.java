package com.excilys.cdb.DAO;

import org.junit.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.excilys.cdb.DAOImpl.CompanyDAOImpl;
import com.excilys.cdb.model.Company;

public class CompanyDAOTest {
private static CompanyDAOImpl dao ;
private Company c ;	
	
	@BeforeClass
    public static void init() {
        dao = new CompanyDAOImpl();
    }
	
	@Before
	public void setUp() {
		c = new Company();
	}
	@After
    public void tearDown() {
    	
    }
	
	@Test
	public void creatTest () {
		c.setName("BenCompany");
		Assert.assertFalse(dao.creat(c) == 0);
	}

}
