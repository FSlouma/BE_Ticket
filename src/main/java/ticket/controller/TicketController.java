package ticket.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.hibernate.hql.ast.origin.hql.parse.HQLParser.standardFunction_return;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ticket.model.Servicce;
import ticket.model.Ticket;
import ticket.repository.ServiceRepository;
import ticket.repository.TicketReposiory;
import ticket.services.SequenceTicket;
import ticket.services.TicketService;

import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;
import static ticket.model.Ticket.*;
import ticket.model.SequenceId;
import ticket.model.ServiceId;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/apiticket")
public class TicketController {
	@Autowired
    private MongoOperations mongoOperations;
	@Autowired
	private TicketService service;
	@Autowired
	private ServiceRepository sRepository;
	private LocalDateTime date;
	@Autowired
	private SequenceTicket ST;
	@Autowired
	private TicketReposiory repo;

	@GetMapping("/tickets")
	public List<Ticket> list() {
		List<Ticket> Tickets = service.listAll();
		return Tickets;
	}

	@GetMapping("/ticket/{id}")
	public Ticket geTicket(@PathVariable Integer id) {
		Ticket list = service.get(id);
		return list;
	}

	@PostMapping("/addticket")
	public void add(@RequestBody Ticket Ticket) {
		this.date = date.now();
		Ticket.setId_ticket(this.ST.getSequenceNumber(SEQUENCE_NAME));
		Ticket.setDate(date);
		service.save(Ticket);
	}

	@PutMapping("/ticket/{id}")
	public ResponseEntity<?> update(@RequestBody Ticket Ticket, @PathVariable Integer id) {
		try {
			Ticket exixTicket = service.get(id);
			service.save(Ticket);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Ticket>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/ticket/{id}")
	public void delete(@PathVariable Integer id) {
		service.delete(id);
	}

	@DeleteMapping("/delticket")
	public void deleteall() {
		List<Ticket> Tickets = service.listAll();
		for (Ticket ticket : Tickets) {
			service.delete(ticket.getId_ticket());
		}
		List<Servicce> servicces=sRepository.findAll();
		for (Servicce ticket : servicces) {
			sRepository.deleteById(ticket.getId_ticket());
		}
		
		  Query query = new Query(Criteria.where("id").is("Ticket_sequence"));
	        //update the sequence no
	        Update update = new Update().update("seq", 0);
	        //modify in document
	        ServiceId counter = mongoOperations
	                .findAndModify(query,
	                        update, options().returnNew(true).upsert(true),
	                        ServiceId.class);
	        
	        Query query2 = new Query(Criteria.where("id").is("user_sequence"));
	        //update the sequence nos
	        Update update2 = new Update().update("seq", 0);
	        //modify in document
	        SequenceId counter2 = mongoOperations
	                .findAndModify(query2,
	                        update2, options().returnNew(true).upsert(true),
	                        SequenceId.class);

	}

	@GetMapping("/tickete/{email}")
	public Ticket ChangeRes(@PathVariable String email) {
		Ticket ticket = repo.findByEmail(email);
		ticket.setRes(true);
		repo.save(ticket);
		return ticket;
	}

	@GetMapping("/ticketbyemail/{email}")
	public Ticket gettikcetbyemail(@PathVariable String email) {
		Ticket opt = repo.findByEmail(email);
		return opt;

	}
}
