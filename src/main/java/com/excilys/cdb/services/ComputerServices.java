package com.excilys.cdb.services;

import java.util.List;
import java.util.Optional;
import java.util.ArrayList;

import com.excilys.cdb.DAOImpl.ComputerDAOImpl;
import com.excilys.cdb.DTO.ComputerDTO;
import com.excilys.cdb.model.Page;

public class ComputerServices {
	 
/**
 * Return a Computer DTO if is not null : using Optional and the method of the DAO 
 * @return ComputerDTO , DTO  useful for the view   
 */
 public static Optional<List<ComputerDTO>> getListComputerToShowService(){
	 
	 List<ComputerDTO> listComputerDTO = null;
	 ComputerDAOImpl daoComputer = new ComputerDAOImpl();
	 daoComputer.getList();

	 
	 return Optional.ofNullable(listComputerDTO);
 }
 /**
  * Add a Computer in the data base using the method of the DAO
  */
 public static void addComputerService() {
	 
 }
}
