package com.example.Module2.controllers;

import com.example.Module2.dto.EmployeeDTO;
import com.example.Module2.services.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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
    public ResponseEntity<EmployeeDTO> getEmployeeById(@PathVariable(name = "employeeId") Long Id) {
        Optional<EmployeeDTO> employeeDTO = employeeService.getEmployeeById(Id);
        return employeeDTO.map(employeeDTO1 -> ResponseEntity.ok(employeeDTO1))
                .orElseThrow(() -> new RuntimeException("Employee not found with id: "+Id));
    }

    @GetMapping(path = "getAllEmployee")
    public ResponseEntity<List<EmployeeDTO>> getAllEmployee(@RequestParam(required = false) Long id,
                                               @RequestParam(required = false) String name) {
        return ResponseEntity.ok(employeeService.getAllEmployee());
    }

    @PostMapping(path = "addEmployee")
    public ResponseEntity<EmployeeDTO> createEmployee(@RequestBody EmployeeDTO employeeDTO) {
        EmployeeDTO savedEmployee = employeeService.createEmployee(employeeDTO);
        return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);
    }

    @DeleteMapping(path = "deleteEmployee/{employeeId}")
    public ResponseEntity<Boolean> deleteEmployeeById(@PathVariable(name = "employeeId") Long Id) {
        return ResponseEntity.ok(employeeService.deleteEmployeeById(Id));
    }

    @PutMapping("updateEmployee/{employeeId}")
    public ResponseEntity<EmployeeDTO> updateEmployeeUsingId(@RequestBody EmployeeDTO employeeDTO, @PathVariable(name = "employeeId") Long Id) {
        return ResponseEntity.ok(employeeService.updateEmployeeUsingId(Id, employeeDTO));
    }

    @PatchMapping("/{employeeId}")
    public ResponseEntity<EmployeeDTO> updatePartialEmployeeById(@RequestBody Map<String, Object> updates, @PathVariable Long employeeId) {
        EmployeeDTO employeeDTO = employeeService.updatePartialEmployeeById(employeeId, updates);
        if(employeeId == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(employeeDTO);
    }
}
