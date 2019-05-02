package com.excilys.cdb.mapper;

import java.util.ArrayList;

import java.util.List;
import java.util.Optional;

import org.apache.log4j.Logger;

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
	 * 
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
			computer.setCompanyId(companyID);
			logger.info("Inside the method for mapping View Dto To Computer ...");
		} catch (NumberFormatException numberFormatException) {
			numberFormatException.printStackTrace();
			logger.error("Number Format Exception : fail to parse company id String to Long ");
		}
		return Optional.ofNullable(computer);
	}
	
	public static Optional<List<ComputerDTO>> mapListDTO(List<Computer>listComputer,List<Company>listCompany){
		List<ComputerDTO> listAllComputerDto = new ArrayList<ComputerDTO>();
		for (Computer computer : listComputer) {
			Optional<Company> optionalCompany = listCompany.stream()
					.filter(company -> company.getId() == computer.getCompanyId()).findFirst();
			String nameCompnay = optionalCompany.isPresent() ? optionalCompany.get().getName() : "";
			listAllComputerDto.add(ComputerMapper.mapDTO(computer, nameCompnay));
		}
		return Optional.ofNullable(listAllComputerDto);
	}
	
	public static Optional<List<Computer>>mapList(List<ComputerDTO> listAllComputerDto,List<Computer>listAllComputer){
		List<Computer> listReturnComputer = new ArrayList<Computer>();
		for (ComputerDTO computerDtofilter : listAllComputerDto) {
			Optional<Computer> optionalComputer = listAllComputer.stream()
					.filter(computer -> computer.getId() == computerDtofilter.getId()).findFirst();
			if (optionalComputer.isPresent()) {
				listReturnComputer.add(optionalComputer.get());
			}
		}
		return Optional.ofNullable(listReturnComputer);
	}

}
