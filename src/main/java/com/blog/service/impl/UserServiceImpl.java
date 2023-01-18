package com.blog.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.entity.User;
import com.blog.exception.ResourceNotFoundExcrption;
import com.blog.payload.UserDto;
import com.blog.repository.UserRepo;
import com.blog.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserRepo userRepo;

	/*
	 * Casting the entity class object into dto class object and vice versa
	 */
	@Autowired
	private ModelMapper modelMapper;

	@Override
	@Transactional
	public UserDto createUser(UserDto userDto) {

		User user = this.dtoToUser(userDto);

		User save = this.userRepo.save(user);

		return this.userToUserdto(save);

	}

	@Override
	@Transactional
	public UserDto updateUser(UserDto userDto, Integer userId) {

		User user = this.userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundExcrption("User", "id", userId));

		user.setAbout(userDto.getAbout());
		user.setEmail(userDto.getEmail());
		user.setId(userDto.getId());
		user.setName(userDto.getName());
		user.setPassword(userDto.getPassword());

		User save = this.userRepo.save(user);
		UserDto userDto1 = this.userToUserdto(user);

		return userDto1;
	}

	@Override
	public UserDto getUserById(Integer userid) {

		System.err.println(2);
		User user = this.userRepo.findById(userid)
				.orElseThrow(() -> new ResourceNotFoundExcrption("User", "Id", userid));

		return this.userToUserdto(user);
	}

	@Override
	public List<UserDto> getAllUsers() {

		List<User> users = this.userRepo.findAll();

		/*
		 * pure list ko convert karne ke lye in userDto
		 */

		List<UserDto> userDtos = users.stream().map(user -> this.userToUserdto(user)).collect(Collectors.toList());

		return userDtos;
	}

	@Override
	@Transactional
	public void deleteUser(Integer userId) {

		User user = this.userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundExcrption("User", "Id", userId));
		this.userRepo.delete(user);
	}

	private User dtoToUser(UserDto userDto) {
		/*
		 * ModelMapper -> ha eka object la dusrya object madhe convert karto yat
		 * map(kontya object la convert kar aycha , kontya class madhe convert karaycha)
		 * method ahe
		 */
		User user = this.modelMapper.map(userDto, User.class);
//		User user = new User();
//		user.setId(userDto.getId());
//		user.setAbout(userDto.getAbout());
//		user.setEmail(userDto.getEmail());
//		user.setName(userDto.getName());
//		user.setPassword(userDto.getPassword());

		return user;
	}

	private UserDto userToUserdto(User user) {

		UserDto userDto = this.modelMapper.map(user, UserDto.class);
//		UserDto userDto = new UserDto();
//		userDto.setId(user.getId());
//		userDto.setAbout(user.getAbout());
//		userDto.setEmail(user.getEmail());
//		userDto.setName(user.getName());
//		userDto.setPassword(user.getPassword());
//		

		return userDto;
	}

}
