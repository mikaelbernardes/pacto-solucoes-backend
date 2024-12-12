package com.pacto_solucoes.recruitment.repositories;

import com.pacto_solucoes.recruitment.domain.Vacancy;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VacancyRepository extends JpaRepository<Vacancy, Long> {
}
