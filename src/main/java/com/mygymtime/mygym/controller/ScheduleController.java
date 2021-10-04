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
@RequestMapping({ "/schedule" })
public class ScheduleController {
	private ScheduleRepository repository;

	public ScheduleController(ScheduleRepository scheduleRepository) {
		this.repository = scheduleRepository;
	}

	@GetMapping(value = "/all")
	ResponseEntity<List<Schedule>> listAll() {
		return ResponseEntity.ok().body(repository.findAll());
	}

	@GetMapping(value = "/{date}")
	public ResponseEntity<Optional<Schedule>> findById(@PathVariable String date) {
		Date dateConverted = convertDate(date);
		if(dateConverted != null) {
			return ResponseEntity.ok().body(repository.findById(dateConverted));
		}else {
			return (ResponseEntity<Optional<Schedule>>) ResponseEntity.notFound();
		}
		/*
		 * SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-ddHH:mm"); try {
		 * return ResponseEntity.ok().body(repository.findById(formatter.parse(date)));
		 * } catch (ParseException e) { e.printStackTrace(); return
		 * (ResponseEntity<Optional<Schedule>>) ResponseEntity.notFound(); }
		 */
	}

	@PostMapping(value = "/{ap}/{date}")
	public Boolean schedulePost(@PathVariable String ap, @PathVariable String date) {
		Date dateConverted = convertDate(date);
		Optional<Schedule> notFree = repository.findById(dateConverted);
		if(notFree.isPresent()) {
			return false;
		}else {
			Schedule schedule = new Schedule();
			schedule.setApartment(ap);
			schedule.setDt_schedule(dateConverted);
			schedule.setReserved(true);
			schedule.setTs(new Date());
			this.repository.save(schedule);
			return true;	
		}
		
	}

	@PutMapping(value = "/{ap}/{date}")
	public Boolean scheduleUpdate(@PathVariable String ap, @PathVariable String date) {
		Date dateConverted = convertDate(date);
		Schedule schedule = new Schedule();
		schedule.setApartment(ap);
		schedule.setDt_schedule(dateConverted);
		schedule.setReserved(true);
		schedule.setTs(new Date());
		this.repository.save(schedule);
		return true;
	}
	
	// method to convert string to date
	public Date convertDate(String date) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-ddHH:mm");
		try {
			return formatter.parse(date);
		} catch (ParseException e) {
			return null;
		}
	}

}
