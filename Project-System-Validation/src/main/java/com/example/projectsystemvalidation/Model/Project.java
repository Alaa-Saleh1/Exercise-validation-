package com.example.projectsystemvalidation.Model;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Project {

    @NotNull(message = "cannot be null")
    @Min(value = 2, message = "id length should be more than 2")
    private int id;
    @NotEmpty(message = "cannot be empty")
    @Size(min = 8, message = "title length should be more than 8")
    private String title;
    @NotEmpty(message = "cannot be empty")
    @Size(min = 15, message = "description length should be more than 15")
    private String description;
    @NotEmpty(message = "cannot be empty")
    @Pattern(regexp = "not started|in progress|completed", message = "The status should be not started or in progress or completed")
    private String status;
    @NotEmpty(message = "cannot be empty")
    @Size(min = 6, message = "company name length should be more than 6")
    private String companyName;


}
