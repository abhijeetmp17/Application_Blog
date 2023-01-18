package com.blog.service;

import java.util.List;


import com.blog.payload.UserDto;

public interface UserService 
{
	UserDto createUser(UserDto user);
	UserDto updateUser(UserDto user , Integer userId);
	UserDto getUserById(Integer userid);
	List<UserDto> getAllUsers();
	void deleteUser(Integer userId);
	
}
