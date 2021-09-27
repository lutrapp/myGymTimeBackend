package com.mygymtime.mygym.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Schedule {
	
	@Id
	private Date dt_schedule;
	private String apartment;
	private Boolean reserved;
	private Date ts;
	public Date getDt_schedule() {
		return dt_schedule;
	}
	public void setDt_schedule(Date dt_schedule) {
		this.dt_schedule = dt_schedule;
	}
	public String getApartment() {
		return apartment;
	}
	public void setApartment(String apartment) {
		this.apartment = apartment;
	}
	public Boolean getReserved() {
		return reserved;
	}
	public void setReserved(Boolean reserved) {
		this.reserved = reserved;
	}
	public Date getTs() {
		return ts;
	}
	public void setTs(Date ts) {
		this.ts = ts;
	}
	
	
	


}
