package com.pacto_solucoes.recruitment.service;

import com.pacto_solucoes.recruitment.domain.Application;
import com.pacto_solucoes.recruitment.domain.ApplicationStatus;
import com.pacto_solucoes.recruitment.domain.User;
import com.pacto_solucoes.recruitment.domain.Vacancy;
import com.pacto_solucoes.recruitment.repositories.ApplicationRepository;
import com.pacto_solucoes.recruitment.repositories.UserRepository;
import com.pacto_solucoes.recruitment.repositories.VacancyRepository;
import io.micrometer.common.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class VacancyService {

    @Autowired
    private VacancyRepository vacancyRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ApplicationRepository applicationRepository;

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

        Vacancy newVacancy = vacancyRepository.save(vacancy);

        if(newVacancy.getId() != null) {
            return newVacancy;
        }else {
            throw new Exception("Não foi possível cadastrar uma nova vaga!");
        }
    }

    public List<Vacancy> listAllVacancies() throws Exception {
        try {

            List<Vacancy> vacancies = vacancyRepository.findAll();

            if(!vacancies.isEmpty()) {
                return vacancies;
            }else {
                throw new Exception("Nenhuma vaga encontrada!");
            }

        } catch (Exception e) {
            throw e;
        }
    }

    public Application ApplyTheVacancy(Vacancy vacancy, User user) throws Exception {
        try {

            if(vacancy != null && user != null) {

                if(vacancy.getId() != null && user.getId() != null) {

                    user = userRepository.findById(user.getId()).get();

                    List<Application> submittedApplications = applicationRepository.findByUserAndVacancy(user, vacancy);

                    if(submittedApplications != null && !submittedApplications.isEmpty()) {
                        throw new Exception("Você já realizou a sua candidatura nesta vaga!");
                    }


                    Application application = new Application();
                    application.setUser(user);
                    application.setVacancy(vacancy);
                    application.setStatus(ApplicationStatus.UNDER_REVIEW);
                    application.setApplicationAt(LocalDate.now());

                    Application newApplication = applicationRepository.save(application);

                    if(newApplication.getId() != null) {
                        return newApplication;
                    }else {
                        throw new Exception("Não foi possível candidatar nessa vaga, tente novamente!");
                    }

                }else {
                    throw new Exception("informações incompletas!");
                }

            }

        } catch (Exception e) {
            throw e;
        }
        return null;
    }

}
