package com.blog.contoller;

import java.util.List;

import javax.validation.Valid;

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
import com.blog.payload.CatagoryDto;
import com.blog.payload.UserDto;
import com.blog.service.CatagoryService;
import com.blog.service.impl.CatagoryServiceImpl;

import lombok.val;

@RestController
@RequestMapping("api/catagory")
public class CatagoryController {

	@Autowired(required = true)
	private CatagoryServiceImpl catagoryServiceImpl;

	// create
	@PostMapping("/")
	public ResponseEntity<CatagoryDto> createCatagory(@Valid @RequestBody CatagoryDto catagoryDto) {
		CatagoryDto createCatagory = this.catagoryServiceImpl.createCatagory(catagoryDto);

		return new ResponseEntity<CatagoryDto>(createCatagory, HttpStatus.CREATED);

	}
	// update

	@PutMapping("/{catId}")
	public ResponseEntity<CatagoryDto> updateCatagory(@Valid @RequestBody CatagoryDto catagoryDto,
			@PathVariable Integer catId) {
		CatagoryDto createCatagory = this.catagoryServiceImpl.updateCatagory(catagoryDto, catId);

		return new ResponseEntity<CatagoryDto>(createCatagory, HttpStatus.OK);

	}

	// delete
	@DeleteMapping("/{catId}")
	public ResponseEntity<CatagoryDto> deleteCatagory(@PathVariable Integer catId) {
		this.catagoryServiceImpl.deleteCatagory(catId);

		return new ResponseEntity(new ApiResponce("catagory deleted Sucessfully", true), HttpStatus.OK);

	}
	// get

	@GetMapping("/{catId}")
	public ResponseEntity<CatagoryDto> getcatagory(@PathVariable Integer catId) {
		CatagoryDto catagoryDto = this.catagoryServiceImpl.getCatagory(catId);

		return new ResponseEntity<CatagoryDto>(catagoryDto, HttpStatus.OK);

	}

	// getAll

	@GetMapping("/")
	public ResponseEntity<List<CatagoryDto>> getAllCatagory() {

		List<CatagoryDto> catagories = this.catagoryServiceImpl.getAllCatagory();
		return ResponseEntity.ok(catagories);
	}

}
