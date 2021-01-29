package com.example.demo.controller;


import com.example.demo.dto.ReturnData;
import com.example.demo.dto.SalariesWhere;
import com.example.demo.entity.Salaries;
import com.example.demo.service.SalariesService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/mysql")
public class AppController {

    private final SalariesService salariesService;

    public AppController(SalariesService salariesService) {
        this.salariesService = salariesService;
    }


    //pobranie ilości rekordów
    @GetMapping("/mysql")
    public ReturnData mysql() {
        ReturnData data = new ReturnData();
        data.setIloscWybranychDanych(0L);

        Long start = System.nanoTime();
        salariesService.selectAll();
        Long finish = System.nanoTime();

        data.setIloscDanych(salariesService.countDane());
        data.setCzasOpreacji((int) ((finish - start) / 1_000_000.0));
        return data;
    }

    //usuwa losowy rekord z bazy danych
    @GetMapping("/delete-random")
    public ReturnData deleteRandom() {
        ReturnData data = new ReturnData();
        data.setIloscDanych(salariesService.countDane());

        Salaries salaries = salariesService.findRandom();

        Long start = System.nanoTime();
        salariesService.deleteRandom(salaries);
        Long finish = System.nanoTime();

        data.setCzasOpreacji((int) ((finish - start) / 1_000_000.0));

        data.setIloscWybranychDanych(salariesService.countDane() - data.getIloscDanych());
        return data;
    }


    //update random
    @GetMapping("/update-random")
    public ReturnData updateRandom() {
        ReturnData data = new ReturnData();
        data.setIloscDanych(salariesService.countDane());

        Salaries salaries = salariesService.findRandom();
        System.out.println(salaries);
        System.out.println("ID znalezionego: " + salaries.getId());
        Long start = System.nanoTime();
        salariesService.updateRandom(salaries);
        Long finish = System.nanoTime();

        data.setCzasOpreacji((int) ((finish - start) / 1_000_000.0));

        data.setIloscWybranychDanych(salariesService.countDane() - data.getIloscDanych());
        return data;
    }

    //insert salary
    @GetMapping("/insert-random")
    public ReturnData insertRandom() throws ParseException {
        ReturnData data = new ReturnData();
        data.setIloscDanych(salariesService.countDane());

        Salaries salaries = new Salaries();
        salaries.setEmp_no("123123");
        salaries.setSalary("60000");
        salaries.setFrom_date(new SimpleDateFormat("yyyy-MM-dd").parse("2020-02-22"));
        salaries.setTo_date(new SimpleDateFormat("yyyy-MM-dd").parse("2021-02-22"));

        Long start = System.nanoTime();
        salariesService.insertRandom(salaries);
        Long finish = System.nanoTime();

        data.setCzasOpreacji((int) ((finish - start) / 1_000_000.0));
        data.setIloscWybranychDanych(salariesService.countDane() - data.getIloscDanych());
        data.setIloscDanych(salariesService.countDane());
        return data;
    }

//    //podwaja wielkość bazy danych, działa ultra słabo
//    @GetMapping("/double/double/double")
//    public ReturnData addMil() {
//        ReturnData data = new ReturnData();
//        data.setIloscDanych(salariesService.countDane());
//        List<Salaries> salariesList = salariesService.findAll();
//        List<Salaries> salariesDoubleList = new ArrayList<>();
//        salariesList.forEach(salaries -> {
//            Salaries sal = new Salaries();
//            sal.setEmp_no(salaries.getEmp_no());
//            sal.setSalary(salaries.getSalary());
//            sal.setFrom_date(salaries.getFrom_date());
//            sal.setTo_date(salaries.getTo_date());
//            salariesDoubleList.add(sal);
//        });
//
//        Long start = System.nanoTime();
//        salariesService.doubleUP(salariesDoubleList);
//        Long finish = System.nanoTime();
//
//        data.setCzasOpreacji((int) ((finish - start) / 1_000_000.0));
//        data.setIloscWybranychDanych(salariesService.countDane() - data.getIloscDanych());
//        data.setIloscDanych(salariesService.countDane());
//        return data;
//
//    }

    //bardziej skąplikowany select
    @GetMapping("/select")
    public ReturnData select() {
        ReturnData data = new ReturnData();
        data.setIloscDanych(salariesService.countDane());
        Salaries salaries = salariesService.findRandom();

        Long start = System.nanoTime();
        data.setIloscWybranychDanych((long) salariesService.select(salaries));
        Long finish = System.nanoTime();

        data.setCzasOpreacji((int) ((finish - start) / 1_000_000.0));

        return data;
    }

    //grupowanie
    @GetMapping("/group")
    public ReturnData group() {
        ReturnData data = new ReturnData();
        data.setIloscDanych(salariesService.countDane());

        Long start = System.nanoTime();
        data.setIloscWybranychDanych((long) salariesService.group().size());
        Long finish = System.nanoTime();

        data.setCzasOpreacji((int) ((finish - start) / 1_000_000.0));

        return data;
    }

    //where z niektórymi kolumnami
    @GetMapping("/where")
    public ReturnData where() {
        ReturnData data = new ReturnData();
        data.setIloscDanych(salariesService.countDane());
        Long start = System.nanoTime();
        List<SalariesWhere> salariesWhere = salariesService.where();
        Long finish = System.nanoTime();
        data.setIloscWybranychDanych((long) salariesWhere.size());

        data.setCzasOpreacji((int) ((finish - start) / 1_000_000.0));

        return data;
    }

    //insert z pliku
    @GetMapping("/insert-from-file")
    public ReturnData insertFromFile(){
        ReturnData data = new ReturnData();
        data.setIloscWybranychDanych(salariesService.countDane());
        Long start = System.nanoTime();
//        salariesService.insertFromFile("C:\\Users\\razoh\\Desktop\\PAB Projkect\\MySQL\\src\\main\\resources\\salaries.csv");
        salariesService.insertFromFile("salaries.csv");
        Long finish = System.nanoTime();
        data.setIloscDanych(salariesService.countDane());
        data.setIloscWybranychDanych(data.getIloscDanych() - data.getIloscWybranychDanych());

        data.setCzasOpreacji((int) ((finish - start) / 1_000_000.0));

        return data;
    }


}
