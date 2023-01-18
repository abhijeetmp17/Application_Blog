package com.blog.service;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder.In;

import com.blog.payload.CatagoryDto;

public interface CatagoryService 
{
	
	//create 
	CatagoryDto createCatagory(CatagoryDto catagoryDto);
	
	//update 
	CatagoryDto updateCatagory(CatagoryDto catagoryDto , Integer catagoryId);
	//delete
	void deleteCatagory(Integer catagoryId);

	//get
	CatagoryDto getCatagory (Integer catagoryId);
	//getall
	List<CatagoryDto> getAllCatagory();
	
	
}
