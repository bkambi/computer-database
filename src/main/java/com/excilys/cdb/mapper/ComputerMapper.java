package com.excilys.cdb.mapper;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.excilys.cdb.dao.ComputerDAO;
import com.excilys.cdb.dto.ComputerDTO;
import com.excilys.cdb.model.Company;
import com.excilys.cdb.model.Computer;
import com.excilys.cdb.util.Parser;

public class ComputerMapper {

	private static Logger logger = Logger.getLogger(ComputerMapper.class);

	/**
	 * Cast Computer and Company to ComputerDTO
	 * 
	 * @param computer
	 * @param company
	 * @return
	 */
	public static ComputerDTO mapDTO(Computer computer, String company) {

		String introduced = computer.getIntroduced() != null ? computer.getIntroduced().toString().split(" ")[0] : "";
		String discontinued = computer.getDiscontinued() != null ? computer.getDiscontinued().toString().split(" ")[0]
				: "";
		ComputerDTO ComputerDto = new ComputerDTO.ComputerDTOBuilder().setId(computer.getId()).setName(computer.getName())
				.setIntroduced(introduced).setDiscontinued(discontinued).setCompany(company).build();
		return ComputerDto;

	}

	public static Optional<Computer> mapViewDtoToComputer(ComputerDTO computerDto) {

		String computerName = computerDto.getName();
		String introduced = computerDto.getIntroduced();
		String discontinued = computerDto.getDiscontinued();
		String companyId = computerDto.getCompany();

		Computer computer = new Computer();

		computer.setName(computerName);
		computer.setIntroduced(Parser.stringToTimestamp(introduced).get());
		computer.setDiscontinued(Parser.stringToTimestamp(discontinued).get());
		Long companyID = null;

		try {
			companyID = Long.parseLong(companyId);
			computer.setCompany_id(companyID);
			logger.info("Inside the method for mapping View Dto To Computer ...");
		} catch (NumberFormatException numberFormatException) {
			numberFormatException.printStackTrace();
			logger.error("Number Format Exception : fail to parse company id String to Long ");
		}
		return Optional.ofNullable(computer);
	}

}
