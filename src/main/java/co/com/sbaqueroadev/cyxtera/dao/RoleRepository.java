package co.com.sbaqueroadev.cyxtera.dao;

import org.springframework.data.mongodb.repository.MongoRepository;

import co.com.sbaqueroadev.cyxtera.model.implementation.Role;
import org.springframework.data.repository.query.Param;

public interface RoleRepository extends MongoRepository<Role, String> {
    	Role findByName(@Param("name") String name);
}