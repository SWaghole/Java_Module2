package com.example.Module2.dto;

import com.example.Module2.annotations.EmployeeRoleValidation;
import com.example.Module2.annotations.Password;
import com.example.Module2.annotations.PrimeNumber;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDTO {

    private Long id;

    @NotBlank(message = "Name of the employee cannot be blank")
    @Size(min = 3, max = 10, message = "Number of characters in name should be in range : [3,10]")
    private String name;

    @NotBlank(message = "Employee Email cannot be blank")
    @Email(message = "Email should be valid")
    private String email;

    @Min(value = 18)
    @Max(value = 60)
    private Integer age;

    @NotNull(message = "Salary of Employee should be not null")
    @Positive(message = "Salary should be greater then zero")
    @Digits(integer = 6, fraction = 2, message = "The salary can be in the from XXXXXX.YY")
    @DecimalMax(value = "100000.99")
    @DecimalMin(value = "100.50")
    private Double salary;

    @EmployeeRoleValidation(message = "Role should be either USER or ADMIN")
    private String role;

    @AssertTrue(message = "Employee should be active")
    private Boolean active;

    @PastOrPresent(message = "DateOfJoining field in Employee cannot be in the future")
    private LocalDate dateOfJoining;
}
