package com.blog.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.blog.payload.ApiResponce;

/*
 * @RestControllerAdvice -> hamare sare contoller scan karegi aur agar excrption aati hai
 * to iss class main hone wale methods ko execute karegi.
 */
@RestControllerAdvice	
public class GlobleExceptionHandler {
	/*
	 * Exteption handler method par hum @ExceptionHandler likhte hai and uske (iss
	 * main hum use handle karne wali class likhte hai) jab bhi ye exception aayega
	 * tab hi ye method call hogi
	 */
	@ExceptionHandler(ResourceNotFoundExcrption.class)
	public ResponseEntity<ApiResponce> ResourceNotFoundExceptionHandler(ResourceNotFoundExcrption ex) {
		System.err.println(4);
		String msg = ex.getMessage();
		System.err.println(msg);
		ApiResponce aResponce = new ApiResponce(msg, false);
		return new ResponseEntity<ApiResponce>(aResponce, HttpStatus.NOT_FOUND);

	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, String>> handleMethodArgNotValidException(MethodArgumentNotValidException ex) {
		/*
		 * error pudhe show karaicha ahe in the form of key and value pair
		 */
		Map<String, String> resp = new HashMap<>();

		ex.getBindingResult().getAllErrors().forEach((error) -> {
			String fieldName = ((FieldError) error).getField();
			String message = error.getDefaultMessage();
			resp.put(fieldName, message);
		});
		return new ResponseEntity<Map<String, String>>(resp, HttpStatus.BAD_REQUEST);
	}
}
