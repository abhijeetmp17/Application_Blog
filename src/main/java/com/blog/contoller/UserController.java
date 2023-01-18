package com.blog.contoller;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import com.blog.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.payload.ApiResponce;
import com.blog.payload.UserDto;
import com.blog.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {

	@Autowired
	private UserService userService;

	/*
	 *  post-create operation done by post mapping
	 */
	@PostMapping("/")
	/*
	 * ResponceEntity-> will going to give the userDto object and status code to Postman and print it
	 * @RequestBody will get the data from postman and store in dto object
	 */
	public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto) {
		UserDto createUserDto = this.userService.createUser(userDto);

		return new ResponseEntity<>(createUserDto, HttpStatus.CREATED);
	}

	// put-update-user

	/*
	 * @pathVariable -> is user to get value which is in url in the variable and we can use it further	
	 */
	@PutMapping("/{userId}")
	public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto, @PathVariable Integer userId) {
		UserDto updateUser = this.userService.updateUser(userDto, userId);

		return ResponseEntity.ok(updateUser);
	}

	// delete delete-user

	@DeleteMapping("/{userId}")
	/*
	 * koi cheez home ke baad agar hame koi msg send karna hai to hum ApiResponce ki class banayenge 
	 * aur uska constructor call karenge
	 */
	public ResponseEntity<ApiResponce> deleteUser(@Valid @RequestBody UserDto userDto,
			@PathVariable("userId") Integer userid) {
		this.userService.deleteUser(userid);
		return new ResponseEntity(new ApiResponce("User deleted Sucessfully", true), HttpStatus.OK);

	}

	// Get get-All-user
	
	@GetMapping("/")
	public ResponseEntity<List<UserDto>> getAllUser()
	{
		List<UserDto> users = userService.getAllUsers();


		return ResponseEntity.ok(users);
	}
	
	// Get get-user
	
		@GetMapping("/{userId}")
		public ResponseEntity<UserDto> getSingleUser(@PathVariable Integer userId)
		{
			System.err.println(1);
			
			return ResponseEntity.ok(this.userService.getUserById(userId));
		}
}
