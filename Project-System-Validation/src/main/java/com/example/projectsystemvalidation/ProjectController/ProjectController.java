package com.example.projectsystemvalidation.ProjectController;

import com.example.projectsystemvalidation.ApiResponse.ApiResponse;
import com.example.projectsystemvalidation.Model.Project;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/project-system")
public class ProjectController {
    ArrayList<Project> projects = new ArrayList<>();

    @GetMapping("/get")
    public ResponseEntity getProject(){
        return ResponseEntity.status(200).body(projects);
    }

    @PostMapping("/add")
    public ResponseEntity addProject(@RequestBody @Valid Project project, Errors errors ){
        if(errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        projects.add(project);
        return ResponseEntity.status(200).body(new ApiResponse("Project added successfully"));
    }

    @PutMapping("/update/{index}")
    public ResponseEntity updateProject(@PathVariable int index ,@RequestBody @Valid Project project, Errors errors){
        if(errors.hasErrors()){
           String message = errors.getFieldError().getDefaultMessage();
           return ResponseEntity.status(400).body(message);
        }
        projects.set(index,project);
        return ResponseEntity.status(200).body(new ApiResponse("Project updated successfully"));

    }

    @DeleteMapping("/delete/{index}")
    public ResponseEntity deleteProject(@PathVariable int index){
        projects.remove(index);
        return ResponseEntity.status(200).body(new ApiResponse("Project deleted successfully"));
    }

    @PutMapping("/change-status/{index}")
    public ResponseEntity changeStatus(@PathVariable int index){
        if(projects.get(index).getStatus().equalsIgnoreCase("not started")){
            projects.get(index).setStatus("in progress");
            return ResponseEntity.status(200).body(new ApiResponse("Project status successfully changed"));
        }
        if(projects.get(index).getStatus().equalsIgnoreCase("in progress")){
            projects.get(index).setStatus("completed");
            return ResponseEntity.status(200).body(new ApiResponse("Project status successfully changed"));
        }
        return ResponseEntity.status(200).body(new ApiResponse("Project status is completed"));
    }

    @GetMapping("/search-title/{title}")
    public ResponseEntity searchProjectTitle(@PathVariable String title) {
        for (Project project : projects) {
            if (project.getTitle().equalsIgnoreCase(title)) {
                return ResponseEntity.status(200).body(project);
            }
        }
        return ResponseEntity.status(200).body(new ApiResponse("Project not found"));
    }

    @GetMapping("/search-company/{company}")
    public ResponseEntity searchProjectCompany(@PathVariable String company) {
        ArrayList<Project> company_project = new ArrayList<>();
        for (Project project : projects) {
            if (project.getCompanyName().equalsIgnoreCase(company)) {
                company_project.add(project);
            }
        }
        return ResponseEntity.status(200).body(company_project);
    }









}
