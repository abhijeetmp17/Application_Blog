 package com.blog.contoller;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.Flow.Publisher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.blog.config.AppConstant;
import com.blog.entity.Post;
import com.blog.payload.ApiResponce;
import com.blog.payload.PostDto;
import com.blog.payload.PostResponce;
import com.blog.service.FileService;
import com.blog.service.PostService;

import lombok.val;

@RestController
@RequestMapping("/api")
public class PostController 
{
	
	
	
	@Autowired
	private PostService postService;
	
	@Autowired
	private FileService fileService;
	
	@Value("$ {project.image}")
	private String path;
	//create
	
	@PostMapping("/user/{userId}/catagory/{catagoryId}/posts")
	public ResponseEntity<PostDto> createPost
					(@RequestBody PostDto postDto , @PathVariable Integer userId ,
							@PathVariable Integer catagoryId)	
	{
		PostDto createPost = this.postService.createPost(postDto, userId, catagoryId);
		return new ResponseEntity<PostDto>(createPost, HttpStatus.CREATED );
	}
	
	//get posts  by users
	
	@GetMapping("/user/{userId}/posts")
	public ResponseEntity<List<PostDto>> getPostByUser(
			@PathVariable Integer userId
			)
	{
		List<PostDto> postBtUser = this.postService.getPostBtUser(userId);
		return new ResponseEntity<List<PostDto>>(postBtUser , HttpStatus.OK);
	}
	
	

	//get posts  by catagories
	
	@GetMapping("/catagory/{cId}/posts")
	public ResponseEntity<List<PostDto>> getPostByCatagory(
			@PathVariable Integer cId
			)
	{
		  List<PostDto> postByCatagory = this.postService.getPostByCatagory(cId);
		return new ResponseEntity<List<PostDto>>(postByCatagory , HttpStatus.OK);
	}
	
	
	
	// get all posts
	
	@GetMapping("/posts")
	public ResponseEntity<PostResponce> getAllPosts(
			@RequestParam(value = "pageNo" , defaultValue = AppConstant.PAGE_NUMBER , required = false) Integer pageNo , 
			@RequestParam(value = "pageSize" , defaultValue = AppConstant.PAGE_SIZE , required = false) Integer pageSize , 
			@RequestParam(value = "sortBy" , defaultValue = AppConstant.SORT_BY , required = false) String sortBy,
			@RequestParam(value = "sortDir" , defaultValue = AppConstant.SORT_DIR , required = false) String sortDir
			)
	{
		 PostResponce postResponce = this.postService.getAllPosts(pageNo , pageSize ,sortBy , sortDir);
		
		return new ResponseEntity<PostResponce>(postResponce, HttpStatus.OK);
	}
	
	
	//get one post by id
	
	@GetMapping("/posts/{postId}")
	public ResponseEntity<PostDto> getPost(@PathVariable Integer postId)
	{
		PostDto postDto = this.postService.getPost(postId);
		
		return new ResponseEntity<PostDto>(postDto , HttpStatus.OK);
	}
	
	
	//delete controller
	@DeleteMapping("/posts/{postId}")
	public ApiResponce deletePost(@PathVariable Integer postId)
	{
		this.postService.deletePost(postId);
		return new ApiResponce("post successfully deleted" , true);
	}
	
	//Delete controller
		@PutMapping ("/posts/{postId}")
		public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto ,  @PathVariable Integer postId)
		{
			PostDto postDto2 = this.postService.updatePost(postDto, postId);
			
			return new ResponseEntity<PostDto>(postDto2 , HttpStatus.OK);
		}
		
		//searching
		@GetMapping("/posts/search/{keywords}")
		public ResponseEntity<List<PostDto>> searchPostByTitle(
				@PathVariable("keywords") String keywords
				)
		{
			List<PostDto> postDtos = this.postService.searchPosts(keywords);
			
			return new ResponseEntity<List<PostDto>>(postDtos , HttpStatus.OK);	
			}
		
		
		//post image upload
		@PostMapping("/post/image/upload/{postId}")
		public ResponseEntity<PostDto> uploadPostImage(
				@RequestParam ("image") MultipartFile image,
				@PathVariable Integer postId
				) throws IOException
		{
			
			
			
			PostDto postDto = this.postService.getPost(postId); 
			String fileName = this.fileService.uploadImage(path, image);
			
			
			postDto.setImageName(fileName);
			PostDto updatePost = this.postService.updatePost(postDto, postId);
			return new ResponseEntity<PostDto>(updatePost ,	HttpStatus.OK);
		}
}
