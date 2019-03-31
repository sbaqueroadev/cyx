package co.com.sbaqueroadev.cyxtera.model.implementation;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.util.Collection;
import java.util.List;



public class Role {
	
	public static enum Roles{
		ADMIN("ROLE_ADMIN"),
		USER("ROLE_USER"),
		API("ROLE_API");

		public String rol;
		
		Roles(String name){
			this.rol = name;
		}
		
		public String getValue(){
			return this.rol;
		}
		
	}

	public static final String PREFIX = "ROLE_";

	@Id
	private String id;
	private String name;

	@DBRef
	private Collection<ApplicationUser> users;
	@DBRef
	private Collection<Privilege> privileges;

	public String getId() {
		return id;
	}

	/**
	 * @param name
	 * @param privileges
	 */
	public Role(String name, List<Privilege> privileges) {
		this.name = name;
		this.privileges = privileges;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Role() {
		super();
	}

	public Role(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Collection<ApplicationUser> getUsers() {
		return users;
	}

	public void setUsers(Collection<ApplicationUser> users) {
		this.users = users;
	}

	public Collection<Privilege> getPrivileges() {
		return privileges;
	}

	public void setPrivileges(Collection<Privilege> privileges) {
		this.privileges = privileges;
	}



}
