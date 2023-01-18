package com.blog.service.impl;


import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.xml.crypto.Data;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.blog.entity.Catagory;
import com.blog.entity.Post;
import com.blog.entity.User;
import com.blog.exception.ResourceNotFoundExcrption;

import com.blog.payload.PostDto;
import com.blog.payload.PostResponce;

import com.blog.repository.CatagoryRepo;
import com.blog.repository.PostRepo;
import com.blog.repository.UserRepo;
import com.blog.service.PostService;



@Service
public class PostServiceImpl implements PostService {
	@Autowired
	private PostRepo postRepo;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private CatagoryRepo catagoryRepo;

	// create
	@Override
	public PostDto createPost(PostDto postDto, Integer userId, Integer catagoryId) {

		User user = this.userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundExcrption("User", "User_Id", userId));

		Catagory catagory = this.catagoryRepo.findById(catagoryId)
				.orElseThrow(() -> new ResourceNotFoundExcrption("Catagory", "Catagory_Id", catagoryId));

		Post post = this.modelMapper.map(postDto, Post.class);

		Post post1 = this.postRepo.save(post);
		post1.setImageName("default.png");
		post1.setAddedDate(new Date());
		post1.setUser(user);
		post1.setCatagory(catagory);

		Post save = this.postRepo.save(post1);

		return this.modelMapper.map(save, PostDto.class);
	}

	/*
	 * Update post
	 */
	@Override
	public PostDto updatePost(PostDto postDto, Integer postId) {

		 Post post = this.postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundExcrption("Post", "post id", postId));
		 post.setTitle(postDto.getTitle());
		 post.setContent(postDto.getContent());
		 post.setImageName(postDto.getImageName());
		 
		 Post post1 = this.postRepo.save(post);
		return this.modelMapper.map(post1, postDto.getClass());
	}

	/*
	 * Delete post
	 */
	@Override
	public void deletePost(Integer postId) {

		 Post post = this.postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundExcrption("Post", "post id", postId));
		 this.postRepo.delete(post);
	}

	/*
	 * get all posts
	 */
	@Override
	public PostResponce getAllPosts(Integer pageNo , Integer pageSize , String sortBy , String sortDir) {

		Sort sort = null;
		
		
		sort = (sortDir.equalsIgnoreCase("asc"))?Sort.by(sortBy).ascending():sort.by(sortBy).descending();
		
		
		
//		if(sortDir.equalsIgnoreCase("asc"))
//		{
//			sort = Sort.by(sortBy).ascending();
//		}
//		else {
//			sort = sort.by(sortBy).descending();
//		}
		
		Pageable p = PageRequest.of(pageNo, pageSize,sort );
		
		Page<Post> pagePosts = this.postRepo.findAll(p);
		
		List<Post> allPosts = pagePosts.getContent();

		List<PostDto> postDtos = allPosts.stream().map((post) -> this.modelMapper.map(post, PostDto.class))
				.collect(Collectors.toList());

		
		PostResponce postResponce = new PostResponce();
		
		postResponce.setContent(postDtos);
		postResponce.setPageNumber(pagePosts.getNumber());
		postResponce.setPageSize(pagePosts.getSize());
		postResponce.setTotalElements(pagePosts.getTotalElements());
		
		postResponce.setTotalPages(pagePosts.getTotalPages());
		postResponce.setLastPage(pagePosts.isLast());
		return postResponce;
	}

	/*
	 * get post by id
	 */
	@Override
	public PostDto getPost(Integer postId) {

		Post post = this.postRepo.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundExcrption("Post", "post_id", postId));

		return this.modelMapper.map(post, PostDto.class);
	}

	/*
	 * get post by catagory
	 */
	@Override
	public List<PostDto> getPostByCatagory(Integer catagoryId) {

		Catagory cat = this.catagoryRepo.findById(catagoryId)
				.orElseThrow(() -> new ResourceNotFoundExcrption("Catagory", "catagory_id", catagoryId));

		List<Post> posts = this.postRepo.findByCatagory(cat);

		List<PostDto> postDtos = posts.stream().map((post) -> this.modelMapper.map(post, PostDto.class))
				.collect(Collectors.toList());
		return postDtos;
	}

	/*
	 * get post by user
	 */
	@Override
	public List<PostDto> getPostBtUser(Integer userId) {

		User user = this.userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundExcrption("User", "user_id", userId));

		List<Post> posts = this.postRepo.findByUser(user);

		List<PostDto> postDtos = posts.stream().map((post) -> this.modelMapper.map(post, PostDto.class))
				.collect(Collectors.toList());

		return postDtos;
	}

	/*
	 * search post
	 */
	@Override
	public List<PostDto> searchPosts(String keyword) {

		List<Post> posts = this.postRepo.findByTitleContaining(keyword);
		List<PostDto> postDtos = 
				posts.stream().map((post)-> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		
		
		return postDtos;
	}

}
