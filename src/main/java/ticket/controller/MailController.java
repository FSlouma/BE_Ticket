package ticket.controller;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import net.bytebuddy.dynamic.DynamicType.Builder.FieldDefinition.Optional;
import ticket.model.MessageResponse;
import ticket.model.Ticket;
import ticket.model.User;
import ticket.repository.TicketReposiory;
import ticket.repository.UserRepository;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("mail")
public class MailController {
	@Autowired
	public MailSender emailSender;
	@Autowired
	public UserRepository userRepo;
	@Autowired
	public TicketReposiory ticketRepo;

	@ResponseBody
	@GetMapping("/send/{username}")
	public ResponseEntity<?> CodeEmail(@PathVariable String username) {
		Random random = new Random();
		String generatecode = String.format("%04d", random.nextInt(10000));
		SimpleMailMessage message = new SimpleMailMessage();
		Ticket u = ticketRepo.findByEmail(username);
		u.setCode(generatecode);
		ticketRepo.save(u);
		message.setTo(u.getEmail());
		message.setSubject("Confimation de votre reservation");
		message.setText("Bonjour, Pour confirmer votre reservation veuillez saisir ce code " + generatecode);
		this.emailSender.send(message);
		return new ResponseEntity<>(new MessageResponse("email sent successfully!"), HttpStatus.OK);
	}

	@GetMapping("/sent/{username}")
	public ResponseEntity<?> PasswordEmail(@PathVariable String username) {
		Random random = new Random();
		String generatePin = String.format("%04d", random.nextInt(10000));
		SimpleMailMessage message = new SimpleMailMessage();
		User u = userRepo.findByEmail(username);
		u.setPassword(generatePin);
		userRepo.save(u);
		message.setTo(u.getEmail());
		message.setSubject("Récupuration du compte");
		message.setText("Bonjour, votre mot de passe est " + generatePin);
		// Send Message!
		this.emailSender.send(message);

		return new ResponseEntity<>(new MessageResponse("email sent successfully!"), HttpStatus.OK);
	}

	@GetMapping("/senv/{username}")
	public ResponseEntity<?> ValidationEmail(@PathVariable String username) {
		SimpleMailMessage message = new SimpleMailMessage();
		Ticket u = ticketRepo.findByEmail(username);
		message.setTo(u.getEmail());
		message.setSubject("Reservation Confirmé");
		message.setText("Bonjour, Votre confirmation à été effectuer avec succes votre numero de ticket est "
				+ u.getId_ticket());
		this.emailSender.send(message);
		return new ResponseEntity<>(new MessageResponse("email sent successfully!"), HttpStatus.OK);
	}
}
