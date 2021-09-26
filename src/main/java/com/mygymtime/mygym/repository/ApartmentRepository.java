package com.mygymtime.mygym.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mygymtime.mygym.model.Apartment;

public interface ApartmentRepository extends JpaRepository<Apartment, String>{

}
