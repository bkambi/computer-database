package com.excilys.cdb.DAO;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.excilys.cdb.DAOImpl.ComputerDAOImpl;
import com.excilys.cdb.model.Computer;

public class ComputerDAOTest  {
	private static ComputerDAOImpl dao ;
	
	
	@BeforeClass
    public static void init() {
        dao = new ComputerDAOImpl();
    }
	@Before
	public void setUp() {
		
	}
    @After
    public void tearDown() {
    	
    }
	
	@Test
	public void creatTest () {
//		Timestamp introduced = new Timestamp();
//		Computer c = new Computer("Excilys",);
//		dao.creat();
	}
	@Test
	public void getByIdTest () {
		
	}
	@Test
	public void getListTest () {
		
	}
	@Test
	public void updateTest () {
		
	}
	@Test
	public void deleteTest () {
		
	}
	

}
