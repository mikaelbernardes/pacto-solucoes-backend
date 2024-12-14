package com.pacto_solucoes.recruitment.controllers;

import com.pacto_solucoes.recruitment.DTOs.ApplicationUserDTO;
import com.pacto_solucoes.recruitment.domain.Application;
import com.pacto_solucoes.recruitment.domain.User;
import com.pacto_solucoes.recruitment.service.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/application")
public class ApplicationController {

    @Autowired
    private ApplicationService applicationService;

    @GetMapping("/{id}")
    public ResponseEntity<Application> getApplicationById(@PathVariable Long id) {
        return applicationService.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Application>> getApplicationsByUser(@PathVariable Long userId) {
        List<Application> applications = applicationService.findByUserId(userId);

        if (applications.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(applications);
    }

    @GetMapping("/{vacancyId}/applications/users")
    public ResponseEntity<List<ApplicationUserDTO>> getUsersByVacancy(@PathVariable Long vacancyId) {
        List<ApplicationUserDTO> users = applicationService.getUsersByVacancyId(vacancyId);
        if (users.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(users);
    }

    @PutMapping
    public ResponseEntity<Application> changeApplication(@RequestBody Application request) {
        Optional<Application> updatedApplication = applicationService.changeApplication(request);

        return updatedApplication
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteApplication(@PathVariable Long id) {
        if (applicationService.findById(id).isPresent()) {
            applicationService.delete(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
