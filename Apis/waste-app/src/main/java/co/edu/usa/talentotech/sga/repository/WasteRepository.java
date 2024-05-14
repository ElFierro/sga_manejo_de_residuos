package co.edu.usa.talentotech.sga.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import co.edu.usa.talentotech.sga.model.Waste;
@Repository
public interface WasteRepository extends MongoRepository<Waste, String>{

}
