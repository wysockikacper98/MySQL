package com.example.demo.service;

import com.example.demo.dao.SalariesRepository;
import com.example.demo.dto.SalariesWhere;
import com.example.demo.entity.Salaries;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class SalariesService {

    private SalariesRepository salariesRepository;

    public SalariesService(SalariesRepository salariesRepository) {
        this.salariesRepository = salariesRepository;
    }


    public Long countDane() {
        return (long) salariesRepository.countDane();
    }

    public void deleteRandom(Salaries salaries) {
        salariesRepository.delete(salaries);
    }

    public Salaries findRandom() {
        List<Salaries> salariesList = salariesRepository.showCouple();
        Random random = new Random();
        int selected = random.nextInt(salariesList.size());
        System.out.println("Wybrany rekord: " + selected);

        return salariesList.get(selected);
    }

    public void updateRandom(Salaries salaries) {
        System.out.println("Salary before update:" + salaries.getSalary());
        salaries.setSalary(String.valueOf(Integer.parseInt(salaries.getSalary()) + 13));
        salariesRepository.save(salaries);
        System.out.println("Salary after update:" + salaries.getSalary());
    }

    public void insertRandom(Salaries salaries) {
        salariesRepository.save(salaries);
    }



    public int select(Salaries randomSalary) {
        return salariesRepository.countSelect(randomSalary.getEmp_no(), randomSalary.getSalary(), randomSalary.getFrom_date(), randomSalary.getTo_date());
    }

    public List<Salaries> selectAll() {
        return salariesRepository.showMilion();
    }

    public List<Salaries> group() {
        return salariesRepository.group();
    }

    public List<SalariesWhere> where() {
        return salariesRepository.where();
    }

    public void insertFromFile(String filePath) {
        salariesRepository.importFromFile(filePath);
    }

    public void deleteAllDataInTheTable() {
        salariesRepository.deleteAllDataInTheTable();
    }
}
