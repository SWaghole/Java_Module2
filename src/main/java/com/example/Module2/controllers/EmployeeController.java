package com.example.Module2.controllers;


import com.example.Module2.dto.EmployeeDTO;
import com.example.Module2.entities.EmployeeEntity;
import com.example.Module2.services.EmployeeService;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

//    @GetMapping(path = "/employeeSecretCode")
//    public String getSecretKey() {
//        return "asd1212739";
//    }

    final private EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping(path = "getEmployeeById/{employeeId}")
    public EmployeeDTO getEmployeeById(@PathVariable(name = "employeeId") Long Id) {
        return employeeService.getEmployeeById(Id);
    }

    @GetMapping(path = "getAllEmployee")
    public List<EmployeeDTO> getAllEmployee(@RequestParam(required = false) Long id,
                                               @RequestParam(required = false) String name) {
        return employeeService.getAllEmployee();
    }

    @PostMapping(path = "addEmployee")
    public EmployeeDTO createEmployee(@RequestBody EmployeeDTO employeeDTO) {
        System.out.println("Employee Create Successfully");
        return employeeService.createEmployee(employeeDTO);
    }

    @DeleteMapping(path = "deleteEmployee/{employeeId}")
    public void deleteEmployeeById(@PathVariable(name = "employeeId") Long Id) {
        System.out.println("Employee Deleted");
        employeeService.deleteEmployeeById(Id);
    }
}
