package com.example.eventsystemvalidation.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
@Data
@AllArgsConstructor
public class Event {

    @NotNull(message = "cannot be null")
    @Min(value = 2, message = "id length should be more than 2")
    private int id;

    @NotEmpty(message = "cannot be Empty")
    @Size(min = 15,message = "the text should be longest")
    private String description;

    @NotNull(message = "cannot be null")
    @Min(value = 25, message = "Capacity must be 25 and above")
    @Digits(integer = 5,fraction = 0,message = "The Capacity must be a numeric value ")
    private int capacity;

    @NotNull(message = "cannot be null")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime startDate;

    @NotNull(message = "cannot be null")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime endDate;

}
