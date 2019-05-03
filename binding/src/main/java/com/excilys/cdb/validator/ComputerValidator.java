package com.excilys.cdb.validator;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import org.apache.log4j.Logger;
import com.excilys.cdb.dto.ComputerDTO;
import com.excilys.cdb.exception.InvalidDataComputerException;

public class ComputerValidator {
		
	public ComputerValidator(ComputerDTO computerDto,Logger logger) throws InvalidDataComputerException {
		ValidatorFactory factory= Validation.buildDefaultValidatorFactory();
		Validator validator= factory.getValidator();
		Set<ConstraintViolation<ComputerDTO>> violations = validator.validate(computerDto);
		for (ConstraintViolation<ComputerDTO> violation : violations) {
			logger.error(violation.getMessage()); 
		}
		if(!violations.isEmpty()) {
			throw new InvalidDataComputerException();
		}
	}
	
}
