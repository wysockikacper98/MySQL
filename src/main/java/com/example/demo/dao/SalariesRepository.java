package com.example.demo.dao;

import com.example.demo.dto.QueryItems;
import com.example.demo.entity.Salaries;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "salary", path = "salary")
public interface SalariesRepository extends JpaRepository<Salaries, Integer> {
    @Query(value = "select count(s) from Salaries s")
    int countDane();

}
