package com.example.Module2.controllers;


import com.example.Module2.entities.EmployeeEntity;
import com.example.Module2.repository.EmployeeRepository;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

//    @GetMapping(path = "/employeeSecretCode")
//    public String getSecretKey() {
//        return "asd1212739";
//    }

    final private EmployeeRepository employeeRepository;

    public EmployeeController(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @GetMapping(path = "getEmployeeById/{employeeId}")
    public EmployeeEntity getEmployeeById(@PathVariable(name = "employeeId") Long Id) {
        return employeeRepository.findById(Id).orElse(null);
    }

    @GetMapping(path = "getAllEmployee")
    public List<EmployeeEntity> getAllEmployee(@RequestParam(required = false) Long id,
                                               @RequestParam(required = false) String name) {
        return employeeRepository.findAll();
    }

    @PostMapping(path = "addEmployee")
    public EmployeeEntity createEmployee(@RequestBody EmployeeEntity employeeEntity) {
        System.out.println("Employee Create Successfully");
        return employeeRepository.save(employeeEntity);
    }

    @DeleteMapping(path = "deleteEmployee/{employeeId}")
    public void deleteEmployeeById(@PathVariable Long employeeId) {
        System.out.println("Employee Deleted");
        employeeRepository.deleteById(employeeId);
    }
}
