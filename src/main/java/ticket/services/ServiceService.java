package ticket.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ticket.model.Servicce;
import ticket.repository.ServiceRepository;
@Service
public class ServiceService {
	@Autowired
	private ServiceRepository repo;

	public List<Servicce> listAll() {
		return repo.findAll();
	}

	public void save(Servicce article) {
		repo.save(article);
	}
}
