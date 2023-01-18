package com.blog.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
/*
 * this class is created to throw the exception
 */
public class ResourceNotFoundExcrption extends RuntimeException {
	String resourceName;
	String fieldName;
	long fieldValue;

	public ResourceNotFoundExcrption(String resourceName, String fieldName, long fieldValue) {
		/*
		 * String.format hi method variables s chya jagevar lihite
		 * super(String)->Runtime vali super(message)call->Exception vali
		 * super(message)call ->Throwable vala constructor call hua
		 */

		super(String.format("%s not found with %s :%s", resourceName, fieldName, fieldValue));
		System.err.println(3);

//		this.resourceName = resourceName;
//		this.fieldName = fieldName;
//		this.fieldValue = fieldValue;
	}

}
