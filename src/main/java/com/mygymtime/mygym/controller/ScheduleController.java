package com.mygymtime.mygym.controller;

import java.util.List;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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

import com.mygymtime.mygym.model.Schedule;
import com.mygymtime.mygym.repository.ScheduleRepository;

@RestController
@CrossOrigin("*")
@RequestMapping({"/schedule"})
public class ScheduleController {
	private ScheduleRepository repository;
	
	public ScheduleController(ScheduleRepository scheduleRepository) {
		this.repository = scheduleRepository;
	}
	
	@GetMapping(value= "/all") ResponseEntity<List<Schedule>> listAll(){
		return ResponseEntity.ok().body(repository.findAll());
	}
	
	@GetMapping(value= "/{id}")
	public ResponseEntity<Optional<Schedule>>findById(@PathVariable String id){
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-ddHH:mm");
		try {
			return ResponseEntity.ok().body(repository.findById(formatter.parse(id)));
		} catch (ParseException e) {
			e.printStackTrace();
			return (ResponseEntity<Optional<Schedule>>) ResponseEntity.notFound();
		}
	}

}
