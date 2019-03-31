package co.com.sbaqueroadev.cyxtera.config;


import co.com.sbaqueroadev.cyxtera.dao.ApplicationUserRepository;
import co.com.sbaqueroadev.cyxtera.dao.PrivilegeRepository;
import co.com.sbaqueroadev.cyxtera.dao.RoleRepository;
import co.com.sbaqueroadev.cyxtera.model.implementation.ApplicationUser;
import co.com.sbaqueroadev.cyxtera.model.implementation.Privilege;
import co.com.sbaqueroadev.cyxtera.model.implementation.Privilege.Privileges;
import co.com.sbaqueroadev.cyxtera.model.implementation.Role;
import co.com.sbaqueroadev.cyxtera.model.implementation.Role.Roles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;

@Component
public class InitialDataLoader implements ApplicationListener<ContextRefreshedEvent> {

	boolean alreadySetup = false;

	@Autowired
	private ApplicationUserRepository applicationUserRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private PrivilegeRepository privilegeRepository;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;


	@Override
	@Transactional
	public void onApplicationEvent(ContextRefreshedEvent event) {

		if (alreadySetup)
			return;
		
		Privilege write = createPrivilegeIfNotFound(Privileges.WRITE.getValue());
		Privilege read = createPrivilegeIfNotFound(Privileges.READ.getValue());
		Privilege api = createPrivilegeIfNotFound(Privileges.API.getValue());
		
		Role adminRole = createRoleIfNotFound(new Role(Roles.ADMIN.getValue(),Arrays.asList(read, write, api)));
		Role userRole =
		createRoleIfNotFound(new Role(Roles.USER.getValue(),Arrays.asList(read, write)));
		Role apiRole = createRoleIfNotFound(
				new Role(Roles.API.getValue(), Arrays.asList(api)));


		ApplicationUser admin = new ApplicationUser();
		admin.setPassword(bCryptPasswordEncoder.encode("admin"));
		admin.setUsername("admin");
		admin.setEmail("admin@test.com");
		admin.setId("adminUserEx");
		admin.setRole(adminRole);
		admin = createUserIfNotFound(admin);

		ApplicationUser apiUser = new ApplicationUser();
		apiUser.setPassword(bCryptPasswordEncoder.encode("api"));
		apiUser.setUsername("api");
		apiUser.setEmail("api@test.com");
		apiUser.setId("apiUserEx");
		apiUser.setRole(apiRole);
		apiUser = createUserIfNotFound(apiUser);

		alreadySetup = true;
	}
	private Privilege createPrivilegeIfNotFound(Privilege priv) {

		Privilege privilege = privilegeRepository.findByName(priv.getName());
		if (privilege == null) {
			privilege = new Privilege(priv.getName());
			privilegeRepository.save(privilege);
		}
		return privilege;
	}

	private Role createRoleIfNotFound(Role rol) {

		Role role = roleRepository.findByName(rol.getName());
		if (role == null) {
			role = new Role(rol.getName());
			role.setPrivileges(rol.getPrivileges());
			roleRepository.save(role);
		}
		return role;
	}

	private ApplicationUser createUserIfNotFound(
			ApplicationUser appUser) {

		ApplicationUser user = applicationUserRepository.findByUsername(appUser.getUsername());
		if (user == null) {
			user = appUser;
			applicationUserRepository.save(user);
		}
		return user;
	}
}