package com.blog.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Catagory {
	@Id
	/*
	 * generated type in hinernate by default AUTO type is IDENTITY but in other
	 * 
	 */
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer catagoryIdInteger;
	private String catagoryTitle;
	private String catagoryDescription;

	@OneToMany(mappedBy = "catagory", cascade = CascadeType.ALL)
	private List<Post> posts = new ArrayList<>();

}
