package co.com.sbaqueroadev.cyxtera.dao;

import org.springframework.data.mongodb.repository.MongoRepository;

import co.com.sbaqueroadev.cyxtera.model.implementation.ApplicationUser;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Optional;

@RepositoryRestResource(collectionResourceRel = "appUs", path = "appUs")
public interface ApplicationUserRepository extends MongoRepository<ApplicationUser, String> {

    Optional<ApplicationUser> findById(@Param("id")String id);

    ApplicationUser findByUsername(String username);

    ApplicationUser[] findByRole(String role);

    ApplicationUser save(ApplicationUser user);
}