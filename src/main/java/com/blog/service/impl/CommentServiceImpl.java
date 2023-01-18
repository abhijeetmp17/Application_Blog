package com.blog.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.entity.Comment;
import com.blog.entity.Post;
import com.blog.exception.ResourceNotFoundExcrption;
import com.blog.payload.CommentDto;
import com.blog.repository.CommentRepo;
import com.blog.repository.PostRepo;
import com.blog.service.CommentService;
@Service
public class CommentServiceImpl implements CommentService
{

	@Autowired
	private PostRepo postRepo;
	
	@Autowired
	private CommentRepo commentRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public CommentDto createComment(CommentDto commentDto, Integer postId) {

		
		Post post =  this.postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundExcrption("Post", "post id", postId));
		
		
		Comment comment = this.modelMapper.map(commentDto, Comment.class);
		
		comment.setPost(post);
		
		Comment comment2 = this.commentRepo.save(comment);
		
		return this.modelMapper.map(comment2, CommentDto.class);
	}

	@Override
	public void deleteComment(Integer commentId) {
		Comment comment =  this.commentRepo.findById(commentId).orElseThrow(()-> new ResourceNotFoundExcrption("Comment", "comment id", commentId));
		
		this.commentRepo.delete(comment);
	}
	
}
