package com.cooksys.socialmedia.entities;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Entity
public class Hashtag {
	@Id
	@GeneratedValue
	private Long id;
	  
	@Column(unique = true, nullable=false)
	private String label;
	   
	@CreationTimestamp
	private Timestamp firstUsed;
	
	@UpdateTimestamp
	private Timestamp lastUsed;
	
	@ManyToMany(mappedBy = "hashtags", cascade = CascadeType.ALL)
    private List<Tweet> tweets = new ArrayList<>();

}
