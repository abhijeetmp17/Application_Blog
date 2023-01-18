package com.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blog.entity.Catagory;
/*
 * JpaRepository<Konsi class handel karni hai konsa datatype , Uski id kya hai>
 */
public interface CatagoryRepo extends JpaRepository<Catagory, Integer> {

}
