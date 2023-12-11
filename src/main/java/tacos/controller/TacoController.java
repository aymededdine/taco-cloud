package tacos.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import tacos.entity.Taco;
import tacos.repository.TacoRepository;

@RestController
@RequestMapping(path = "/api/tacos", produces = "application/json")
@CrossOrigin(origins = "http://tacocloud:8080")
public class TacoController {

	@Autowired
	TacoRepository tacoRepo;

	@GetMapping(params = "recent")
	public Iterable<Taco> recentTacos() {

		PageRequest page = PageRequest.of(0, 12, Sort.by("createdAt").descending());
		return tacoRepo.findAll(page).getContent();

	}

	@GetMapping("/{id}")
	public Optional<Taco> tacoById(@PathVariable("id") Long id) {
		return tacoRepo.findById(id);
	}

	@PostMapping(consumes = "application/json")
	@ResponseStatus(HttpStatus.CREATED)
	public Taco postTaco(@RequestBody Taco taco) {
		return tacoRepo.save(taco);
	}

	@PutMapping(path = "/{orderId}", consumes = "application/json")
	public Taco putOrder(@PathVariable("orderId") Long id, @RequestBody Taco taco) {
		taco.setId(id);
		return tacoRepo.save(taco);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteOrder(@PathVariable("orderId") Long id) {
		try {
			tacoRepo.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
		}
	}

//	@PatchMapping(path = "/{orderId}", consumes = "application/json")
//	public TacoOrder patchOrder(@PathVariable("orderId") Long orderId, @RequestBody TacoOrder patch) {
//		TacoOrder order = repo.findById(orderId).get();
//		if (patch.getDeliveryName() != null) {
//			order.setDeliveryName(patch.getDeliveryName());
//		}
//		if (patch.getDeliveryStreet() != null) {
//			order.setDeliveryStreet(patch.getDeliveryStreet());
//		}
//		if (patch.getDeliveryCity() != null) {
//			order.setDeliveryCity(patch.getDeliveryCity());
//		}
//		if (patch.getDeliveryState() != null) {
//			order.setDeliveryState(patch.getDeliveryState());
//		}
//		if (patch.getDeliveryZip() != null) {
//			order.setDeliveryZip(patch.getDeliveryZip());
//		}
//		if (patch.getCcNumber() != null) {
//			order.setCcNumber(patch.getCcNumber());
//		}
//		if (patch.getCcExpiration() != null) {
//			order.setCcExpiration(patch.getCcExpiration());
//		}
//		if (patch.getCcCVV() != null) {
//			order.setCcCVV(patch.getCcCVV());
//		}
//		return repo.save(order);
//	}

}
