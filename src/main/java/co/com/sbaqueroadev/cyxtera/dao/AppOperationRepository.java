package co.com.sbaqueroadev.cyxtera.dao;

import co.com.sbaqueroadev.cyxtera.model.implementation.ApplicationUser;
import co.com.sbaqueroadev.cyxtera.model.implementation.operation.AppOperation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Optional;

public interface AppOperationRepository extends MongoRepository<AppOperation, String> {
    Optional<AppOperation> findById(@Param("id") String id);

    AppOperation save(AppOperation entity);
}