package co.com.sbaqueroadev.cyxtera.dao;

import co.com.sbaqueroadev.cyxtera.model.implementation.SessionCalculation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;
import java.util.Optional;

@RepositoryRestResource(collectionResourceRel = "session")
public interface SessionCalculationRepository extends MongoRepository<SessionCalculation, String> {

    Optional<SessionCalculation> findById(@Param("id") String id);

    List<SessionCalculation> findAll();
}
