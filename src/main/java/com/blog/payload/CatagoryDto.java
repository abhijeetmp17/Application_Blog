package com.blog.payload;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.blog.entity.Post;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class CatagoryDto 
{
	
	private Integer catagoryId;
	
	@NotBlank
	@Size(min = 4 , message = "size must be gretaer than 4 words")
	private String catagoryTitle;
	
	
	@NotBlank
	@Size(min = 10 , message = "size must be gretaer than 10 words")
	private String catagoryDescription;
	
	

		
}
