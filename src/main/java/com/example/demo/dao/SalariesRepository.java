package com.example.demo.dao;

import com.example.demo.dto.SalariesWhere;
import com.example.demo.entity.Salaries;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@CrossOrigin(origins = "*")
@RepositoryRestResource(collectionResourceRel = "salary", path = "salary")
public interface SalariesRepository extends JpaRepository<Salaries, Integer> {
    @Query(value = "select count(s) from Salaries s")
    int countDane();

    @Query(value = "select count(s) from Salaries s where s.emp_no = :emp_no and s.salary = :salary and s.from_date = :from_date and s.to_date = :to_date")
    int countSelect(@Param("emp_no") String emp_no, @Param("salary") String salary, @Param("from_date") Date from_date, @Param("to_date") Date to_date);


    @Query(nativeQuery = true, value = "select * from salaries.salaries limit 1000000")
    List<Salaries> showMilion();

    @Query(nativeQuery = true, value = "select * from salaries.salaries limit 10000")
    List<Salaries> showCouple();

    @Query(nativeQuery = true, value = "select * from salaries.salaries s where from_date >= '2020-02-21' and to_date <= '2021-02-21' group by s.id, s.salary, s.from_date;")
    List<Salaries> group();

    @Query(value = "select new com.example.demo.dto.SalariesWhere(s.emp_no, s.to_date) from Salaries s where s.from_date >= '2020-02-21' and s.to_date <= '2021-02-21'")
    List<SalariesWhere> where();

    @Transactional
    @Modifying
    @Query(nativeQuery = true, value = "LOAD DATA INFILE :fileName INTO TABLE salaries.salaries (emp_no, salary, from_date, to_date) SET id = NULL")
    void importFromFile(@Param("fileName") String filePath);

    @Transactional
    @Modifying
    @Query(nativeQuery = true, value = "delete from salaries.salaries")
    void deleteAllDataInTheTable();
}
