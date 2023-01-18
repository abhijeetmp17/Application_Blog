package com.blog.contoller;

import org.aspectj.weaver.NewConstructorTypeMunger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.blog.entity.Comment;
import com.blog.payload.ApiResponce;
import com.blog.payload.CommentDto;
import com.blog.service.CommentService;

@RestController
@RequestMapping("api")
public class CommentController 
{
	@Autowired
	private CommentService commentService;
	
	
	/*
	 * creating the comment
	 */
	
	@PostMapping("/post/{postId}/comments")
	public ResponseEntity<CommentDto> createComment(@RequestBody CommentDto commentDto , @PathVariable Integer postId)
	{
		CommentDto commentDto2 = this.commentService.createComment(commentDto, postId);
		
		return new ResponseEntity<CommentDto>(commentDto2 , HttpStatus.CREATED); 
	}
	
	
	/*
	 *Deleting the comment
	 */
	
	@DeleteMapping("/comments/{commentId}")
	public ResponseEntity<ApiResponce> createComment( @PathVariable Integer commentId)
	{
		this.commentService.deleteComment(commentId);
		
		return new ResponseEntity<ApiResponce>(new ApiResponce("deleted Successfully" , true) , HttpStatus.OK ); 
	}
}
