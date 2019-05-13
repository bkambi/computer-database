package com.excylis.cdb.console.controller;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;

import com.excilys.cdb.model.Computer;

@Path("/cli/computers")
public class ComputerCliController {
	
	private  Logger logger = Logger.getLogger(ComputerCliController.class);
	private String stringRetour="";
	
	/**
	 * This method print all computer on the database
	 * 
	 * @return String list of all Computer
	 */
	@GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public List<Computer> retrieveAllComputers() {
		Client client = ClientBuilder.newClient();
		List<Computer> computers =
	            client.target("http://localhost:8088/webapp").path("api-rest/computers")
	            .request(MediaType.APPLICATION_JSON)
	            .get(new GenericType<List<Computer> >() {
	            });
	    return computers;} 

	/**
	 * This method print a details of a specific computer on the database
	 * 
	 * @return String details of a specific computer
	 */
	@POST
	@Produces({MediaType.APPLICATION_JSON})
	public Computer retrieveOneComputer(Long id) {
		Client client = ClientBuilder.newClient();
		return client.target("http://localhost:8088/webapp").path("api-rest/computer/"+id)
				.request(MediaType.APPLICATION_JSON).get(new GenericType<Computer>() {});
	}
	
	

	/**
	 * Create a computer and add it to the database
	 */
	@POST
	@Produces({MediaType.APPLICATION_JSON})
	public void add(Computer computer) {
		Client client = ClientBuilder.newClient();
		WebTarget webTarget = client.target("http://localhost:8088/webapp").path("api-rest/add-computer");
		Invocation.Builder invocationBuilder =  webTarget.request(MediaType.APPLICATION_JSON);
		Response response = invocationBuilder.post(Entity.entity(computer, MediaType.APPLICATION_JSON));
		System.out.println(response.getStatus());
		System.out.println(response.readEntity(String.class));
	}
	public void addComputer() {
		
		Computer computer = new Computer();
		String thirdEnter = "";
		thirdEnter = lireEntrerClavier();
		if (!thirdEnter.equals("q")) {
			String[] computerInfo = thirdEnter.split(";");
			if (computerInfo.length == 4) {
				try {

					String name = computerInfo[0];
					Timestamp introduced = castStringToTimestamp(computerInfo[1]);
					Timestamp discontinued = castStringToTimestamp(computerInfo[2]);
					Long companyId = Long.parseLong(computerInfo[3]);

					//computer.setId(-1L);
					computer.setName(name);
					computer.setIntroduced(introduced);
					computer.setDiscontinued(discontinued);
					//computer.setCompanyId(companyId);
					add(computer);
					System.out.println("creat ook !");
					logger.info("creat ook !");
				} catch (NumberFormatException e) {
					System.out.println("Format Company_id incorrect entrer un nombre");
					logger.error(stringRetour);
					e.printStackTrace();
				}
			} else {
				System.out.println("Remplir correctement tous les champs");
				logger.error("Remplir correctement tous les champs");
			}
		}
	}



	/**
	 * Update a computer in the database
	 */
	@PUT
	@Produces({MediaType.APPLICATION_JSON})
	public void update(Long id , Computer computer) {
		Client client = ClientBuilder.newClient();
		WebTarget webTarget = client.target("http://localhost:8088/webapp").path("api-rest/update-computer/"+id);
		Invocation.Builder invocationBuilder =  webTarget.request(MediaType.APPLICATION_JSON);
		Response response = invocationBuilder.put(Entity.entity(computer, MediaType.APPLICATION_JSON));
		System.out.println(response.getStatus());
		System.out.println(response.readEntity(String.class));
	}
	public void updateComputer(Long id) {
		Computer computer = new Computer();
		String thirdEnter = "";
		thirdEnter = lireEntrerClavier();
		if (!thirdEnter.equals("q")) {
			String[] computerInfo = thirdEnter.split(";");
			if (computerInfo.length == 4) {
				try {

					String name = computerInfo[0];
					Timestamp introduced = castStringToTimestamp(computerInfo[1]);
					Timestamp discontinued = castStringToTimestamp(computerInfo[2]);
					Long companyId = Long.parseLong(computerInfo[3]);

					computer.setName(name);
					computer.setIntroduced(introduced);
					computer.setDiscontinued(discontinued);
					computer.setCompanyId(companyId);
					update(id ,computer);
					System.out.println("Update ok ! ");
					logger.info("Update ok ! ");
				} catch (NumberFormatException e) {
					System.out.println("Format Company_id incorrect entrer un nombre");
					logger.error(stringRetour);
					e.printStackTrace();
				}
			} else {
				System.out.println("Remplir correctement tous les champs");
				logger.error("Remplir correctement tous les champs");
			}
		}
	}

	/**
	 * Delete a computer on the database
	 * 
	 * @return String , information of the method
	 */
	@DELETE
	@Produces({MediaType.APPLICATION_JSON})
	private void delete(Long id) {
		Client client = ClientBuilder.newClient();
		WebTarget webTarget = client.target("http://localhost:8088/webapp").path("api-rest/delete-computer/"+id);
		Invocation.Builder invocationBuilder =  webTarget.request(MediaType.APPLICATION_JSON);
		Response response = invocationBuilder.delete();
		System.out.println(response.getStatus());
		System.out.println(response.readEntity(String.class));
	}
	public String deleteComputer() {
		stringRetour = "";
		String forthEnter = "";
		forthEnter = lireEntrerClavier();
		if (!forthEnter.equals("q")) {
			try {
				Long id = Long.parseLong(forthEnter);
				delete(id);
				stringRetour = "Computer delete \n";
				logger.info(stringRetour);
			} catch (NumberFormatException e) {
				e.printStackTrace();
				stringRetour = "deleteComputerInDatabase : Number Format Exception \n";
				logger.error(stringRetour);
			}
		}
		return stringRetour;
	}

	

	/**
	 * Cast String time to Timestamp
	 * 
	 * @param dateString
	 * @return
	 */
	public Timestamp castStringToTimestamp(String dateString) {
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date date = null;
		try {
			date = dateFormat.parse(dateString);
		} catch (ParseException e) {
			stringRetour = "Format de la date incorrect";
			logger.error(stringRetour);
			e.printStackTrace();
		}
		long time = date.getTime();
		return new Timestamp(time);
	}

	/**
	 * Read the enter keybord
	 * 
	 * @return
	 */
	public String lireEntrerClavier() {
		Scanner sc = new Scanner(System.in);
		return sc.nextLine();
}
	
	
}