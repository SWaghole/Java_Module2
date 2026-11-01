package com.example.Module2.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DepartmentDTO {

    private Long id;

    @NotBlank(message = "Enter title.")
    private String title;

    private Boolean active;

    @PastOrPresent(message = "Created at field in Department cannot be in the future")
    private LocalDate createdAt;
}
