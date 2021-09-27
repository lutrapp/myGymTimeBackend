package com.mygymtime.mygym.repository;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import com.mygymtime.mygym.model.Schedule;

public interface ScheduleRepository extends JpaRepository<Schedule, Date>{

}
