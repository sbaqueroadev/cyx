package co.com.sbaqueroadev.cyxtera.model.implementation;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Collection;

@Document
public class Privilege {
	
	public static enum Privileges{
		READ("READ_PRIVILEGE"),
		WRITE("WRITE_PRIVILEGE"),
		API("API_PRIVILEGE");

		private Privilege value;
		
		private Privileges(String value) {
			this.value = new Privilege(value);
		}

		/**
		 * @return
		 */
		public Privilege getValue() {
			return this.value;
		}
	}

	@Id
	private String id;

	private String name;

	private Collection<Role> roles;



	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Privilege() {
		super();
	}

	public Privilege(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Collection<Role> getRoles() {
		return roles;
	}

	public void setRoles(Collection<Role> roles) {
		this.roles = roles;
	}


}
