package com.excilys.cdb.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class ComputerDTO {
	
	
	@NotNull(message = "id cannot be null")
	private long id ;
	@NotNull(message = "Name cannot be null")
	@Size(min = 3, message 
    = "Name must be bigger than 3 characters")
	private String name ;
	@NotNull(message = "Introduced cannot be null")
	private String introduced ;
	@NotNull(message = "Discontinued cannot be null")
	private String discontinued;
	@NotNull(message = "Company cannot be null")
	private String company ;
	
	public ComputerDTO(long id, String name, String introduced, String discontinued, String company) {
		super();
		this.id = id;
		this.name = name;
		this.introduced = introduced;
		this.discontinued = discontinued;
		this.company = company;
	}
	public ComputerDTO() {
		super();
		this.id =-1L;
		this.name = "";
		this.introduced = "";
		this.discontinued = "";
		this.company = "";
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getIntroduced() {
		return introduced;
	}
	public void setIntroduced(String introduced) {
		this.introduced = introduced;
	}
	public String getDiscontinued() {
		return discontinued;
	}
	public void setDiscontinued(String discontinued) {
		this.discontinued = discontinued;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	
	static public class ComputerDTOBuilder {
		private Long id ;
		private String name ;
		private String introduced ;
		private String discontinued;
		private String company ;
		
		public Long getId() {
			return id;
		}
		public ComputerDTOBuilder setId(Long id) {
			this.id = id;
			return this;
		}
		public ComputerDTOBuilder setName(String name) {
			this.name = name;
			return this;
		}
		public ComputerDTOBuilder setIntroduced(String introduced) {
			this.introduced = introduced;
			return this;
		}
		public ComputerDTOBuilder setDiscontinued(String discontinued) {
			this.discontinued = discontinued;
			return this;
		}
		public ComputerDTOBuilder setCompany(String company) {
			this.company = company;
			return this;
		}
		
		public ComputerDTO build() {
			return new ComputerDTO((long)id,name,introduced,discontinued,company);
		}	
	}

	@Override
	public String toString() {
		return "ComputerDTO [id=" + id + ", name=" + name + ", introduced=" + introduced + ", discontinued="
				+ discontinued + ", company=" + company + "]";
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((company == null) ? 0 : company.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ComputerDTO other = (ComputerDTO) obj;
		if (company == null) {
			if (other.company != null)
				return false;
		} else if (!company.equals(other.company))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
}
