package com.example.Module2.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DepartmentEntity {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long Id;

    private String title;

    private Boolean active;

    private LocalDate createdAt;
}
