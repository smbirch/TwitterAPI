package com.cooksys.socialmedia.entities;

import java.sql.Timestamp;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Hashtag {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	  
	@Column(unique = true, nullable=false)
	private String label;
	   
	@Column(updatable = false)
	@CreationTimestamp
	private Timestamp firstUsed;
	
	@UpdateTimestamp
	private Timestamp lastUsed;

}
