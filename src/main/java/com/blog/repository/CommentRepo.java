package com.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blog.entity.Comment;
import com.blog.payload.CommentDto;

public interface CommentRepo extends JpaRepository<Comment, Integer>
{
	
}
