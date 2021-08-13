package com.rim.emp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name= "request_table")
public @Data class Request {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name= "RE_ID")
	private int id;
	@Column(name= "empid")
	private int empid;
	@Column(name= "email")
	private String email;
	@Column(name= "name")
	private String name;
	@Column(name= "retype")
	private String retype;
	@Column(name= "phone")
	private long phone;
	@Column(name= "amount")
	private long amount;
	@Column(name= "status")
	private String status;
	
	

}
