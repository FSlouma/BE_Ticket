package ticket.controller;

import static ticket.model.Servicce.SEQUENCE_NAME;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ticket.model.Servicce;
import ticket.services.ServiceService;
import ticket.services.SequenceNticket;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/apiservice")
public class ServiceController {
	@Autowired
	private ServiceService service;
	private LocalDateTime date;
	@Autowired
	private SequenceNticket ST;
	@GetMapping("/services")
	public List<Servicce> list() {
		List<Servicce> Tickets = service.listAll();
		return Tickets;
	}
	@PostMapping("/addservice")
	public void add(@RequestBody Servicce Ticket) {
		this.date=date.now();
		Ticket.setId_ticket(this.ST.getSequenceNumber(SEQUENCE_NAME));
		Ticket.setDate(date);
		service.save(Ticket);
	}
}
