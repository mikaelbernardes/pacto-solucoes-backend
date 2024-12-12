package com.pacto_solucoes.recruitment.controllers;

import com.pacto_solucoes.recruitment.domain.Vacancy;
import com.pacto_solucoes.recruitment.service.VacancyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

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

}
