package com.redhat.service.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.ToString;

@Entity
@Table(name = "peoples")
@Data
@ToString
public class People implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	protected String document;
	protected String name;
	protected String email;
	protected String address;

}
