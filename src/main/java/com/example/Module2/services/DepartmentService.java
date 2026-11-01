package com.example.Module2.services;

import com.example.Module2.dto.DepartmentDTO;
import com.example.Module2.dto.EmployeeDTO;
import com.example.Module2.entities.DepartmentEntity;
import com.example.Module2.entities.EmployeeEntity;
import com.example.Module2.exception.ResourceNotFoundException;
import com.example.Module2.repository.DepartmentRepository;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.data.util.ReflectionUtils;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DepartmentService {

    final public DepartmentRepository departmentRepository;
    final public ModelMapper modelMapper;


    public DepartmentService(DepartmentRepository departmentRepository, ModelMapper modelMapper) {
        this.departmentRepository = departmentRepository;
        this.modelMapper = modelMapper;
    }

    public Optional<DepartmentDTO> getDepartmentById(Long Id) {
        isExistsByEmployeeId(Id);
        return departmentRepository
                .findById(Id)
                .map(departmentEntity -> modelMapper.map(departmentEntity, DepartmentDTO.class));
    }

    public DepartmentDTO createDepartment(@Valid DepartmentDTO departmentDTO) {
        DepartmentEntity toSavedEntity = modelMapper.map(departmentDTO, DepartmentEntity.class);
        DepartmentEntity savedDepartmentEntity  = departmentRepository.save(toSavedEntity);
        return modelMapper.map(savedDepartmentEntity, DepartmentDTO.class);
    }

    public Boolean deleteDepartment(Long Id) {
        isExistsByEmployeeId(Id);
        departmentRepository.deleteById(Id);
        return true;
    }

    public List<DepartmentDTO> getAllDepartment() {
        return departmentRepository
                .findAll()
                .stream()
                .map(departmentEntity -> modelMapper.map(departmentEntity, DepartmentDTO.class))
                .collect(Collectors.toList());
    }

    public DepartmentDTO updateDepartment(Map<String, Object> updates, Long Id) {
        isExistsByEmployeeId(Id);
        DepartmentEntity departmentEntity = departmentRepository.findById(Id).get();
        updates.forEach((field, value) -> {
            Field fieldToBeUpdated = ReflectionUtils.getRequiredField(DepartmentEntity.class, field);
            fieldToBeUpdated.setAccessible(true);
            ReflectionUtils.setField(fieldToBeUpdated, departmentEntity, value);
        });
        return modelMapper.map(departmentRepository.save(departmentEntity), DepartmentDTO.class);
    }

    public void isExistsByEmployeeId(Long Id) {
        boolean exist = departmentRepository.existsById(Id);
        if(!exist) throw new ResourceNotFoundException("Department Not Found" + Id);
    }
}
