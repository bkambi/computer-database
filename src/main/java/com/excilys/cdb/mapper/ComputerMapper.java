package com.excilys.cdb.mapper;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import com.excilys.cdb.dto.ComputerDTO;
import com.excilys.cdb.model.Company;
import com.excilys.cdb.model.Computer;
import com.excilys.cdb.util.Parser;

public class ComputerMapper {

	/**
	 * Cast Computer and Company to ComputerDTO
	 * @param computer
	 * @param company
	 * @return
	 */
	public static ComputerDTO mapDTO(Computer computer, String company) {
	
		String introduced = computer.getIntroduced() != null ?computer.getIntroduced().toString().split(" ")[0] :  "";
		String discontinued = computer.getDiscontinued() != null ?computer.getDiscontinued().toString().split(" ")[0] :"";
		ComputerDTO c = new ComputerDTO.ComputerDTOBuilder()
				.setId(computer.getId())
				.setName(computer.getName())
				.setIntroduced(introduced)
				.setDiscontinued(discontinued)
				.setCompany(company).build();
		return c;

	}
	
	public static Optional<Computer> mapViewDtoToComputer(HttpServletRequest request) {
		
		String computerName = request.getParameter("computerName");
		String introduced = request.getParameter("introduced");
		String discontinued = request.getParameter("discontinued");
		String companyId = request.getParameter("companyId");

		Computer computer = new Computer();

		computer.setName(computerName);
		computer.setIntroduced(Parser.stringToTimestamp(introduced).get());
		computer.setDiscontinued(Parser.stringToTimestamp(discontinued).get());
		Long companyID = null;

		try {
			companyID = Long.parseLong(companyId);
			computer.setCompany_id(companyID);
		} catch (NumberFormatException numberFormatException) {
			numberFormatException.printStackTrace();
		}
		return Optional.ofNullable(computer);
	}

}
