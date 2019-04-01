package co.com.sbaqueroadev.cyxtera.model;

import co.com.sbaqueroadev.cyxtera.model.implementation.Role;

/*
* @author: gasdsba - sbaqueroa@gmail.com
* PrivilegeInterface:
*/
public interface PrivilegeInterface {
	public Role findByName(String name);
}
