package co.com.sbaqueroadev.cyxtera.model.implementation;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


public class ApplicationUser {

    @Id
    private String id;
    private String username;
    private String password;
    @JsonIgnore
    private String email;
    private String name;
    private String firstName;
    private String lastName;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @DBRef
    private Role role;

    public ApplicationUser(User user) {
		this.username = user.getUser();
		this.setPassword(user.getPass());
		this.firstName = user.getFirstName();
		this.lastName = user.getLastName();
	}

	public ApplicationUser() {
	}

	public String getId() {
        return id;
    }
    
    public void setId(String id) {
			this.id = id;
		}

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastname) {
        this.lastName = lastname;
    }
}