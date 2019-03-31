package co.com.sbaqueroadev.cyxtera.dao;

import org.springframework.data.mongodb.repository.MongoRepository;

import co.com.sbaqueroadev.cyxtera.model.implementation.Privilege;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.webmvc.RepositoryRestController;

@RepositoryRestResource( collectionResourceRel = "privilege", path = "privilege")
public interface PrivilegeRepository extends MongoRepository<Privilege, String> {
    Privilege findByName(@Param("name") String name);
}