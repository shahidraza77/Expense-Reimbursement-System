package com.rim.emp.model;

import java.util.ArrayList;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.JoinColumn;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name= "employee")
@NamedQueries({
	@NamedQuery(name="viewAllEmployee",query = "FROM Employee e"),
	@NamedQuery(name="findEmployeeById",query = "FROM Employee e WHERE e.id=:id"),
	@NamedQuery(name="findEmployeeByEmail",query = "FROM Employee e WHERE e.email=:email")
})
public @Data class Employee {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
//	@ManyToMany(cascade = CascadeType.ALL)
//	 @JoinTable(name = "EMP_REQUEST", 
//	  joinColumns = { @JoinColumn(name = "EMP_ID") }, 
//	  inverseJoinColumns = { @JoinColumn(name = "RE_ID") })
//	private ArrayList<Request> req=new ArrayList<Request>();
	@Column(name= "name")
	private String name;
	@Column(name= "email")
	private String email;
	@Column(name= "password")
	private String password;
	@Column(name= "phone")
	private long phone;
	@Column(name= "age")
	private int age;
	@Column(name= "address")
	private String address;
	@Column(name= "gender")
	private String gender;

}

