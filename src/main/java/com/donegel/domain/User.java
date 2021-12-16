package com.donegel.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;
@Getter
@Setter
@Entity
@Table(name = "users")
@EntityListeners(AuditingEntityListener.class)
public class User {

	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "users_seq")
	@SequenceGenerator(sequenceName = "customer_seq", allocationSize = 1, name = "users_seq")
    @Id

	private long id;
	private String firstName;
	private String lastName;
	private String emailId;
	private Date createdAt;
	private String createdBy;
	private Date updatedAt;
	private String updatedby;
	

}