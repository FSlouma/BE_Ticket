package ticket.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ticket.model.Ticket;
import ticket.repository.TicketReposiory;
@Service
public class TicketService {
	@Autowired
	private TicketReposiory repo;
	
	public List<Ticket> listAll(){
		return repo.findAll();
	}
	
	public void save(Ticket article) {
		repo.save(article);
	}
	
	public Ticket get(Integer id) {
		return repo.findById(id).get();
	}
	
	public void delete(Integer id) {
		repo.deleteById(id);
	}
}
