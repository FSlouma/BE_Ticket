package ticket.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

import ticket.model.User;
import ticket.repository.UserRepository;
import ticket.services.UserService;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/apiuser")
public class UserController {
	@Autowired
	private UserService service;
	@Autowired
	private UserRepository repo;
	
	@GetMapping("/users")
	public List<User> list(){
		List<User> Users= service.listAll();
		return Users;
	}
	
	@GetMapping("/user/{id}")
    public User geTicket(@PathVariable String id){
		try {
			User list=service.get(id);
	        return list;
		} catch (Exception e) {
		   throw new RuntimeException("User not found with id "+id);
		}
    }
	@PostMapping("/adduser")
	public void add(@RequestBody User User) {
		service.save(User);
	}
	
	@PutMapping("/user/{id}")
		public ResponseEntity<?> update(@RequestBody User User, @PathVariable String id){
	     try 
	    {
	    	   User exixUser=service.get(id);
	    	   service.save(User);
	    	   return new ResponseEntity<>(HttpStatus.OK);
		}
	       catch (Exception e) {
	    	   return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
		}
	}
	
	@DeleteMapping("/user/{id}")
    public void delete(@PathVariable String id) {
		try 
		{
			User list=service.get(id);
			service.delete(id);
		} 
		catch (Exception e) {
		   throw new RuntimeException("User not found with id "+id);
		}
    }
	@GetMapping("/userbyemail/{id}")
	public User userbyemail(@PathVariable String id){
		/*List<User> Users= service.listAll();
		User u=null;
		for(User user:Users)
		{
			String email=user.getEmail();
			if(email==id)
				u=user;
		}
		
		*/User u=repo.findByEmail(id);
		return u;
		
	}
}
