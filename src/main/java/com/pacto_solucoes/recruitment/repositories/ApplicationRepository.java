package com.pacto_solucoes.recruitment.repositories;

import com.pacto_solucoes.recruitment.domain.Application;
import com.pacto_solucoes.recruitment.domain.User;
import com.pacto_solucoes.recruitment.domain.Vacancy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ApplicationRepository extends JpaRepository<Application, Long> {

    List<Application> findByUserAndVacancy(User user, Vacancy vacancy);

    List<Application> findByUserId(Long userId);

    @Query("SELECT a FROM Application a WHERE a.vacancy.id = :vacancyId")
    List<Application> findByVacancyId(Long vacancyId);

}
