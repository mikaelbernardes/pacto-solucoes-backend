package com.pacto_solucoes.recruitment.service;

import com.pacto_solucoes.recruitment.DTOs.ApplicationUserDTO;
import com.pacto_solucoes.recruitment.domain.Application;
import com.pacto_solucoes.recruitment.domain.User;
import com.pacto_solucoes.recruitment.repositories.ApplicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ApplicationService {

    @Autowired
    private ApplicationRepository applicationRepository;

    public List<Application> findAll() {
        return applicationRepository.findAll();
    }

    public Optional<Application> findById(Long id) {
        return applicationRepository.findById(id);
    }

    public List<Application> findByUserId(Long userId) {
        return applicationRepository.findByUserId(userId);
    }

    public Optional<Application> changeApplication(Application application) {
        return applicationRepository.findById(application.getId())
                .map(existingApplication -> {
                    existingApplication.setStatus(application.getStatus());
                    existingApplication.setVacancy(application.getVacancy());
                    existingApplication.setUser(application.getUser());

                    return applicationRepository.save(existingApplication);
                });
    }

    public void delete(Long id) {
        applicationRepository.deleteById(id);
    }

    public List<ApplicationUserDTO> getUsersByVacancyId(Long vacancyId) {
        List<Application> applications = applicationRepository.findByVacancyId(vacancyId);
        return applications.stream()
                .map(application -> {
                    var user = application.getUser();
                    return new ApplicationUserDTO(user.getName(), user.getLogin());
                })
                .collect(Collectors.toList());
    }

}
