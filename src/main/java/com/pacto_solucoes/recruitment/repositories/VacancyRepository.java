package com.pacto_solucoes.recruitment.repositories;

import com.pacto_solucoes.recruitment.domain.Vacancy;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VacancyRepository extends JpaRepository<Vacancy, Long> {
    List<Vacancy> findByUserId(Long userId);
}
