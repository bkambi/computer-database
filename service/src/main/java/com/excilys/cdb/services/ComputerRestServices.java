package com.excilys.cdb.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.excilys.cdb.dao.repositories.ComputerRepository;
import com.excilys.cdb.exception.NoDataFoundException;
import com.excilys.cdb.model.Computer;

@Service
public class ComputerRestServices {

	@Autowired
	ComputerRepository computerRepository;

	public ResponseEntity<List<Computer>> findAllComputers() {
		List<Computer> listComputers = computerRepository.findAll();
		return new ResponseEntity<List<Computer>>(listComputers, HttpStatus.OK);

	}

	public ResponseEntity<Page<Computer>> findAllComputersByName(Pageable pageable, String orderBy, String sorted,
			String search, String size, String arrayIndice, String indice) {
		Page<Computer> listComputers = null;
		
		Sort sort = Sort.by("name");
		
		if (!("".equals(orderBy))) {
				sort = Sort.by(orderBy);
			if (!("".equals(sorted))) {
				if(sorted.equals("asc")) {
					sort = sort.ascending();
				}else if(sorted.equals("desc")){
					sort = sort.ascending();
				}
			}
		}
		if(!("".equals(indice)) & !("".equals(size))) {
			pageable = PageRequest.of(Integer.valueOf(indice), Integer.valueOf(size),sort);
		}else {
			pageable = PageRequest.of(Integer.valueOf(0), Integer.valueOf(50),sort);
		}
		
		if (!("".equals(search)) ) {

			listComputers = computerRepository.findByNameContains(search, pageable);
		}else {
			listComputers = computerRepository.findByNameContains("", pageable);
		}

		return new ResponseEntity<Page<Computer>>(listComputers, HttpStatus.OK);

	}

	public void saveComputer(Computer computer) {
		computerRepository.save(computer);
	}

	public void deleteComputer(Long id) {
		computerRepository.deleteById(id);
	}

	public ResponseEntity<Computer> findOneComputer(Long id) {
		Computer computer = computerRepository.findById(id).get();
		return 	new ResponseEntity<Computer>(computer, HttpStatus.OK);
	}

}
