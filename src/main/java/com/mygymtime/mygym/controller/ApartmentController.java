package com.mygymtime.mygym.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mygymtime.mygym.model.Apartment;
import com.mygymtime.mygym.repository.ApartmentRepository;

@RestController
@CrossOrigin("*")
@RequestMapping({"/ap"})
public class ApartmentController {
	private ApartmentRepository repository;

	public ApartmentController(ApartmentRepository apartmentRepository) {
		this.repository = apartmentRepository;
	}
	
	@GetMapping(value= "/all") ResponseEntity<List<Apartment>> listAll(){
		return ResponseEntity.ok().body(repository.findAll());
	}
	
	@GetMapping(value= "/{id}")
	public ResponseEntity<Optional<Apartment>> findById(@PathVariable String id) {
		return ResponseEntity.ok().body(repository.findById(id));
	}
	
	//just admin people
	@PostMapping(value= "/register")
	public ResponseEntity<Apartment> save(@RequestBody Apartment apartment){
		return ResponseEntity.created(null).body(repository.save(apartment));
	}
	
	@PutMapping(value= "/update/{id}")
	public ResponseEntity<Void> update(@PathVariable String id,
			@RequestBody Apartment apartmentUpdate){
		apartmentUpdate.setApartment(id);
		repository.save(apartmentUpdate);
		return ResponseEntity.noContent().build();
	}
	
	//@GetMapping(path = {"/all"})
	//public ResponseEntity findAll() {
		//System.out.println("find all");
		//return repository.findById("91")
			//	.map(record -> ResponseEntity.ok().body(record))
				//.orElse(ResponseEntity.notFound().build());
	//}

	
}
