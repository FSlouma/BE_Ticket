package ticket.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ticket.model.Ticket;
@Repository
public interface TicketReposiory extends MongoRepository<Ticket, Integer> {
	//@Query(value ="{'email':?0 }")
    //public Optional<Ticket> getTicketByEmail(@Param("email")String email);
	public Ticket findByEmail(String email);
}
