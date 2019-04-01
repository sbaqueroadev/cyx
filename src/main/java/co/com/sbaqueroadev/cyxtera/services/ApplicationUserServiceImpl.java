package co.com.sbaqueroadev.cyxtera.services;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import co.com.sbaqueroadev.cyxtera.dao.ApplicationUserRepository;
import co.com.sbaqueroadev.cyxtera.dao.RoleRepository;
import co.com.sbaqueroadev.cyxtera.exceptions.UsernameExistsException;
import co.com.sbaqueroadev.cyxtera.model.ApplicationUserInterface;
import co.com.sbaqueroadev.cyxtera.model.implementation.ApplicationUser;

import java.util.UUID;

@Service
public class ApplicationUserServiceImpl implements ApplicationUserInterface {
	
	private ApplicationUserRepository applicationUserRepository;
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	private RoleRepository roleRepository;

	public ApplicationUserServiceImpl(ApplicationUserRepository applicationUserRepository,
			BCryptPasswordEncoder bCryptPasswordEncoder, RoleRepository roleRepository) {
		this.applicationUserRepository = applicationUserRepository;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
		this.roleRepository = roleRepository;
	}

	@Override
	public ApplicationUser registerNewUserAccount(ApplicationUser applicationUser) throws UsernameExistsException {
		if (usernameExist(applicationUser.getUsername())) {
	        throw new UsernameExistsException
	          ("There is an account with that username: " + applicationUser.getUsername());
	    }
	    ApplicationUser user = new ApplicationUser();
	    user.setUsername(applicationUser.getUsername());
	    user.setPassword(bCryptPasswordEncoder.encode(applicationUser.getPassword()));
	    user.setEmail(applicationUser.getEmail());
	    user.setFirstName(applicationUser.getFirstName());
		user.setLastName(applicationUser.getLastName());
	    user.setRole(applicationUser.getRole());
	    user.setId(UUID.randomUUID().toString());
	    return applicationUserRepository.save(user);
	}

	@Override
	public ApplicationUser updateUserAccount(ApplicationUser user) throws NullPointerException {
		if (!usernameExist(user.getUsername())) {
			throw new NullPointerException
					("There is no account with that username: " + user.getUsername());
		}
		ApplicationUser dbUser = findByUserName(user.getUsername());
		dbUser.setPassword(user.getPassword());
		dbUser.setEmail(user.getEmail());
		dbUser.setRole(user.getRole());
		return applicationUserRepository.save(dbUser);
	}

	/**
	 * @param userName
	 * @return The Application User
	 */
	@Override
	public ApplicationUser findByUserName(String userName) {
		return applicationUserRepository.findByUsername(userName);
	}

	@Override
	public ApplicationUser[] findByRole(String role){
		return applicationUserRepository.findByRole(role);
	}

	private boolean usernameExist(String username) {
		if( applicationUserRepository.findByUsername(username) != null ){
			return true;
		}else{
			return false;
		}
	}
	
}
