package com.example.demo.dto;

import com.example.demo.entity.Salaries;

import java.util.Date;

public class SalariesWhere {

    private String salary;
    private Date from_date;

    public SalariesWhere(String salary, Date from_date) {
        this.salary = salary;
        this.from_date = from_date;
    }

}
