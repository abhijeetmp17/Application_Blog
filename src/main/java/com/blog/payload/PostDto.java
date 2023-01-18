package com.blog.payload;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.blog.entity.Catagory;
import com.blog.entity.Comment;
import com.blog.entity.User;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class PostDto {

	
	private Integer postId;
	private String title;

	private String content;
	private String imageName;

	private Date addedDate;

	private CatagoryDto catagory;

	private UserDto user;
	
	private Set<CommentDto> comments = new HashSet<>();

}
