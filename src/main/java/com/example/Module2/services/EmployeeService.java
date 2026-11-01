package com.example.Module2.services;

import com.example.Module2.dto.EmployeeDTO;
import com.example.Module2.entities.EmployeeEntity;
import com.example.Module2.exception.ResourceNotFoundException;
import com.example.Module2.repository.EmployeeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.util.ReflectionUtils;
import org.springframework.stereotype.Service;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    final public EmployeeRepository employeeRepository;
    final public ModelMapper modelMapper;

    public EmployeeService(EmployeeRepository employeeRepository, ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
        this.employeeRepository = employeeRepository;
    }

    public Optional<EmployeeDTO> getEmployeeById(Long Id) {
        return employeeRepository.findById(Id).map(employeeEntity -> modelMapper.map(employeeEntity, EmployeeDTO.class));
    }

    public List<EmployeeDTO> getAllEmployee() {
        List<EmployeeEntity> employeeEntities = employeeRepository.findAll();
        return employeeEntities
                .stream()
                .map(employeeEntity -> modelMapper.map(employeeEntity, EmployeeDTO.class))
                .collect(Collectors.toList());
    }

    public EmployeeDTO createEmployee(EmployeeDTO employeeDTO) {
        EmployeeEntity toSaveEntity = modelMapper.map(employeeDTO, EmployeeEntity.class);
        EmployeeEntity savedEmployeeEntity = employeeRepository.save(toSaveEntity);
        return modelMapper.map(savedEmployeeEntity, EmployeeDTO.class);
    }

    public boolean deleteEmployeeById(Long id) {
        isExistsByEmployeeId(id);
        employeeRepository.deleteById(id);
        return true;
    }

    public EmployeeDTO updateEmployeeUsingId(Long Id, EmployeeDTO employeeDTO) {
        isExistsByEmployeeId(Id);
        EmployeeEntity employeeEntity = modelMapper.map(employeeDTO, EmployeeEntity.class);
        employeeEntity.setId(Id);
        EmployeeEntity savedEmployeeEntity = employeeRepository.save(employeeEntity);
        return modelMapper.map(savedEmployeeEntity, EmployeeDTO.class);
    }

    public void isExistsByEmployeeId(Long employeeId) {
        boolean exist = employeeRepository.existsById(employeeId);
        if(!exist) throw new ResourceNotFoundException("Employee not found " + employeeId);
    }

    public EmployeeDTO updatePartialEmployeeById(Long employeeId, Map<String, Object> updates) {
        isExistsByEmployeeId(employeeId);
        EmployeeEntity employeeEntity = employeeRepository.findById(employeeId).get();
        updates.forEach((field, value) -> {
            Field fieldToUpdated = ReflectionUtils.getRequiredField(EmployeeEntity.class, field);
            fieldToUpdated.setAccessible(true);
            ReflectionUtils.setField(fieldToUpdated, employeeEntity, value);
        });
        return modelMapper.map(employeeRepository.save(employeeEntity), EmployeeDTO.class);
    }
}
