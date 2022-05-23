package ticket.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import ticket.model.Servicce;

@Repository
public interface ServiceRepository extends MongoRepository<Servicce, Integer>{

}
