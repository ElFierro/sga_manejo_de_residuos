package co.edu.usa.talentotech.sga.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import co.edu.usa.talentotech.sga.model.User;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
	
	boolean existsByEmail(String Email);
	
	boolean existsByClientId(String ClientId);
	
	boolean existsByClientSecret(String ClientSecret);

}
