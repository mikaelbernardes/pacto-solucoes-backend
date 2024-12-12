package com.pacto_solucoes.recruitment.service;

import com.pacto_solucoes.recruitment.domain.Vacancy;
import com.pacto_solucoes.recruitment.repositories.VacancyRepository;
import io.micrometer.common.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class VacancyService {

    @Autowired
    private VacancyRepository repository;

    public Vacancy createVacancy(Vacancy vacancy) throws Exception {

        if(vacancy == null) {
            throw new Exception("A vaga não pode ser nula");
        }

        if(vacancy.getUser() == null || vacancy.getUser().getId() == null) {
            throw new Exception("Usuário não encontrado!");
        }

        if(StringUtils.isBlank(vacancy.getTitle())) {
            throw new Exception("Preencha o título da Vaga!");
        }

        if(StringUtils.isBlank(vacancy.getDescription())) {
            throw new Exception("Preencha a descrição da Vaga!");
        }

        vacancy.setCreateAt(LocalDate.now());

        Vacancy newVacancy = repository.save(vacancy);

        if(newVacancy.getId() != null) {
            return newVacancy;
        }else {
            throw new Exception("Não foi possível cadastrar a nova Vaga!");
        }
    }

    public List<Vacancy> listAllVacancies() throws Exception {
        try {

            List<Vacancy> vacancies = repository.findAll();

            if(!vacancies.isEmpty()) {
                return vacancies;
            }else {
                throw new Exception("Nenhuma vaga encontrada!");
            }

        } catch (Exception e) {
            throw e;
        }
    }

}
