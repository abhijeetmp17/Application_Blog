package com.blog.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.entity.Catagory;
import com.blog.exception.ResourceNotFoundExcrption;
import com.blog.payload.CatagoryDto;
import com.blog.repository.CatagoryRepo;
import com.blog.service.CatagoryService;

import net.bytebuddy.asm.Advice.This;
@Service
public class CatagoryServiceImpl implements CatagoryService {
	@Autowired
	private CatagoryRepo catagoryRepo;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public CatagoryDto createCatagory(CatagoryDto catagoryDto) {
		Catagory catagory = this.modelMapper.map(catagoryDto, Catagory.class);
		Catagory save = this.catagoryRepo.save(catagory);

		return this.modelMapper.map(save, CatagoryDto.class);
	}

	@Override
	public CatagoryDto updateCatagory(CatagoryDto catagoryDto, Integer catagoryId) {
		Catagory catagory = this.catagoryRepo.findById(catagoryId)
				.orElseThrow(() -> new ResourceNotFoundExcrption("Catagory", "catagory_id", catagoryId));

		catagory.setCatagoryIdInteger(catagoryDto.getCatagoryId());
		catagory.setCatagoryDescription(catagoryDto.getCatagoryDescription());
		catagory.setCatagoryTitle(catagoryDto.getCatagoryTitle());

		Catagory catagory2 = this.catagoryRepo.save(catagory);

		return this.modelMapper.map(catagory2, CatagoryDto.class);
	}

	@Override
	public void deleteCatagory(Integer catagoryId) {

		Catagory catagory = this.catagoryRepo.findById(catagoryId)
				.orElseThrow(() -> new ResourceNotFoundExcrption("Catagory", "catagory_id", catagoryId));

		this.catagoryRepo.delete(catagory);
	}

	@Override
	public CatagoryDto getCatagory(Integer catagoryId) {

		Catagory catagory = this.catagoryRepo.findById(catagoryId)
				.orElseThrow(() -> new ResourceNotFoundExcrption("Catagory", "catagory_id", catagoryId));

		return this.modelMapper.map(catagory, CatagoryDto.class);
	}

	@Override
	public List<CatagoryDto> getAllCatagory() {

		List<Catagory> allCatagories = this.catagoryRepo.findAll();

		List<CatagoryDto> catagories = allCatagories.stream().map((cat) -> this.modelMapper.map(cat, CatagoryDto.class))
				.collect(Collectors.toList());

		return catagories;
	}

}
