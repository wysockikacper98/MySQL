package com.example.demo.entity;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "salaries")
@Getter
@Setter
public class Salaries {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "emp_no")
    private String emp_no;

    @Column(name = "salary")
    private String salary;

    @Column(name = "from_date")
    private Date from_date;

    @Column(name = "to_date")
    private Date to_date;

}
