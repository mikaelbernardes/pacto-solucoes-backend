package com.pacto_solucoes.recruitment.controllers;

import com.pacto_solucoes.recruitment.DTOs.ApplyVacancyDTO;
import com.pacto_solucoes.recruitment.domain.Application;
import com.pacto_solucoes.recruitment.domain.Vacancy;
import com.pacto_solucoes.recruitment.service.VacancyService;
import io.micrometer.common.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/vacancy")
public class VacancyController {

    @Autowired
    private VacancyService vacancyService;

    @GetMapping
    public ResponseEntity<Object> listVacancy() {
        try {

            List<Vacancy> vacancies = vacancyService.listAllVacancies();

            if(vacancies != null) {
                return ResponseEntity.ok(vacancies);
            }else {
                return ResponseEntity.badRequest().body("Nenhuma vaga encontrada!");
            }

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Vacancy> getVacancyById(@PathVariable Long id) {
        return vacancyService.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/recruiter/{userId}")
    public ResponseEntity<Object> listVacanciesByRecruiter(@PathVariable Long userId) {
        try {
            List<Vacancy> vacancies = vacancyService.listVacanciesByUserId(userId);

            if (vacancies != null && !vacancies.isEmpty()) {
                return ResponseEntity.ok(vacancies);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Nenhuma vaga encontrada para o recrutador!");
            }

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/register")
    public ResponseEntity<Object> cadastrarVaga(@RequestBody Vacancy vacancy) {

        try {

            if(vacancy != null) {

                Vacancy newVacancy = vacancyService.createVacancy(vacancy);

                if(newVacancy != null) {
                    return ResponseEntity.ok(newVacancy);
                }else {
                    return ResponseEntity.badRequest().body("Não foi possível completar este cadastro, tente novamente!");
                }

            }else {
                return ResponseEntity.badRequest().body("Informe todos os dados");
            }

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

    @PostMapping("/apply")
    public ResponseEntity<Object> applyVacancy(@RequestBody ApplyVacancyDTO applyVacancyDTO) {
        try {

            if(applyVacancyDTO != null) {

                if(applyVacancyDTO.vacancy() != null && applyVacancyDTO.user() != null) {

                    if(applyVacancyDTO.vacancy().getId() == null) {
                        return ResponseEntity.badRequest().body("A vaga selecionada é inválida! Tente novamente.");
                    }

                    if(applyVacancyDTO.vacancy().getId() == null) {
                        return ResponseEntity.badRequest().body("Não foi possível identificar o usuário, tente novamente!");
                    }

                    Application newApplication = new Application();

                    newApplication = vacancyService.ApplyTheVacancy(applyVacancyDTO.vacancy(), applyVacancyDTO.user());

                    if(newApplication != null && newApplication.getId() != null) {
                        return ResponseEntity.ok(newApplication);
                    }else {
                        return ResponseEntity.badRequest().body("Não foi possível se candidatar a vaga!");
                    }

                }

            }

            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Vacancy> updateVacancy(@PathVariable Long id, @RequestBody Vacancy vacancyRequest) {
        try {
            Optional<Vacancy> existingVacancy = vacancyService.findById(id);
            if (existingVacancy.isEmpty()) {
                return ResponseEntity.notFound().build();
            }

            Vacancy vacancyToUpdate = existingVacancy.get();
            vacancyToUpdate.setTitle(vacancyRequest.getTitle());
            vacancyToUpdate.setDescription(vacancyRequest.getDescription());
            vacancyToUpdate.setRequirements(vacancyRequest.getRequirements());
            vacancyToUpdate.setStatus(vacancyRequest.getStatus());

            Vacancy updatedVacancy = vacancyService.changeVacancy(vacancyToUpdate);

            return ResponseEntity.ok(updatedVacancy);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVacancyById(@PathVariable Long id) {
        if (vacancyService.findById(id).isPresent()) {
            vacancyService.deleteVacancy(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
