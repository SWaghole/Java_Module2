package com.example.Module2.controllers;

import com.example.Module2.dto.DepartmentDTO;
import com.example.Module2.dto.EmployeeDTO;
import com.example.Module2.exception.ResourceNotFoundException;
import com.example.Module2.services.DepartmentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/department")
public class DepartmentController {

    final private DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping("getDepartmentById/{departmentId}")
    public ResponseEntity<DepartmentDTO> getDepartmentById(@PathVariable(value = "departmentId") Long Id) {
        Optional<DepartmentDTO> departmentDTO = departmentService.getDepartmentById(Id);
        return departmentDTO
                .map(departmentDTO1 -> ResponseEntity.ok(departmentDTO1))
                .orElseThrow(() -> new ResourceNotFoundException("Department not found:" + Id));
    }

    @GetMapping("getAllDepartment")
    public ResponseEntity<List<DepartmentDTO>> getAllDepartment() {
        return ResponseEntity.ok(departmentService.getAllDepartment());
    }

    @PostMapping("createDepartment")
    public ResponseEntity<DepartmentDTO> createDepartment(@RequestBody @Valid DepartmentDTO departmentDTO) {
        DepartmentDTO savedDepartment = departmentService.createDepartment(departmentDTO);
        return new ResponseEntity<>(savedDepartment, HttpStatus.CREATED);
    }

    @DeleteMapping("deleteDepartment/{departmentId}")
    public ResponseEntity<Boolean> deleteDepartment(@PathVariable(value = "departmentId") Long Id) {
        return ResponseEntity.ok(departmentService.deleteDepartment(Id));
    }

    @PatchMapping("updateDepartment/{departmentId}")
    public ResponseEntity<DepartmentDTO> updateDepartment(@RequestBody Map<String, Object> updates, @PathVariable(value = "departmentId") Long Id) {
        DepartmentDTO departmentDTO = departmentService.updateDepartment(updates, Id);
        if(departmentDTO == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(departmentDTO);
    }
}
