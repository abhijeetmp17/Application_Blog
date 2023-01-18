package com.blog.payload;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserDto {
	private int id;

	@NotEmpty
	@Size(min = 3, message = "username must be more than 3 char")
	private String name;
	@Email
	private String email;
	@NotEmpty
	@Size(min = 4, max = 10, message = "password must be >4 and <10 char")
	private String password;

	@NotEmpty
	private String about;

}
