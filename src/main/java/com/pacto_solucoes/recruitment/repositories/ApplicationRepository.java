package com.pacto_solucoes.recruitment.repositories;

import com.pacto_solucoes.recruitment.domain.Application;
import com.pacto_solucoes.recruitment.domain.User;
import com.pacto_solucoes.recruitment.domain.Vacancy;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ApplicationRepository extends JpaRepository<Application, Long> {

    List<Application> findByUserAndVacancy(User user, Vacancy vacancy);

}
