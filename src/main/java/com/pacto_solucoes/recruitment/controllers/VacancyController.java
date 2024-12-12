package com.pacto_solucoes.recruitment.controllers;

import com.pacto_solucoes.recruitment.DTOs.ApplyVacancyDTO;
import com.pacto_solucoes.recruitment.domain.Application;
import com.pacto_solucoes.recruitment.domain.Vacancy;
import com.pacto_solucoes.recruitment.service.VacancyService;
import io.micrometer.common.util.StringUtils;
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


}
