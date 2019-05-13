package com.excilys.cdb.controller.rest;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.excilys.cdb.dao.dao.CompanyDAO;
import com.excilys.cdb.dto.ComputerDTO;
import com.excilys.cdb.model.Company;
import com.excilys.cdb.model.Computer;
import com.excilys.cdb.services.ComputerRestServices;
import com.excilys.cdb.services.CompanyRestServices;

@RestController
@RequestMapping(value = "/api-rest")
public class ComputerRestController {

	Logger logger = Logger.getLogger(ComputerRestController.class);

	@Autowired
	private ComputerRestServices computerRestServices;

	@Autowired
	private CompanyRestServices companyRestServices;
	
	@Autowired
	Pageable newPageable;
	
	@GetMapping("/companies")
	public ResponseEntity<List<Company>> getCompanies() {

		logger.info(" inside DashboardController : getCompanies() method");

		return companyRestServices.findAllCompanies();
	}

	@GetMapping("/computers")
	public ResponseEntity<List<Computer>> getDashboardPage() {

		logger.info(" inside DashboardController : getDashboardPage() method");

		return computerRestServices.findAllComputers();
	}
	@GetMapping("/computer/{id}")
	public ResponseEntity<Computer> getComputerById(@PathVariable Long id) {

		logger.info(" inside DashboardController : getComputerById(Long id) method");
	
		return computerRestServices.findOneComputer(id);
	}

	@GetMapping("/computers/filter")
	public ResponseEntity<Page<Computer>> getDashboardPageFilter(
			@RequestParam(value = "orderBy", required = false) String orderBy,
			@RequestParam(value = "sorted", required = false) String sorted,
			@RequestParam(value = "search", required = false) String search,
			@RequestParam(value = "size", required = false) String size,
			@RequestParam(value = "arrayIndice", required = false) String arrayIndice,
			@RequestParam(value = "indice", required = false) String indice) {

		logger.info(" inside DashboardController : getDashboardPageFilter() method");
		return computerRestServices.findAllComputersByName(newPageable, orderBy, sorted, search, size, arrayIndice,
				indice);
	}

	@PostMapping(value = "/add-computer")
	public void addComputer(@RequestBody Computer computer) {
		logger.info("inside addComputer method ");
		computerRestServices.saveComputer(computer);
	}

	@PutMapping(value = "/update-computer/{id}")
	public void updateComputer(@PathVariable Long id, @RequestBody Computer computer) {
		logger.info("inside addComputer editComputer ");
		computer.setId(id);
		computerRestServices.saveComputer(computer);
	}

	@DeleteMapping(value = "/delete-computer/{id}")
	public void deleteComputer(@PathVariable Long id) {
		logger.info("inside addComputer editComputer ");
		computerRestServices.deleteComputer(id);
	}

}
