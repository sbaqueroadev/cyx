package co.com.sbaqueroadev.cyxtera.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.com.sbaqueroadev.cyxtera.dao.RoleRepository;
import co.com.sbaqueroadev.cyxtera.model.RoleInterface;
import co.com.sbaqueroadev.cyxtera.model.implementation.Role;

/*
 * @author: gasdsba - sbaqueroa@gmail.com
 * RoleServiceImpl:
 */
@Service
public class RoleServiceImpl implements RoleInterface {

	@Autowired
	private RoleRepository roleRepository;

	public RoleServiceImpl() {
		super();
	}

	/* (non-Javadoc)
	 * @see co.com.sbaqueroadev.cyxtera.model.RoleInterface#findByName(java.lang.String)
	 */
	@Override
	public Role findByName(String name) {
		return roleRepository.findByName(name);
	}

}
