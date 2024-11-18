package com.example.eventsystemvalidation.EventController;

import com.example.eventsystemvalidation.ApiResponse.ApiResponse;
import com.example.eventsystemvalidation.Model.Event;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/event-system")
public class EventController {
    ArrayList<Event> events = new ArrayList<>();

    @GetMapping("/get")
    public ResponseEntity getEvent(){
        return ResponseEntity.status(200).body(events);
    }

    @PostMapping("/add")
    public ResponseEntity addEvent(@RequestBody @Valid Event event, Errors errors){
        if(errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        events.add(event);
        return ResponseEntity.status(200).body(new ApiResponse("Event added successfully"));
    }

    @PutMapping("/update/{index}")
    public ResponseEntity updateEvent(@PathVariable int index, @RequestBody @Valid Event event, Errors errors){
        if (errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        events.set(index,event);
        return ResponseEntity.status(200).body(new ApiResponse("Event updated successfully"));
    }

    @DeleteMapping("/delete/{index}")
    public ResponseEntity deleteEvent(@PathVariable int index){
        events.remove(index);
        return ResponseEntity.status(200).body("Event deleted successfully");
    }

    @PutMapping("/change-capacity/{id}/{capacity}")
    public ResponseEntity changeEventCapacity(@PathVariable int id, @PathVariable int capacity) {
        for (Event event : events) {
            if (event.getId() == id) {
                event.setCapacity(capacity);
                return ResponseEntity.status(200).body("The event capacity has been successfully changed");
            }
        }
        return ResponseEntity.status(400).body("invalid id");
    }

    @GetMapping("/search/{id}")
    public ResponseEntity searchEvent(@PathVariable int id) {
        for (Event event : events) {
            if (event.getId() == id ){
                return ResponseEntity.status(200).body(event);
            }
        }
        return ResponseEntity.status(400).body("Event not found");
    }







}
