package ticket.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ticket.model.User;
@Repository
public interface UserRepository extends MongoRepository<User, String> {
    public User findByEmail(String email);

}
