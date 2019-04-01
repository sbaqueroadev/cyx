package co.com.sbaqueroadev.cyxtera.dao;

import co.com.sbaqueroadev.cyxtera.model.implementation.Calculation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.Optional;

//Mongo recibe una clase que representa una entrada en la BD
// y el tipo de dato del Id que para el caso es String
public interface CalculationRepository extends MongoRepository<Calculation, String> {

    Optional<Calculation> findById(@Param("id") String id);

}