package com.excilys.cdb.DAO;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import com.excilys.cdb.dao.CompanyDAO;
import com.excilys.cdb.dao.ComputerDAO;

@RunWith(MockitoJUnitRunner.class)
public class CompanyDAOTest {

	/**
	 * @InjectMocks Instantiates testing Object CompanyDAO and tries to inject fields annotated with
	 * @mock or @spy into private fields of testing object
	 * 
	 * @mock are a fack object
	 * @spy are a real object that you just spying or stubbing specific methods of it
	 */
	@Autowired
	@InjectMocks
	private CompanyDAO mockCompanyDAO;
	
	@Mock
	private JdbcTemplate jdbcTemplate ;
	
	@Mock
	private ComputerDAO computerDAO ;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@After
	public void tearDown() {

	}

	@Test
	public void testIsJDBCTemplateNull() {
		Assert.assertNotNull(mockCompanyDAO.getJdbcTemplate());
	}

}
