package co.com.sbaqueroadev.cyxtera.model;

import co.com.sbaqueroadev.cyxtera.exceptions.UsernameExistsException;
import co.com.sbaqueroadev.cyxtera.model.implementation.ApplicationUser;

public interface ApplicationUserInterface {

	ApplicationUser registerNewUserAccount(ApplicationUser applicationUser) throws UsernameExistsException;

    ApplicationUser updateUserAccount(ApplicationUser user) throws NullPointerException;

    ApplicationUser findByUserName(String userName);

    ApplicationUser[] findByRole(String role);

}
