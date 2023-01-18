package com.blog.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.blog.entity.Catagory;
import com.blog.entity.Post;
import com.blog.entity.User;

public interface PostRepo extends JpaRepository<Post, Integer>
{
	List<Post> findByUser(User user);
	List<Post> findByCatagory(Catagory catagory);
	
//	@Query("select p from Post p where p.title like : key")
//	List<Post> searchByTitle(@Param("key") String title);
//	
	
	List<Post> findByTitleContaining(String title);
}
