package com.excylis.cdb.console.controller;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

import com.excilys.cdb.model.Company;
import com.excilys.cdb.model.Computer;

@Path("/cli/companies")
public class CompanyCliController {
	
	@GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public List<Company> retrieveAllCompany() {
		Client client = ClientBuilder.newClient();
		List<Company> computers =
	            client.target("http://localhost:8088/webapp").path("api-rest/companies")
	            .request(MediaType.APPLICATION_JSON)
	            .get(new GenericType<List<Company> >() {
	            });
	    return computers;} 
		
}