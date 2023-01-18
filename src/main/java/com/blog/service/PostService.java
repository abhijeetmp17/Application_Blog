package com.blog.service;

import java.util.List;

import com.blog.entity.Post;
import com.blog.payload.PostDto;
import com.blog.payload.PostResponce;

public interface PostService
{
	//create
	PostDto createPost(PostDto postDto , Integer userId , Integer catagoryId);
	
	
	//update
	
	PostDto updatePost (PostDto postDto , Integer postId);
	
	//delete
	
	void deletePost(Integer postId);
	
	//get All posts
	
	PostResponce getAllPosts(Integer pageNo , Integer pageSize , String sortBy , String sortDir);
	
	//get single post
	
	PostDto getPost(Integer postId);
	
	//get all post by catagory
	
	List<PostDto> getPostByCatagory(Integer catagoryId);
	
	//get all post by user
	
	List<PostDto> getPostBtUser(Integer userId);
	
	//search posts
	List<PostDto> searchPosts(String keyword);
	
	
	
}
