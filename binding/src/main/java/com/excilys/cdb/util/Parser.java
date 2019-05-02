package com.excilys.cdb.util;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

public class Parser {
	
	/**
	 * Parse string format date to TimesTamp
	 * @param stringToParse
	 * @return Optional<Timestamp> , if is not null
	 */
	public static Optional<Timestamp> stringToTimestamp(String stringToParse) {
		Timestamp timestamp = null ;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyy-MM-dd");
			Date parseDate =  sdf.parse(stringToParse);
			timestamp = new java.sql.Timestamp(parseDate.getTime());
		}catch(ParseException pe) {
			pe.printStackTrace();
		}
		return Optional.ofNullable(timestamp);
	}

}
