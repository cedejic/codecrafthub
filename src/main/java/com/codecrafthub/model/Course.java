package com.codecrafthub.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Course model stored in JSON file.
 * Lombok generates getters/setters automatically.
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Course {

    /**
     * Auto-generated ID starting from 1
     */
    private Long id;

    /**
     * Required course name
     */
    @NotBlank(message = "Name is required")
    private String name;

    /**
     * Required description
     */
    @NotBlank(message = "Description is required")
    private String description;

    /**
     * JSON field mapping: target_date
     */
    @JsonProperty("target_date")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate targetDate;

    /**
     * Required status value
     */
    private String status;

    /**
     * Auto-generated timestamp
     */
    @JsonProperty("created_at")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdAt;
}