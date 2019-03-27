package com.excilys.cdb.mapper;

import java.sql.Timestamp;
import java.util.Date;

import com.excilys.cdb.DTO.ComputerDTO;
import com.excilys.cdb.model.Company;
import com.excilys.cdb.model.Computer;

public class ComputerMapper {

	/**
	 * Cast Computer and Company to ComputerDTO
	 * @param computer
	 * @param company
	 * @return
	 */
	public static ComputerDTO mapDTO(Computer computer, String company) {
		ComputerDTO c = new ComputerDTO.ComputerDTOBuilder()
				.setId(computer.getId())
				.setName(computer.getName())
				.setIntroduced(computer.getIntroduced() != null ?computer.getIntroduced().toString() :  "")
				.setDiscontinued(computer.getDiscontinued() != null ?computer.getDiscontinued().toString() :"")
				.setCompany(company).build();
		return c;

	}

}
